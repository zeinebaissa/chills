package com.backend.backend.model;

public class MenuItem {
    private String idItem;
    private String nom;
    private String description;
    private double prix;
    private String image;
    private String categoryId;

    // Constructeurs
    public MenuItem() {}

    public MenuItem(String idItem, String nom, String description, double prix, String image, String categoryId) {
        this.idItem = idItem;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.categoryId = categoryId;
    }

    // Getters et Setters
    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
