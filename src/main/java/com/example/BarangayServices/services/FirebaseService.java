package com.example.BarangayServices.services;

import com.example.BarangayServices.enums.Collections;
import com.example.BarangayServices.models.Log;
import com.example.BarangayServices.models.RFID;
import com.example.BarangayServices.models.Resident;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    //returns admin database reference
    private @NotNull DocumentReference getAdminReference(String barangay, String userRFID){
        return FirestoreClient.getFirestore().collection(Collections.Barangays.name())
                .document(barangay).collection(Collections.Residents.name()).document(userRFID);
    }

    //returns database login reference
    private @NotNull DocumentReference getRFIDReference(String userRFID){
        return FirestoreClient.getFirestore().collection(Collections.RFIDs.name()).document(userRFID);
    }

    private Iterable<DocumentReference> getResidentsReference(String barangay){
        return FirestoreClient.getFirestore().collection(Collections.Barangays.name())
                .document(barangay).collection(Collections.Residents.name()).listDocuments();
    }

    private CollectionReference getResidentCollectionReference(String barangay){
        return FirestoreClient.getFirestore().collection(Collections.Barangays.name())
                .document(barangay).collection(Collections.Residents.name());
    }

    private Iterable<DocumentReference> getLogsReference(String barangay){
        return FirestoreClient.getFirestore().collection(Collections.Barangays.name())
                .document(barangay).collection(Collections.Logs.name()).listDocuments();
    }

    private @NotNull DocumentReference getLogReference(String barangay, long timestamp){
        return FirestoreClient.getFirestore()
                .collection(Collections.Barangays.name())
                .document(barangay)
                .collection(Collections.Logs.name())
                .document(String.valueOf(timestamp));
    }

    //returns admin details for login
    private @Nullable RFID getLoginDocument(@NotNull ApiFuture<DocumentSnapshot> future)
            throws ExecutionException, InterruptedException {
        DocumentSnapshot document = future.get();
        if (document.exists()){
            return document.toObject(RFID.class);
        } else {
            return null;
        }
    }

    //returns admin details for login
    private @Nullable Resident getUserDocument(@NotNull ApiFuture<DocumentSnapshot> future)
            throws ExecutionException, InterruptedException {
        DocumentSnapshot document = future.get();
        if (document.exists()){
            return document.toObject(Resident.class);
        } else {
            return null;
        }
    }

    //called by FirebaseController to get admin details from db and print in localhost
    public Resident getResident(String barangay, String userRFID) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = getAdminReference(barangay, userRFID).get();
        return getUserDocument(future);
    }

    //called by FirebaseController to get login credentials from db and print in localhost
    public RFID getLoginCreds(String userRFID) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = getRFIDReference(userRFID).get();
        return getLoginDocument(future);
    }

    //called by FirebaseController to get admin details from db and print in localhost
    public List<Resident> getResidents(String barangay) throws ExecutionException, InterruptedException {
        Iterator<DocumentReference> iterator = getResidentsReference(barangay).iterator();
        List<Resident> residentList = new ArrayList<>();

        while (iterator.hasNext()){
            ApiFuture<DocumentSnapshot> future = iterator.next().get();
            residentList.add(future.get().toObject(Resident.class));
        }
        return residentList;
    }

    public List<Resident> searchResidents(String barangay, String param)
            throws ExecutionException, InterruptedException {

        Query query;
        List<Resident> residentList = new ArrayList<>();

        //condition if RFID is the parameter passed
        if (Character.isDigit(param.charAt(1))){
            query = getResidentCollectionReference(barangay)
                    .whereEqualTo("userRFID", param);
        }

        //condition if surname is the parameter passed
        else {
            String begin = param.substring(0, param.length()-1);
            String end = param.substring(param.length()-1, param.length());

            query = getResidentCollectionReference(barangay)
                    .whereGreaterThanOrEqualTo("lastName", param)
                    .whereLessThan("lastName",
                            begin + Character.toString(
                                    (int) end.charAt(0) + 1)
                    );
        }

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            residentList.add(document.toObject(Resident.class));
        }

        return residentList;
    }

    public List<Log> getLogs(String barangay)
            throws ExecutionException, InterruptedException {
        Iterator<DocumentReference> iterator = getLogsReference(barangay).iterator();
        List<Log> logList = new ArrayList<>();

        while (iterator.hasNext()){
            ApiFuture<DocumentSnapshot> future = iterator.next().get();
            logList.add(future.get().toObject(Log.class));
        }
        return logList;
    }

    public String addResident(String barangay, String userRFID, Resident resident) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = getAdminReference(barangay, userRFID).set(resident);
        return "Update time : " + result.get().getUpdateTime();
    }

    public String addLoginCreds(String userRFID, RFID rfid)
            throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = getRFIDReference(userRFID).set(rfid);
        return "Update time : " + result.get().getUpdateTime();
    }

    public String addLog(String barangay, Log log)
            throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result =
                getLogReference(barangay, log.getTimestamp()).set(log);
        return "Update time : " + result.get().getUpdateTime();
    }

    public String updateResident(String barangay, String userRFID, Resident resident)
            throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = getAdminReference(barangay, userRFID).set(resident);
        return "Update time : " + result.get().getUpdateTime();
    }

    public String updateLoginCreds(String userRFID, RFID rfid)
            throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = getRFIDReference(userRFID).set(rfid);
        return "Update time : " + result.get().getUpdateTime();
    }

    public String deleteResident(String barangay, String userRFID) {
        getAdminReference(barangay, userRFID).delete();
        return "Resident " + userRFID + " of Barangay " + barangay + " has been deleted successfully.";
    }

    public String deleteAdminCreds(String userRFID) {
        getRFIDReference(userRFID).delete();
        return userRFID + " has been deleted successfully.";
    }

}
