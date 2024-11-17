package com.backend.backend.controller;

import com.backend.backend.model.CodePromo;
import com.backend.backend.service.CodePromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/codepromos")
public class CodePromoController {

    @Autowired
    private CodePromoService codePromoService;

    @GetMapping
    public ResponseEntity<List<CodePromo>> getAllCodePromos() {
        List<CodePromo> codePromos = codePromoService.getAllCodePromos();
        if (codePromos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(codePromos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodePromo> getCodePromoById(@PathVariable String id) {
        CodePromo codePromo = codePromoService.getCodePromoById(id);
        return (codePromo != null) ? ResponseEntity.ok(codePromo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CodePromo> createCodePromo(@RequestBody CodePromo codePromo) {
        CodePromo savedCodePromo = codePromoService.saveCodePromo(codePromo);
        return (savedCodePromo != null) ? ResponseEntity.status(201).body(savedCodePromo) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CodePromo> updateCodePromo(@PathVariable String id, @RequestBody CodePromo codePromo) {
        CodePromo updatedCodePromo = codePromoService.updateCodePromo(id, codePromo);
        return (updatedCodePromo != null) ? ResponseEntity.ok(updatedCodePromo) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCodePromo(@PathVariable String id) {
        codePromoService.deleteCodePromo(id);
        return ResponseEntity.noContent().build();
    }
}
