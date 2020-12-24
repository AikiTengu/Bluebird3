package com.firebase.Bluebird3.Service;

import com.firebase.Bluebird3.pojo.BBUser;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    public String saveUserData(BBUser bbuser) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture =  dbFirestore.collection("zorin_test")
                .document(bbuser.getOrg_id()).set(bbuser);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}
