package com.backend.backend.controller;

import com.backend.backend.model.Panier;
import com.backend.backend.model.PanierItem;
import com.backend.backend.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    @Autowired
    private PanierService panierService;

    // Créer un nouveau panier
    @PostMapping
    public Panier createPanier(@RequestBody Panier newPanier) {
        return panierService.createPanier(newPanier);
    }

    // Ajouter un item dans un panier
    @PostMapping("/{id}/items")
    public Panier addItemToPanier(@PathVariable String id, @RequestBody PanierItem newItem) {
        return panierService.addItemToPanier(id, newItem);
    }

    // Récupérer un panier par ID
    @GetMapping("/{id}")
    public Panier getPanierById(@PathVariable String id) {
        return panierService.getPanierById(id);
    }

    // Supprimer un item du panier
    @DeleteMapping("/{id}/items/{menuItemId}")
    public Panier removeItemFromPanier(@PathVariable String id, @PathVariable String menuItemId) {
        return panierService.removeItemFromPanier(id, menuItemId);
    }

    // Vider un panier
    @DeleteMapping("/{id}/items")
    public Panier clearPanier(@PathVariable String id) {
        return panierService.clearPanier(id);
    }
    @PutMapping("/{id}/items/{menuItemId}")
    public Panier updateItemQuantity(@PathVariable String id, @PathVariable String menuItemId, @RequestParam int quantity) {
        return panierService.updateItemQuantity(id, menuItemId, quantity);
    }

}
