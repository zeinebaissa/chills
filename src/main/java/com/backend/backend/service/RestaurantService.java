package com.backend.backend.service;

import com.backend.backend.model.Restaurant;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class RestaurantService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "restaurant";

    @Autowired
    public RestaurantService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Get all restaurants
    public List<Restaurant> getAllRestaurants() {
        CollectionReference restaurants = firestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> query = restaurants.get();
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                restaurantList.add(doc.toObject(Restaurant.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    // Get restaurant by ID
    public Restaurant getRestaurantById(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.toObject(Restaurant.class);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create a new restaurant
    public Restaurant saveRestaurant(Restaurant restaurant) {
        try {
            // Auto-generate ID for restaurant
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
            restaurant.setIdResto(docRef.getId()); // Set the ID from Firestore
            docRef.set(restaurant).get();  // Wait for operation to complete
            return restaurant;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update restaurant
    public Restaurant updateRestaurant(String id, Restaurant restaurantDetails) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);

        // Firestore update returns WriteResult, not Void
        ApiFuture<WriteResult> future = docRef.update(
                "localisationRestau", restaurantDetails.getLocalisationRestau(),
                "etatResto", restaurantDetails.getEtatResto()
        );

        try {
            // Wait for the update operation to complete (write result contains metadata)
            WriteResult writeResult = future.get();  // This is the WriteResult, not Void
            return restaurantDetails;  // Return the updated restaurant details
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;  // Return null if there is an error
    }


    // Delete restaurant
    public void deleteRestaurant(String id) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).delete().get();  // Wait for operation to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
