package com.kimcheese.kimchidomainappserver.domain.photobooth.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.kimcheese.kimchidomainappserver.core.repository.BaseRepository;
import com.kimcheese.kimchidomainappserver.core.util.DocumentIdCryptoUtil;
import com.kimcheese.kimchidomainappserver.core.util.FirebaseDocumentIdGenUtil;
import com.kimcheese.kimchidomainappserver.domain.photobooth.entity.PhotoBooth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class PhotoBoothRepository implements BaseRepository<PhotoBooth> {


    @Value("${firebase.service.page.limit}")
    private int boothLimit;

    public DocumentReference findDocumentById(String photoBoothId) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference photoBoothDocument =  firestore.collection(PhotoBooth.TABLENAME).document(photoBoothId);
        return photoBoothDocument;
    }

    @Override
    public List<QueryDocumentSnapshot> findByField(String fieldName, Object fieldValue) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(PhotoBooth.TABLENAME);

        Query query = collectionRef
                .whereEqualTo(fieldName, fieldValue)
                .orderBy("createdAt", Query.Direction.DESCENDING);
//                .limit(boothLimit);
        
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        return documents;
    }

    public List<QueryDocumentSnapshot> findByFieldWithStartAfter(String fieldName, Object fieldValue, String startAfterKey) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(PhotoBooth.TABLENAME);

        startAfterKey = new DocumentIdCryptoUtil().decrypt(startAfterKey);
        System.out.println(startAfterKey);

        ApiFuture<DocumentSnapshot> future = collectionRef.document(startAfterKey).get();
        DocumentSnapshot snapshot = future.get();

        Query query = collectionRef
                .whereEqualTo(fieldName, fieldValue)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .startAfter(snapshot)
                .limit(boothLimit);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        return documents;
    }

    public Optional<PhotoBooth> save(PhotoBooth photoBooth) throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(PhotoBooth.TABLENAME);
        String uuid = FirebaseDocumentIdGenUtil.generateUUID(collectionRef);
        photoBooth.set_id(uuid);
        ApiFuture<WriteResult> apiFuture =
                collectionRef.document(uuid).set(photoBooth);

        System.out.println(apiFuture.isDone());

        return Optional.of(photoBooth);
    }


}

