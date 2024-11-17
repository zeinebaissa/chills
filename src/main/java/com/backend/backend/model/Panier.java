package com.backend.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private String id;          // Identifiant unique du Panier
    private String clientId;    // Référence au client
    private List<PanierItem> items; // Liste des items dans le panier
    private double total;       // Total du panier

    // Constructeurs
    public Panier() {
        this.items = new ArrayList<>();
        this.total = 0;
    }

    public Panier(String id, String clientId) {
        this.id = id;
        this.clientId = clientId;
        this.items = new ArrayList<>();
        this.total = 0;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public List<PanierItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setItems(List<PanierItem> items) {
        this.items = items;
        updateTotal(); // Mise à jour automatique du total
    }

    // Ajouter un item dans le panier
    public void addItem(PanierItem newItem) {
        boolean exists = false;
        for (PanierItem item : items) {
            if (item.getMenuItemId().equals(newItem.getMenuItemId())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                exists = true;
                break;
            }
        }
        if (!exists) {
            this.items.add(newItem);
        }
        updateTotal();
    }

    // Supprimer un item du panier
    public void removeItem(String menuItemId) {
        items.removeIf(item -> item.getMenuItemId().equals(menuItemId));
        updateTotal();
    }

    // Mettre à jour le total
    public void updateTotal() {
        this.total = items.stream().mapToDouble(PanierItem::getSubTotal).sum();
    }
    public void setTotal(double total) {
        this.total = total;
    }


}
