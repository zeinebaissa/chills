package com.backend.backend.service;

import com.backend.backend.model.CodePromo;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CodePromoService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "codePromos";

    @Autowired
    public CodePromoService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Récupérer tous les codes promo
    public List<CodePromo> getAllCodePromos() {
        CollectionReference codePromos = firestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> query = codePromos.get();
        List<CodePromo> codePromoList = new ArrayList<>();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                codePromoList.add(doc.toObject(CodePromo.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return codePromoList;
    }

    // Récupérer un code promo par ID
    public CodePromo getCodePromoById(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.toObject(CodePromo.class);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Créer un nouveau code promo
    public CodePromo saveCodePromo(CodePromo codePromo) {
        try {
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
            codePromo.setIdCode(docRef.getId()); // Définir un ID généré par Firestore
            docRef.set(codePromo).get();  // Attendre la fin de l'opération
            return codePromo;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mettre à jour un code promo existant
    public CodePromo updateCodePromo(String id, CodePromo codePromoDetails) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> future = docRef.update(
                "code", codePromoDetails.getCode(),
                "reduction", codePromoDetails.getReduction(),
                "dateExpiration", codePromoDetails.getDateExpiration()
        );
        try {
            future.get();  // Attendre la fin de l'opération
            return codePromoDetails;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Supprimer un code promo
    public void deleteCodePromo(String id) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).delete().get();  // Attendre la fin de l'opération
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
