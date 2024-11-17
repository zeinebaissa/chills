package com.backend.backend.service;

import com.backend.backend.model.MenuItem;
import com.backend.backend.model.Panier;
import com.backend.backend.model.PanierItem;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class PanierService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "paniers";
    private final String COLLECTION_MENUITEM = "menuItems";


    @Autowired
    public PanierService(Firestore firestore) {
        this.firestore = firestore;
    }


    // Ajouter un item dans le panier
    public Panier addItemToPanier(String panierId, PanierItem newItem) {
        DocumentReference panierRef = firestore.collection(COLLECTION_NAME).document(panierId);
        DocumentReference menuItemRef = firestore.collection(COLLECTION_MENUITEM).document(newItem.getMenuItemId());

        try {
            // Récupérer les informations du MenuItem
            DocumentSnapshot menuItemDoc = menuItemRef.get().get();
            if (!menuItemDoc.exists()) {
                throw new RuntimeException("MenuItem introuvable avec l'ID : " + newItem.getMenuItemId());
            }

            MenuItem menuItem = menuItemDoc.toObject(MenuItem.class);
            if (menuItem == null) {
                throw new RuntimeException("Erreur lors de la conversion du MenuItem.");
            }

            // Charger le panier
            DocumentSnapshot panierDoc = panierRef.get().get();
            Panier panier;
            if (panierDoc.exists()) {
                panier = panierDoc.toObject(Panier.class);
            } else {
                throw new RuntimeException("Panier introuvable avec l'ID : " + panierId);
            }

            // Compléter les informations de PanierItem depuis MenuItem
            newItem.setNom(menuItem.getNom());
            newItem.setPrixUnitaire(menuItem.getPrix());

            // Ajouter ou mettre à jour l'item dans le panier
            panier.addItem(newItem);

            // Enregistrer les modifications
            panierRef.set(panier).get();
            return panier;

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'item au panier : " + e.getMessage(), e);
        }
    }





    // Créer un panier
    public Panier createPanier(Panier newPanier) {
        DocumentReference panierRef = firestore.collection(COLLECTION_NAME).document();
        newPanier.setId(panierRef.getId());
        newPanier.setItems(new ArrayList<>());
        try {
            panierRef.set(newPanier).get();
            return newPanier;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la création du panier : " + e.getMessage(), e);
        }
    }



    // Récupérer un panier par ID
    public Panier getPanierById(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        try {
            DocumentSnapshot document = docRef.get().get();
            if (!document.exists()) throw new RuntimeException("Panier introuvable avec l'ID : " + id);
            return document.toObject(Panier.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la récupération du panier : " + e.getMessage(), e);
        }
    }

    // Supprimer un item du panier
    public Panier removeItemFromPanier(String panierId, String menuItemId) {
        DocumentReference panierRef = firestore.collection(COLLECTION_NAME).document(panierId);
        try {
            DocumentSnapshot document = panierRef.get().get();
            if (!document.exists()) throw new RuntimeException("Panier introuvable avec l'ID : " + panierId);

            Panier panier = document.toObject(Panier.class);
            panier.removeItem(menuItemId);
            panierRef.set(panier).get();
            return panier;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'item : " + e.getMessage(), e);
        }
    }

    // Vider un panier
    public Panier clearPanier(String panierId) {
        DocumentReference panierRef = firestore.collection(COLLECTION_NAME).document(panierId);
        try {
            DocumentSnapshot document = panierRef.get().get();
            if (!document.exists()) throw new RuntimeException("Panier introuvable avec l'ID : " + panierId);

            Panier panier = document.toObject(Panier.class);
            panier.getItems().clear();
            panierRef.set(panier).get();
            return panier;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors du vidage du panier : " + e.getMessage(), e);
        }
    }


    public Panier updateItemQuantity(String panierId, String menuItemId, int quantity) {
        DocumentReference panierRef = firestore.collection(COLLECTION_NAME).document(panierId);

        try {
            DocumentSnapshot document = panierRef.get().get();
            if (!document.exists()) {
                throw new RuntimeException("Panier introuvable avec l'ID : " + panierId);
            }

            Panier panier = document.toObject(Panier.class);
            boolean itemExists = false;

            for (PanierItem item : panier.getItems()) {
                if (item.getMenuItemId().equals(menuItemId)) {
                    item.setQuantity(quantity); // Mettre à jour la quantité
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                throw new RuntimeException("Item introuvable dans le panier avec l'ID : " + menuItemId);
            }

            // Recalculer le total du panier
            panier.updateTotal();

            // Sauvegarder le panier avec les modifications
            panierRef.set(panier).get();
            return panier;

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la quantité : " + e.getMessage(), e);
        }
    }

}
