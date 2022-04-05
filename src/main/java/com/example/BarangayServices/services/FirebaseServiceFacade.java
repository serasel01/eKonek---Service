package com.example.BarangayServices.services;

import com.example.BarangayServices.models.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseServiceFacade {
    private FirebaseService firebaseResident;
    private FirebaseService firebaseOfficial;
    private FirebaseService firebaseCase;

    public FirebaseServiceFacade() {
        firebaseResident = new FirebaseResident();
        firebaseOfficial = new FirebaseOfficial();
        firebaseCase = new FirebaseCase();
    }

    public List<Resident> getResidents(String barangay,
                                       String parameterType,
                                       String parameterEntry)
            throws ExecutionException, InterruptedException {

        return (List<Resident>) firebaseResident
                .setBarangay(barangay)
                .setParameterType(parameterType)
                .setParameterEntry(parameterEntry)
                .getList();
    }

    public Resident getResident(String userRFID)
            throws ExecutionException,
            InterruptedException {

        return getResidentDocument(
                firebaseResident
                .setUserRFID(userRFID)
                .get()
        );
    }

    public String addResident(String userRFID,
                              Resident resident)
            throws ExecutionException,
            InterruptedException {

        return firebaseResident
                .setUserRFID(userRFID)
                .setResident(resident)
                .add();
    }

    public String updateResident(String userRFID,
                                 Resident resident)
            throws ExecutionException,
            InterruptedException {

        return firebaseResident
                .setUserRFID(userRFID)
                .setResident(resident)
                .update();
    }

    public String deleteResident(String userRFID){

        return firebaseResident
                .setUserRFID(userRFID)
                .delete();
    }

    public List<Official> getOfficials(String barangay,
                                     String parameterType,
                                     String parameterEntry)
            throws ExecutionException, InterruptedException {

        return (List<Official>) firebaseOfficial
                .setBarangay(barangay)
                .setParameterType(parameterType)
                .setParameterEntry(parameterEntry)
                .getList();
    }

    public Official getOfficial(String barangay,
                                String userRFID)
            throws ExecutionException, InterruptedException {

        return getOfficialDocument(
                firebaseOfficial
                        .setBarangay(barangay)
                        .setUserRFID(userRFID)
                        .get()
        );
    }

    public String addOfficial(String userRFID,
                              Official official)
            throws ExecutionException,
            InterruptedException {

        return firebaseOfficial
                .setUserRFID(userRFID)
                .setOfficial(official)
                .add();
    }

    public String updateOfficial(String userRFID,
                                 Official official)
            throws ExecutionException,
            InterruptedException {

        return firebaseOfficial
                .setUserRFID(userRFID)
                .setOfficial(official)
                .update();
    }

    public String deleteOfficial(String userRFID){

        return firebaseOfficial
                .setUserRFID(userRFID)
                .delete();
    }

    public List<Case> getCases(String userRFID)
            throws ExecutionException, InterruptedException {

        return (List<Case>) firebaseCase
                .setUserRFID(userRFID)
                .getList();
    }

    public Case getCase(String userRFID,
                        String caseNumber)
            throws ExecutionException,
            InterruptedException {

        return getCaseDocument(
                firebaseCase
                        .setUserRFID(userRFID)
                        .setCaseNumber(caseNumber)
                        .get()
        );
    }

    public String addCase(String userRFID,
                          Case aCase)
            throws ExecutionException,
            InterruptedException {

        return firebaseCase
                .setUserRFID(userRFID)
                .setaCase(aCase)
                .add();
    }

    public @Nullable Resident getResidentDocument(@NotNull ApiFuture<DocumentSnapshot> future)
            throws ExecutionException, InterruptedException {
        DocumentSnapshot document = future.get();
        if (document.exists()){
            return document.toObject(Resident.class);
        } else {
            return null;
        }
    }

    public @Nullable Official getOfficialDocument(@NotNull ApiFuture<DocumentSnapshot> future)
            throws ExecutionException, InterruptedException {
        DocumentSnapshot document = future.get();
        if (document.exists()){
            return document.toObject(Official.class);
        } else {
            return null;
        }
    }

    public @Nullable Case getCaseDocument(@NotNull ApiFuture<DocumentSnapshot> future)
            throws ExecutionException, InterruptedException {
        DocumentSnapshot document = future.get();
        if (document.exists()){
            return document.toObject(Case.class);
        } else {
            return null;
        }
    }



}
