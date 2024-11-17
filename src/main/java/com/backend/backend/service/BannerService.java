package com.backend.backend.service;

import com.backend.backend.model.Banner;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BannerService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "banners";

    @Autowired
    public BannerService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Get all banners
    public List<Banner> getAllBanners() {
        CollectionReference banners = firestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> query = banners.get();
        List<Banner> bannerList = new ArrayList<>();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                bannerList.add(doc.toObject(Banner.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return bannerList;
    }

    // Get banner by ID
    public Banner getBannerById(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.toObject(Banner.class);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create a new banner
    public Banner saveBanner(Banner banner) {
        try {
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
            banner.setIdBanner(docRef.getId()); // Set Firestore auto-generated ID
            docRef.set(banner).get();  // Wait for the write operation to complete
            return banner;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update banner
    public Banner updateBanner(String id, Banner bannerDetails) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> future = docRef.update(
                "image", bannerDetails.getImage(),
                "etatBanner", bannerDetails.isEtatBanner()
        );
        try {
            future.get();  // Wait for the update to complete
            return bannerDetails;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete banner
    public void deleteBanner(String id) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).delete().get();  // Wait for operation to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
