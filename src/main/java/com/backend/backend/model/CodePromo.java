package com.backend.backend.model;

import com.google.cloud.firestore.annotation.DocumentId;

import java.time.LocalDate;
import java.util.Date;

public class CodePromo {

    @DocumentId
    private String idCode; // Firestore génère automatiquement un ID unique pour chaque document.

    private String code;
    private double reduction;
    private Date dateExpiration; // Utilisation de java.util.Date, compatible avec Firestore.

    // Constructeurs
    public CodePromo() {}

    public CodePromo(String code, double reduction, Date dateExpiration) {
        this.code = code;
        this.reduction = reduction;
        this.dateExpiration = dateExpiration;
    }

    // Getters et Setters
    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}
