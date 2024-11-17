package com.backend.backend.service;

import com.backend.backend.model.Client;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ClientService {

    private final Firestore firestore;
    private final String COLLECTION_NAME = "clients";

    @Autowired
    public ClientService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Get all clients
    public List<Client> getAllClients() {
        CollectionReference clients = firestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> query = clients.get();
        List<Client> clientList = new ArrayList<>();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                clientList.add(doc.toObject(Client.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    // Get client by ID
    public Client getClientById(String id) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.toObject(Client.class);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create a new client
    public Client saveClient(Client client) {
        try {
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
            client.setId(docRef.getId()); // Set Firestore auto-generated ID
            docRef.set(client).get();  // Wait for the write operation to complete
            return client;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update client
    public Client updateClient(String id, Client clientDetails) {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> future = docRef.update(
                "nom", clientDetails.getNom(),
                "email", clientDetails.getEmail(),
                "motDePasse", clientDetails.getMotDePasse(),
                "tel", clientDetails.getTel(),
                "imgClient", clientDetails.getImgClient()
        );
        try {
            future.get();  // Wait for the update to complete
            return clientDetails;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete client
    public void deleteClient(String id) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).delete().get();  // Wait for operation to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
