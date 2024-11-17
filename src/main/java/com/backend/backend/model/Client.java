package com.backend.backend.model;

import java.util.List;

public class Client {

    private String id;  // Identifiant unique du client
    private String nom;  // Nom du client
    private String email;  // Email du client
    private String motDePasse;  // Mot de passe du client (idéalement stocké sous forme de hachage)
    private String tel;  // Numéro de téléphone du client
    private String imgClient;  // URL de l'image du client

    // Constructeurs
    public Client() {
    }

    public Client(String id, String nom, String email, String motDePasse, String tel, String imgClient, List<Avis> avis) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.tel = tel;
        this.imgClient = imgClient;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImgClient() {
        return imgClient;
    }

    public void setImgClient(String imgClient) {
        this.imgClient = imgClient;
    }


}
