package com.backend.backend.model;

public class Avis {

    private String idAvis;         // Identifiant unique de l'avis
    private String clientId;       // Référence à l'ID du client
    private String commentaire;    // Commentaire laissé par le client
    private int note;              // Note donnée (par exemple, entre 1 et 5)
    private boolean validation;    // Statut de validation de l'avis

    // Constructeurs
    public Avis() {}

    public Avis(String idAvis, String clientId, String commentaire, int note, boolean validation) {
        this.idAvis = idAvis;
        this.clientId = clientId;
        this.commentaire = commentaire;
        this.note = note;
        this.validation = validation;
    }

    // Getters et Setters
    public String getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(String idAvis) {
        this.idAvis = idAvis;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }
}
