package com.backend.backend.service;

import com.backend.backend.model.MenuItem;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class MenuItemService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "menuItems";

    @Autowired
    public MenuItemService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Récupérer tous les MenuItems
    public List<MenuItem> getAllMenuItems() {
        CollectionReference menuItems = firestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> query = menuItems.get();
        List<MenuItem> menuItemList = new ArrayList<>();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                menuItemList.add(doc.toObject(MenuItem.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return menuItemList;
    }

    // Récupérer un MenuItem par ID
    public MenuItem getMenuItemById(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.toObject(MenuItem.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Créer un nouveau MenuItem
    public MenuItem createMenuItem(MenuItem menuItem) {
        try {
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
            menuItem.setIdItem(docRef.getId());
            docRef.set(menuItem).get();
            return menuItem;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mettre à jour un MenuItem
    public MenuItem updateMenuItem(String id, MenuItem menuItemDetails) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        try {
            docRef.update(
                    "nom", menuItemDetails.getNom(),
                    "description", menuItemDetails.getDescription(),
                    "prix", menuItemDetails.getPrix(),
                    "image", menuItemDetails.getImage(),
                    "categoryId", menuItemDetails.getCategoryId()
            ).get();
            return menuItemDetails;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Supprimer un MenuItem
    public void deleteMenuItem(String id) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).delete().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
