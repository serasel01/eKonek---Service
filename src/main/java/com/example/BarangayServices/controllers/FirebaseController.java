package com.example.BarangayServices.controllers;

import com.example.BarangayServices.models.Log;
import com.example.BarangayServices.models.RFID;
import com.example.BarangayServices.models.Resident;
import com.example.BarangayServices.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class FirebaseController {

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping("/Barangays/{barangay}/Residents/{userRFID}")
    public Resident getResident(@PathVariable String barangay, @PathVariable String userRFID)
            throws ExecutionException, InterruptedException {
        return firebaseService.getResident(barangay, userRFID);
    }

    @GetMapping("/Barangays/{barangay}/Residents")
    public List<Resident> getResidents(@PathVariable String barangay)
            throws ExecutionException, InterruptedException {
        return firebaseService.getResidents(barangay);
    }

    @GetMapping("/Barangays/{barangay}/Logs")
    public List<Log> getLogs(@PathVariable String barangay)
            throws ExecutionException, InterruptedException {
        return firebaseService.getLogs(barangay);
    }

    @GetMapping("/RFIDs/{userRFID}")
    public RFID getLoginCreds(@PathVariable String userRFID)
            throws ExecutionException, InterruptedException {
        return firebaseService.getLoginCreds(userRFID);
    }

    @PostMapping("/Barangays/{barangay}/Residents")
    public List<Resident> searchResidents(@PathVariable String barangay,
                                       @RequestParam String param)
            throws ExecutionException, InterruptedException {
        return firebaseService.searchResidents(barangay, param);
    }

    @PostMapping("/Barangays/{barangay}/Residents/{userRFID}")
    public String addResident(@PathVariable String barangay, @PathVariable String userRFID,
                              @RequestBody Resident resident)
            throws  ExecutionException, InterruptedException {
        return firebaseService.addResident(barangay, userRFID, resident);
    }

    @PostMapping("/RFIDs/{userRFID}")
    public String addLoginCreds(@PathVariable String userRFID, @RequestBody RFID RFID)
            throws ExecutionException, InterruptedException {
        return firebaseService.addLoginCreds(userRFID, RFID);
    }

    @PostMapping("/Barangays/{barangay}/Logs/{timestamp}")
    public String addLog(@PathVariable String barangay,
                          @RequestBody Log log)
            throws  ExecutionException, InterruptedException {
        return firebaseService.addLog(barangay, log);
    }

    @PutMapping("/Barangays/{barangay}/Residents/{userRFID}")
    public String updateResident(@PathVariable String barangay, @PathVariable String userRFID,
                                   @RequestBody Resident resident)
            throws ExecutionException, InterruptedException {
        return firebaseService.updateResident(barangay, userRFID, resident);
    }

    @PutMapping("/RFIDs/{userRFID}")
    public String updateLoginCreds(@PathVariable String userRFID, @RequestBody RFID RFID)
            throws ExecutionException, InterruptedException {
        return firebaseService.updateLoginCreds(userRFID, RFID);
    }

    @DeleteMapping("/Barangays/{barangay}/Residents/{userRFID}")
    public String deleteResident(@PathVariable String barangay, @PathVariable String userRFID)
            throws ExecutionException, InterruptedException {
        return firebaseService.deleteResident(barangay, userRFID);
    }

    @DeleteMapping("/RFIDs/{userRFID}")
    public String deleteAdminCreds(@PathVariable String userRFID)
            throws ExecutionException, InterruptedException {
        return firebaseService.deleteAdminCreds(userRFID);
    }

}
