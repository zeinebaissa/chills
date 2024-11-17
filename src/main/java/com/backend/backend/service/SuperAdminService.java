package com.backend.backend.service;

import com.backend.backend.model.SuperAdmin;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SuperAdminService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "superAdmins";  // Nom de la collection Firestore

    @Autowired
    public SuperAdminService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Get all SuperAdmins
    public List<SuperAdmin> getAllSuperAdmins() {
        CollectionReference superAdmins = firestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> query = superAdmins.get();
        List<SuperAdmin> superAdminList = new ArrayList<>();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                superAdminList.add(doc.toObject(SuperAdmin.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return superAdminList;
    }

    // Get SuperAdmin by ID
    public SuperAdmin getSuperAdminById(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.toObject(SuperAdmin.class);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create a new SuperAdmin
    public SuperAdmin saveSuperAdmin(SuperAdmin superAdmin) {
        try {
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
            superAdmin.setIdSuperAdmin(docRef.getId()); // Set Firestore auto-generated ID
            docRef.set(superAdmin).get();  // Wait for the write operation to complete
            return superAdmin;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update SuperAdmin
    public SuperAdmin updateSuperAdmin(String id, SuperAdmin superAdminDetails) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> future = docRef.update(
                "nom", superAdminDetails.getNom(),
                "email", superAdminDetails.getEmail(),
                "mot_de_passe", superAdminDetails.getMotDePasse()
        );
        try {
            future.get();  // Wait for the update to complete
            return superAdminDetails;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete SuperAdmin
    public void deleteSuperAdmin(String id) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).delete().get();  // Wait for operation to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
