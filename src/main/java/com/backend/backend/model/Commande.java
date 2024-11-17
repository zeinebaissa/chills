package com.backend.backend.model;

import java.util.List;

public class Commande {
    private String idCommande;          // Identifiant unique de la commande
    private String clientId;            // Référence à l'ID du client
    private List<PanierItem> items;     // Liste des items dans la commande
    private double total;               // Total de la commande
    private String localisationResto;   // Adresse du restaurant
    private String etat;                // Statut de la commande (ex. "en préparation")
    private String typeCommande;        // Type de commande (ex. "pickup")
    private String commentaire;         // Commentaire de la commande
    private String adresseLivraison;    // Adresse de livraison, si applicable

    // Constructeurs
    public Commande() {}

    public Commande(String idCommande, String clientId, List<PanierItem> items, double total,
                    String localisationResto, String etat, String typeCommande, String commentaire,
                    String adresseLivraison) {
        this.idCommande = idCommande;
        this.clientId = clientId;
        this.items = items;
        this.total = total;
        this.localisationResto = localisationResto;
        this.etat = etat;
        this.typeCommande = typeCommande;
        this.commentaire = commentaire;
        this.adresseLivraison = adresseLivraison;
    }

    // Getters et Setters
    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<PanierItem> getItems() {
        return items;
    }

    public void setItems(List<PanierItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getLocalisationResto() {
        return localisationResto;
    }

    public void setLocalisationResto(String localisationResto) {
        this.localisationResto = localisationResto;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTypeCommande() {
        return typeCommande;
    }

    public void setTypeCommande(String typeCommande) {
        this.typeCommande = typeCommande;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }
}
