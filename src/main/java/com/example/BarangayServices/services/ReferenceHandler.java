package com.example.BarangayServices.services;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

public interface ReferenceHandler {
    DocumentReference getDocumentReference(String id);
    CollectionReference getCollectionReference();
    DocumentReference getDocumentReference(String id, String caseNumber);
    CollectionReference getCollectionReference(String id);
}
