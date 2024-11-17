package com.backend.backend.model;

public class Banner {
    private String idBanner;
    private String image;
    private boolean etatBanner;

    // Default constructor (obligatoire pour Firebase)
    public Banner() {}

    public Banner(String idBanner, String image, boolean etatBanner) {
        this.idBanner = idBanner;
        this.image = image;
        this.etatBanner = etatBanner;
    }

    public String getIdBanner() {
        return idBanner;
    }

    public void setIdBanner(String idBanner) {
        this.idBanner = idBanner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEtatBanner() {
        return etatBanner;
    }

    public void setEtatBanner(boolean etatBanner) {
        this.etatBanner = etatBanner;
    }
}
