package com.firebase.Bluebird3.Service;

import com.firebase.Bluebird3.model.BBUser;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    public String saveUserData(BBUser bbuser) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        if (checkUserRef(bbuser)) {
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("zorin_test")
                    .document(bbuser.getOrg_id()).set(bbuser);
            return bbuser.getOrg_id();
        }
        else return "Error in references";
    }

    public BBUser getUserData(String org_id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("zorin_test").document(org_id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot documentSnapshot = future.get();

        BBUser bbUser = null;

        if(documentSnapshot.exists()) {
            bbUser = documentSnapshot.toObject(BBUser.class);
        }

        return bbUser;
    }

    public boolean checkUserRef(BBUser bbUser) throws ExecutionException, InterruptedException {
        boolean result = true;
        Firestore dbFirestore = FirestoreClient.getFirestore();
        if (bbUser.getReferences() != null) {
            for (String reference : bbUser.getReferences()) {
                DocumentReference documentReference = dbFirestore.collection("zorin_test").document(reference);
                ApiFuture<DocumentSnapshot> future = documentReference.get();

                DocumentSnapshot documentSnapshot = future.get();

                if (!documentSnapshot.exists()) result = false;
            }
        }
        else result = false;
        return result;
    }
}
