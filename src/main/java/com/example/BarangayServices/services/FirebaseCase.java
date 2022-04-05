package com.example.BarangayServices.services;

import com.example.BarangayServices.enums.Collection;
import com.example.BarangayServices.models.Case;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseCase extends FirebaseService implements ReferenceHandler{
    @Override
    public ApiFuture<DocumentSnapshot> get() {
        return getDocumentReference(
                getUserRFID(),
                getCaseNumber())
                .get();
    }

    @Override
    public List<?> getList() throws ExecutionException, InterruptedException {
        Query query = getCollectionReference(getUserRFID());
        List<Case> caseList = new ArrayList<>();

        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            caseList.add(document.toObject(Case.class));
        }

        return caseList;
    }

    @Override
    public String add() throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result =
                getDocumentReference(getUserRFID(),
                        getaCase().getCaseNumber())
                        .set(getaCase());
        return "Update time : " + result.get().getUpdateTime();
    }

    @Override
    public String update() throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result =
                getDocumentReference(getUserRFID(),
                        getaCase().getCaseNumber())
                        .set(getaCase());
        return "Update time : " + result.get().getUpdateTime();
    }

    @Override
    public String delete() {
        getDocumentReference(getUserRFID(),
                getaCase().getCaseNumber())
                .delete();
        return "Case " +getaCase().getCaseNumber() + " has been deleted successfully.";
    }

    @Override
    public DocumentReference getDocumentReference(String id) {
        return null;
    }

    @Override
    public CollectionReference getCollectionReference() {
        return null;
    }

    @Override
    public DocumentReference getDocumentReference(String userRFID,
                                                  String caseNumber) {
        return FirestoreClient
                .getFirestore()
                .collection(Collection.Residents.name())
                .document(userRFID)
                .collection(Collection.Cases.name())
                .document(caseNumber);
    }

    @Override
    public CollectionReference getCollectionReference(String userRFID) {
        return FirestoreClient
                .getFirestore()
                .collection(Collection.Residents.name())
                .document(userRFID)
                .collection(Collection.Cases.name());
    }
}
