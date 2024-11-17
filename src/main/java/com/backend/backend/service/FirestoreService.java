package com.backend.backend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    private static final String COLLECTION_NAME = "testCollection";

    // Method to add data to Firestore
    public String addData(String documentId, String name, String value) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference = db.collection(COLLECTION_NAME).document(documentId);

        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("value", value);

        ApiFuture<WriteResult> result = documentReference.set(data);
        return "Update time: " + result.get().getUpdateTime();
    }

    // Method to retrieve data from Firestore
    public Map<String, Object> getData(String documentId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference = db.collection(COLLECTION_NAME).document(documentId);

        ApiFuture<com.google.cloud.firestore.DocumentSnapshot> future = documentReference.get();
        return future.get().getData();
    }
}
