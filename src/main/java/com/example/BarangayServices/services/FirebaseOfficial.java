package com.example.BarangayServices.services;

import com.example.BarangayServices.enums.Collection;
import com.example.BarangayServices.enums.OfficialFilterParameter;
import com.example.BarangayServices.enums.ResidentFilterParameter;
import com.example.BarangayServices.models.Official;
import com.example.BarangayServices.models.Resident;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseOfficial
        extends FirebaseService
        implements ReferenceHandler{

    public FirebaseOfficial() {
    }


    @Override
    public ApiFuture<DocumentSnapshot> get() {
        return getDocumentReference(
                getUserRFID()).get();
    }

    @Override
    public List<?> getList() throws ExecutionException, InterruptedException {
        Query query;
        List<Official> officialList = new ArrayList<>();
        String begin, end;

        switch (OfficialFilterParameter.valueOf(getParameterType())) {
            case OfficialRFID -> query =
                    getCollectionReference()
                            .whereEqualTo(
                                    "userRFID",
                                    getParameterEntry()
                            ).whereEqualTo(
                                    "barangay",
                                    getBarangay()
                            );

            case FirstName -> {
                begin = getParameterEntry()
                        .substring(0, getParameterEntry().length() - 1);
                end = getParameterEntry()
                        .substring(getParameterEntry().length() - 1);
                query = getCollectionReference()
                        .whereGreaterThanOrEqualTo("firstName",
                                getParameterEntry())
                        .whereLessThan(
                                "firstName",
                                begin + Character
                                        .toString((int) end.charAt(0) + 1)
                        ).whereEqualTo(
                                "barangay",
                                getBarangay()
                        );
            }

            case LastName -> {
                begin = getParameterEntry()
                        .substring(0, getParameterEntry().length() - 1);
                end = getParameterEntry()
                        .substring(getParameterEntry().length() - 1);
                query = getCollectionReference()
                        .whereGreaterThanOrEqualTo("lastName",
                                getParameterEntry())
                        .whereLessThan("lastName",
                                begin + Character.toString(
                                        (int) end.charAt(0) + 1)
                        ).whereEqualTo(
                                "barangay",
                                getBarangay()
                        );
            }

            default -> query = getCollectionReference()
                    .whereEqualTo(
                            "barangay",
                            getBarangay()
                    );

        }

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            officialList.add(document.toObject(Official.class));
        }

        return officialList;
    }

    @Override
    public String add() throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result =
                getDocumentReference(getUserRFID())
                        .set(getOfficial());
        return "Update time : " + result.get().getUpdateTime();
    }

    @Override
    public String update() throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result =
                getDocumentReference(getUserRFID())
                        .set(getOfficial());
        return "Update time : " + result.get().getUpdateTime();
    }

    @Override
    public String delete() {
        getDocumentReference(getUserRFID()).delete();
        return "Barangay Official " + getUserRFID()
                + " has been deleted successfully.";
    }

    @Override
    public DocumentReference getDocumentReference(String userRFID) {
        return FirestoreClient
                .getFirestore()
                .collection(Collection.Officials.name())
                .document(userRFID);
    }

    @Override
    public CollectionReference getCollectionReference() {
        return FirestoreClient
                .getFirestore()
                .collection(Collection.Officials.name());
    }

    @Override
    public DocumentReference getDocumentReference(String id, String caseNumber) {
        return null;
    }

    @Override
    public CollectionReference getCollectionReference(String id) {
        return null;
    }
}
