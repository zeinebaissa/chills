package com.backend.backend.controller;

import com.backend.backend.model.Avis;
import com.backend.backend.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;

    // Ajouter un avis
    @PostMapping
    public Avis createAvis(@RequestBody Avis avis) {
        return avisService.createAvis(avis);
    }

    // Récupérer tous les avis pour un client
    @GetMapping("/client/{clientId}")
    public List<Avis> getAvisByClientId(@PathVariable String clientId) {
        return avisService.getAvisByClientId(clientId);
    }

    // Récupérer un avis par ID
    @GetMapping("/{idAvis}")
    public Avis getAvisById(@PathVariable String idAvis) {
        return avisService.getAvisById(idAvis);
    }

    // Mettre à jour un avis
    @PutMapping("/{idAvis}")
    public Avis updateAvis(@PathVariable String idAvis, @RequestBody Avis updatedAvis) {
        return avisService.updateAvis(idAvis, updatedAvis);
    }

    // Supprimer un avis
    @DeleteMapping("/{idAvis}")
    public void deleteAvis(@PathVariable String idAvis) {
        avisService.deleteAvis(idAvis);
    }
}
