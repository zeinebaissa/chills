package com.backend.backend.service;

import com.backend.backend.model.Avis;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AvisService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "avis";

    @Autowired
    public AvisService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Ajouter un nouvel avis
    public Avis createAvis(Avis avis) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
        avis.setIdAvis(docRef.getId());
        try {
            docRef.set(avis).get();
            return avis;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la création de l'avis : " + e.getMessage(), e);
        }
    }

    // Récupérer tous les avis pour un client spécifique
    public List<Avis> getAvisByClientId(String clientId) {
        CollectionReference collection = firestore.collection(COLLECTION_NAME);
        Query query = collection.whereEqualTo("clientId", clientId);
        try {
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<Avis> avisList = new ArrayList<>();
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                avisList.add(document.toObject(Avis.class));
            }
            return avisList;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la récupération des avis : " + e.getMessage(), e);
        }
    }

    // Récupérer un avis par ID
    public Avis getAvisById(String idAvis) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(idAvis);
        try {
            DocumentSnapshot document = docRef.get().get();
            if (!document.exists()) {
                throw new RuntimeException("Avis introuvable avec l'ID : " + idAvis);
            }
            return document.toObject(Avis.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'avis : " + e.getMessage(), e);
        }
    }

    // Mettre à jour un avis
    public Avis updateAvis(String idAvis, Avis updatedAvis) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(idAvis);
        try {
            docRef.set(updatedAvis).get();
            return updatedAvis;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'avis : " + e.getMessage(), e);
        }
    }

    // Supprimer un avis
    public void deleteAvis(String idAvis) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(idAvis);
        try {
            docRef.delete().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'avis : " + e.getMessage(), e);
        }
    }
}
