package com.backend.backend.model;

public class Category {
    private String idCategorie;
    private String categorie;
    private String etat;
    private String img;

    // Constructeurs
    public Category() {}

    public Category(String idCategorie, String categorie, String etat, String img) {
        this.idCategorie = idCategorie;
        this.categorie = categorie;
        this.etat = etat;
        this.img = img;
    }

    // Getters et Setters
    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
