package com.example.BarangayServices.services;

import com.example.BarangayServices.models.Official;
import com.example.BarangayServices.models.Case;
import com.example.BarangayServices.models.Resident;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class FirebaseService {
    private String barangay;
    private String userRFID;
    private String caseNumber;
    private String parameterType;
    private String parameterEntry;

    private Resident resident;
    private Official official;
    private Case aCase;

    public String getBarangay() {
        return barangay;
    }

    public FirebaseService setBarangay(String barangay) {
        this.barangay = barangay;
        return this;
    }

    public String getUserRFID() {
        return userRFID;
    }

    public FirebaseService setUserRFID(String userRFID) {
        this.userRFID = userRFID;
        return this;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public FirebaseService setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
        return this;
    }

    public String getParameterType() {
        return parameterType;
    }

    public FirebaseService setParameterType(String parameterType) {
        this.parameterType = parameterType;
        return this;
    }

    public String getParameterEntry() {
        return parameterEntry;
    }

    public FirebaseService setParameterEntry(String parameterEntry) {
        this.parameterEntry = parameterEntry;
        return this;
    }

    public Resident getResident() {
        return resident;
    }

    public FirebaseService setResident(Resident resident) {
        this.resident = resident;
        return this;
    }

    public Official getOfficial() {
        return official;
    }

    public FirebaseService setOfficial(Official official) {
        this.official = official;
        return this;
    }

    public Case getaCase() {
        return aCase;
    }

    public FirebaseService setaCase(Case aCase) {
        this.aCase = aCase;
        return this;
    }

    public abstract ApiFuture<DocumentSnapshot> get();
    public abstract List<?> getList() throws ExecutionException, InterruptedException;
    public abstract String add() throws ExecutionException, InterruptedException;
    public abstract String update() throws ExecutionException, InterruptedException;
    public abstract String delete();

}
