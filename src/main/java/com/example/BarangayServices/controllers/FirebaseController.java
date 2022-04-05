package com.example.BarangayServices.controllers;

import com.example.BarangayServices.models.*;
import com.example.BarangayServices.services.FirebaseServiceFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class FirebaseController {

    @Autowired
    private FirebaseServiceFacade firebaseService;

    @GetMapping("/Residents")
    public List<Resident> getResidents(@RequestParam String parameterType,
                                       @RequestParam String parameterEntry,
                                       @RequestParam String barangay)
            throws ExecutionException, InterruptedException {
        return firebaseService
                .getResidents(
                        barangay,
                        parameterType,
                        parameterEntry
                );
    }

    @GetMapping("/Residents/{userRFID}")
    public Resident getResident(@PathVariable String userRFID)
            throws ExecutionException, InterruptedException {
        return firebaseService.getResident(userRFID);
    }

    @PostMapping("/Residents/{userRFID}")
    public String addResident(@PathVariable String userRFID,
                              @RequestBody Resident resident)
            throws ExecutionException, InterruptedException, JsonProcessingException {
        return firebaseService.addResident(userRFID, resident);
    }

    @PutMapping("/Residents/{userRFID}")
    public String updateResident(@PathVariable String userRFID,
                                 @RequestBody Resident resident)
            throws ExecutionException, InterruptedException {
        return firebaseService.updateResident(userRFID, resident);
    }

    @DeleteMapping("/Residents/{userRFID}")
    public String deleteResident(@PathVariable String userRFID)
            throws ExecutionException, InterruptedException {
        return firebaseService.deleteResident(userRFID);
    }

    @GetMapping("/Residents/{userRFID}/Cases")
    public List<Case> getCases(@PathVariable String userRFID)
            throws ExecutionException, InterruptedException {
        return firebaseService.getCases(userRFID);
    }

    @GetMapping("/Residents/{userRFID}/Cases/{caseNumber}")
    public Case getCase(@PathVariable String userRFID,
                        @PathVariable String caseNumber)
            throws ExecutionException, InterruptedException {
        return firebaseService.getCase(userRFID, caseNumber);
    }

    @PostMapping("/Residents/{userRFID}/Cases/{caseNumber}")
    public String addCase(@PathVariable String userRFID,
                          @PathVariable String caseNumber,
                          @RequestBody Case aCase)
            throws ExecutionException, InterruptedException {
        return firebaseService.addCase(userRFID, aCase);
    }

    @GetMapping("/Officials")
    public List<Official> getOfficials(@RequestParam String parameterType,
                                     @RequestParam String parameterEntry,
                                     @RequestParam String barangay)
            throws ExecutionException, InterruptedException {
        return firebaseService
                .getOfficials(
                        barangay,
                        parameterType,
                        parameterEntry
                );
    }

    @GetMapping("/Officials/{userRFID}")
    public Official getOfficial(@PathVariable String userRFID,
                                @RequestParam String barangay)
            throws ExecutionException, InterruptedException {
        return firebaseService.getOfficial(barangay, userRFID);
    }

    @PostMapping("/Officials/{userRFID}")
    public String addOfficial(@PathVariable String userRFID,
                              @RequestBody Official official)
            throws ExecutionException, InterruptedException {
        return firebaseService.addOfficial(userRFID, official);
    }

    @PutMapping("/Officials/{userRFID}")
    public String updateOfficial(@PathVariable String userRFID,
                                 @RequestBody Official official)
            throws ExecutionException, InterruptedException {
        return firebaseService.updateOfficial(userRFID, official);
    }

    @DeleteMapping("/Officials/{userRFID}")
    public String deleteOfficial(@PathVariable String userRFID) {
        return firebaseService.deleteOfficial(userRFID);
    }

}
