package com.backend.backend.controller;

import com.backend.backend.model.Banner;
import com.backend.backend.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    // Get all banners
    @GetMapping
    public ResponseEntity<List<Banner>> getAllBanners() {
        List<Banner> banners = bannerService.getAllBanners();
        if (banners.isEmpty()) {
            return ResponseEntity.noContent().build();  // No content if the list is empty
        }
        return ResponseEntity.ok(banners);
    }

    // Get a banner by ID
    @GetMapping("/{id}")
    public ResponseEntity<Banner> getBannerById(@PathVariable String id) {
        Banner banner = bannerService.getBannerById(id);
        return (banner != null) ? ResponseEntity.ok(banner) : ResponseEntity.notFound().build();
    }

    // Create a new banner
    @PostMapping
    public ResponseEntity<Banner> createBanner(@RequestBody Banner banner) {
        Banner savedBanner = bannerService.saveBanner(banner);
        return (savedBanner != null) ? ResponseEntity.status(201).body(savedBanner) : ResponseEntity.badRequest().build();
    }

    // Update an existing banner
    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBanner(@PathVariable String id, @RequestBody Banner bannerDetails) {
        Banner updatedBanner = bannerService.updateBanner(id, bannerDetails);
        return (updatedBanner != null) ? ResponseEntity.ok(updatedBanner) : ResponseEntity.notFound().build();
    }

    // Delete a banner
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable String id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.noContent().build();
    }
}
