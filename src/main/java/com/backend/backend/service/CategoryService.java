package com.backend.backend.service;

import com.backend.backend.model.Category;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "categories";

    @Autowired
    public CategoryService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Récupérer toutes les catégories
    public List<Category> getAllCategories() {
        CollectionReference categories = firestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> query = categories.get();
        List<Category> categoryList = new ArrayList<>();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                categoryList.add(doc.toObject(Category.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    // Récupérer une catégorie par ID
    public Category getCategoryById(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.toObject(Category.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Créer une nouvelle catégorie
    public Category createCategory(Category category) {
        try {
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
            category.setIdCategorie(docRef.getId());
            docRef.set(category).get();
            return category;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mettre à jour une catégorie
    public Category updateCategory(String id, Category categoryDetails) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        try {
            docRef.update(
                    "categorie", categoryDetails.getCategorie(),
                    "etat", categoryDetails.getEtat(),
                    "img", categoryDetails.getImg()
            ).get();
            return categoryDetails;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Supprimer une catégorie
    public void deleteCategory(String id) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).delete().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
