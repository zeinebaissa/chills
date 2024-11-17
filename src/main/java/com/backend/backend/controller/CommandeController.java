package com.backend.backend.controller;

import com.backend.backend.model.Commande;
import com.backend.backend.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    // Créer une commande à partir d'un panier
    @PostMapping("/from-panier/{panierId}")
    public Commande createCommandeFromPanier(@PathVariable String panierId, @RequestBody Commande newCommande) {
        return commandeService.createCommandeFromPanier(panierId, newCommande);
    }

    // Récupérer une commande par ID
    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable String id) {
        return commandeService.getCommandeById(id);
    }

    // Mettre à jour une commande
    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable String id, @RequestBody Commande updatedCommande) {
        return commandeService.updateCommande(id, updatedCommande);
    }

    // Supprimer une commande
    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable String id) {
        commandeService.deleteCommande(id);
    }
}
