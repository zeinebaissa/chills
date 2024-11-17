package com.backend.backend.service;

import com.backend.backend.model.Commande;
import com.backend.backend.model.Panier;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CommandeService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "commandes";

    @Autowired
    public CommandeService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Créer une commande à partir d'un panier
    public Commande createCommandeFromPanier(String panierId, Commande newCommande) {
        DocumentReference panierRef = firestore.collection("paniers").document(panierId);
        DocumentReference commandeRef = firestore.collection(COLLECTION_NAME).document();

        try {
            // Récupérer le panier
            DocumentSnapshot panierDoc = panierRef.get().get();
            if (!panierDoc.exists()) {
                throw new RuntimeException("Panier introuvable avec l'ID : " + panierId);
            }

            Panier panier = panierDoc.toObject(Panier.class);

            // Vérifier que le panier n'est pas vide
            if (panier.getItems() == null || panier.getItems().isEmpty()) {
                throw new RuntimeException("Le panier est vide. Impossible de créer une commande.");
            }

            // Remplir la commande avec les données du panier
            newCommande.setIdCommande(commandeRef.getId());
            newCommande.setClientId(panier.getClientId());
            newCommande.setItems(panier.getItems());
            newCommande.setTotal(panier.getTotal());

            // Enregistrer la commande
            commandeRef.set(newCommande).get();

            // Optionnel : Vider le panier après avoir créé la commande
            panier.getItems().clear();
            panier.setTotal(0);
            panierRef.set(panier).get();

            return newCommande;

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la création de la commande à partir du panier : " + e.getMessage(), e);
        }
    }

    // Récupérer une commande par ID
    public Commande getCommandeById(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        try {
            DocumentSnapshot document = docRef.get().get();
            if (!document.exists()) {
                throw new RuntimeException("Commande introuvable avec l'ID : " + id);
            }
            return document.toObject(Commande.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la récupération de la commande : " + e.getMessage(), e);
        }
    }

    // Mettre à jour une commande
    public Commande updateCommande(String id, Commande updatedCommande) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        try {
            docRef.set(updatedCommande).get();
            return updatedCommande;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la commande : " + e.getMessage(), e);
        }
    }

    // Supprimer une commande
    public void deleteCommande(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        try {
            docRef.delete().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la suppression de la commande : " + e.getMessage(), e);
        }
    }
}
