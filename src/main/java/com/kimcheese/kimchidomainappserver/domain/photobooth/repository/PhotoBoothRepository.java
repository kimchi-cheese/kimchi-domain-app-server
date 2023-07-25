package com.kimcheese.kimchidomainappserver.domain.photobooth.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.kimcheese.kimchidomainappserver.core.repository.BaseRepository;
import com.kimcheese.kimchidomainappserver.core.util.DocumentIdCryptoUtil;
import com.kimcheese.kimchidomainappserver.domain.photobooth.entity.PhotoBooth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoBoothRepository implements BaseRepository<PhotoBooth> {

    @Value("${firebase.service.page.limit}")
    private int boothLimit;

    @Override
    public List<QueryDocumentSnapshot> findByField(String fieldName, Object fieldValue) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference photoBoothRef = firestore.collection(PhotoBooth.TABLENAME);

        Query query = photoBoothRef
                .whereEqualTo(fieldName, fieldValue);
//                .orderBy("timestamp", Query.Direction.DESCENDING)
//                .limit(boothLimit);
        
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        return documents;
    }

    public List<QueryDocumentSnapshot> findByFieldWithStartAfter(String fieldName, Object fieldValue, String startAfter) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference photoBoothRef = firestore.collection(PhotoBooth.TABLENAME);

        Query query = photoBoothRef
                .whereEqualTo(fieldName, fieldValue)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .startAfter(new DocumentIdCryptoUtil().decrypt(startAfter))
                .limit(boothLimit);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        return documents;
    }
}

