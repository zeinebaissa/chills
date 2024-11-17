package com.backend.backend.controller;

import com.backend.backend.model.SuperAdmin;
import com.backend.backend.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superAdmins")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    // Get all SuperAdmins
    @GetMapping
    public ResponseEntity<List<SuperAdmin>> getAllSuperAdmins() {
        List<SuperAdmin> superAdmins = superAdminService.getAllSuperAdmins();
        if (superAdmins.isEmpty()) {
            return ResponseEntity.noContent().build();  // No content if the list is empty
        }
        return ResponseEntity.ok(superAdmins);
    }

    // Get a SuperAdmin by ID
    @GetMapping("/{id}")
    public ResponseEntity<SuperAdmin> getSuperAdminById(@PathVariable String id) {
        SuperAdmin superAdmin = superAdminService.getSuperAdminById(id);
        return (superAdmin != null) ? ResponseEntity.ok(superAdmin) : ResponseEntity.notFound().build();
    }

    // Create a new SuperAdmin
    @PostMapping
    public ResponseEntity<SuperAdmin> createSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        SuperAdmin savedSuperAdmin = superAdminService.saveSuperAdmin(superAdmin);
        return (savedSuperAdmin != null) ? ResponseEntity.status(201).body(savedSuperAdmin) : ResponseEntity.badRequest().build();
    }

    // Update an existing SuperAdmin
    @PutMapping("/{id}")
    public ResponseEntity<SuperAdmin> updateSuperAdmin(@PathVariable String id, @RequestBody SuperAdmin superAdminDetails) {
        SuperAdmin updatedSuperAdmin = superAdminService.updateSuperAdmin(id, superAdminDetails);
        return (updatedSuperAdmin != null) ? ResponseEntity.ok(updatedSuperAdmin) : ResponseEntity.notFound().build();
    }

    // Delete a SuperAdmin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuperAdmin(@PathVariable String id) {
        superAdminService.deleteSuperAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
