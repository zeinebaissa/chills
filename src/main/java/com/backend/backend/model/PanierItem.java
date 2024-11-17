package com.backend.backend.model;

public class PanierItem {
    private String menuItemId;  // Référence au MenuItem
    private String nom;         // Nom du MenuItem
    private double prixUnitaire; // Prix unitaire
    private int quantity;       // Quantité
    private double subTotal;    // Sous-total (prixUnitaire * quantity)

    // Constructeurs
    public PanierItem() {}

    public PanierItem(String menuItemId, String nom, double prixUnitaire, int quantity) {
        this.menuItemId = menuItemId;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.quantity = quantity;
        this.subTotal = prixUnitaire * quantity; // Calcul automatique
    }

    // Getters et setters
    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        updateSubTotal(); // Mise à jour automatique du sous-total
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateSubTotal(); // Mise à jour automatique du sous-total
    }

    public double getSubTotal() {
        return subTotal;
    }

    private void updateSubTotal() {
        this.subTotal = this.prixUnitaire * this.quantity;
    }
}
