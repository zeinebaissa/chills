package com.backend.backend.model;

public class Restaurant {

    private String idResto;  // Firebase uses a String as ID (you can use Firebase auto-generated IDs)
    private String localisationRestau;
    private String etatResto; // "open" or "close"

    // Default constructor
    public Restaurant() {}

    // Constructor
    public Restaurant(String idResto, String localisationRestau, String etatResto) {
        this.idResto = idResto;
        this.localisationRestau = localisationRestau;
        this.etatResto = etatResto;
    }

    // Getter and Setter for idResto
    public String getIdResto() {
        return idResto;
    }

    public void setIdResto(String idResto) {
        this.idResto = idResto;
    }

    // Getter and Setter for localisationRestau
    public String getLocalisationRestau() {
        return localisationRestau;
    }

    public void setLocalisationRestau(String localisationRestau) {
        this.localisationRestau = localisationRestau;
    }

    // Getter and Setter for etatResto
    public String getEtatResto() {
        return etatResto;
    }

    public void setEtatResto(String etatResto) {
        this.etatResto = etatResto;
    }
}
