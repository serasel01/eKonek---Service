package com.example.BarangayServices.services;

import com.example.BarangayServices.enums.Collection;
import com.example.BarangayServices.enums.ParameterType;
import com.example.BarangayServices.models.Case;
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
    private @NotNull DocumentReference getAdminReference(String barangay,
                                                         String userRFID){
        return FirestoreClient.getFirestore()
                .collection(Collection.Barangays.name())
                .document(barangay)
                .collection(Collection.Residents.name())
                .document(userRFID);
    }

    //returns database login reference
    private @NotNull DocumentReference getRFIDReference(String userRFID){
        return FirestoreClient.getFirestore().collection(Collection.RFIDs.name()).document(userRFID);
    }

    private Iterable<DocumentReference> getResidentsReference(String barangay){
        return FirestoreClient.getFirestore().collection(Collection.Barangays.name())
                .document(barangay).collection(Collection.Residents.name()).listDocuments();
    }

    private CollectionReference getResidentCollectionReference(String barangay){
        return FirestoreClient.getFirestore().collection(Collection.Barangays.name())
                .document(barangay).collection(Collection.Residents.name());
    }

    private CollectionReference getLogCollectionReference(String barangay){
        return FirestoreClient.getFirestore().collection(Collection.Barangays.name())
                .document(barangay).collection(Collection.Logs.name());
    }

    private Iterable<DocumentReference> getLogsReference(String barangay){
        return FirestoreClient.getFirestore().collection(Collection.Barangays.name())
                .document(barangay).collection(Collection.Logs.name()).listDocuments();
    }

    private @NotNull DocumentReference getLogReference(String barangay, long timestamp){
        return FirestoreClient.getFirestore()
                .collection(Collection.Barangays.name())
                .document(barangay)
                .collection(Collection.Logs.name())
                .document(String.valueOf(timestamp));
    }

    private Iterable<DocumentReference> getCasesReference(String barangay, String userRFID){
        return FirestoreClient.getFirestore().collection(Collection.Barangays.name())
                .document(barangay).collection(Collection.Residents.name())
                .document(userRFID).collection(Collection.Cases.name()).listDocuments();
    }

    private @NotNull DocumentReference getCaseReference(String barangay,
                                                        String userRFID,
                                                        String caseId){
        return FirestoreClient.getFirestore()
                .collection(Collection.Barangays.name())
                .document(barangay)
                .collection(Collection.Residents.name())
                .document(userRFID)
                .collection(Collection.Cases.name())
                .document(caseId);
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

    public List<Resident> searchResidents(String barangay,
                                          String parameterType,
                                          String parameterEntry)
            throws ExecutionException, InterruptedException {

        Query query;
        List<Resident> residentList = new ArrayList<>();
        String begin, end;

        switch (ParameterType.valueOf(parameterType)){
            case ResidentRFID:
                    query = getResidentCollectionReference(barangay)
                            .whereEqualTo("userRFID", parameterEntry);
                    break;

            case ResidentName:
                begin = parameterEntry.substring(0, parameterEntry.length()-1);
                end = parameterEntry.substring(parameterEntry.length()-1);

                query = getResidentCollectionReference(barangay)
                        .whereGreaterThanOrEqualTo("lastName", parameterEntry)
                        .whereLessThan("lastName",
                                begin + Character.toString(
                                        (int) end.charAt(0) + 1)
                            );
                    break;

            default:
                query = getResidentCollectionReference(barangay);
                break;
        }

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            residentList.add(document.toObject(Resident.class));
        }

        return residentList;
    }

    public List<Log> getLogs(String barangay,
                             String parameterType,
                             String parameterEntry)
            throws ExecutionException, InterruptedException {

        Query query;
        List<Log> logList = new ArrayList<>();
        String begin, end;

        switch (ParameterType.valueOf(parameterType)){
            case ResidentRFID:
                    query = getLogCollectionReference(barangay)
                            .whereEqualTo("residentRFID", parameterEntry);
                    break;

            case AdminRFID:
                    query = getLogCollectionReference(barangay)
                            .whereEqualTo("adminRFID", parameterEntry);
                    break;

            case Event:
                    query = getLogCollectionReference(barangay)
                            .whereEqualTo("event", parameterEntry);
                    break;

            case ResidentName:
                begin = parameterEntry.substring(0, parameterEntry.length()-1);
                end = parameterEntry.substring(parameterEntry.length()-1);

                query = getLogCollectionReference(barangay)
                        .whereGreaterThanOrEqualTo("residentName", parameterEntry)
                        .whereLessThan("residentName",
                                begin + Character.toString(
                                        (int) end.charAt(0) + 1)
                            );
                    break;

            case AdminName:
                begin = parameterEntry.substring(0, parameterEntry.length()-1);
                end = parameterEntry.substring(parameterEntry.length()-1);

                query = getLogCollectionReference(barangay)
                        .whereGreaterThanOrEqualTo("adminName", parameterEntry)
                        .whereLessThan("adminName",
                                begin + Character.toString(
                                        (int) end.charAt(0) + 1)
                        );
                break;

            case Timestamp:
                long timestamp = Long.parseLong(parameterEntry);

                query = getLogCollectionReference(barangay)
                        .whereGreaterThanOrEqualTo("timestamp", timestamp)
                        .whereLessThan("timestamp", timestamp + 86400000);
                break;

            default:
                query = getLogCollectionReference(barangay);
                break;
        }

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            logList.add(document.toObject(Log.class));
        }

        return logList;
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

    public List<Case> getCases(String barangay, String userRFID) throws ExecutionException, InterruptedException {
        Iterator<DocumentReference> iterator = getCasesReference(barangay, userRFID).iterator();
        List<Case> caseList = new ArrayList<>();

        while (iterator.hasNext()){
            ApiFuture<DocumentSnapshot> future = iterator.next().get();
            caseList.add(future.get().toObject(Case.class));
        }
        return caseList;
    }

    public String addResident(String barangay,
                              String userRFID,
                              Resident resident)
            throws ExecutionException, InterruptedException {

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

    public String addCase(String barangay,
                          String userRFID,
                          String caseId,
                          Case caseItem)
            throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result =
                getCaseReference(barangay, userRFID, caseId).set(caseItem);
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
