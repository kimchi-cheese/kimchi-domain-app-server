//package com.kimcheese.kimchidomainappserver.domain.photo.repository;
//
//import com.google.api.core.ApiFuture;
//import com.google.cloud.firestore.CollectionReference;
//import com.google.cloud.firestore.DocumentReference;
//import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.WriteResult;
//import com.google.firebase.cloud.FirestoreClient;
//import com.kimcheese.kimchidomainappserver.core.util.FirebaseDocumentIdGenUtil;
//import com.kimcheese.kimchidomainappserver.domain.photo.entity.Photo;
//import com.kimcheese.kimchidomainappserver.domain.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.Map;
//
//@Repository
//@RequiredArgsConstructor
//public class PhotoRepository {
//    private final UserRepository userRepository;
//    public Map<String,String> save(Map<String,String> value){
//        Firestore firestore = FirestoreClient.getFirestore();
//        CollectionReference collectionRef = firestore.collection(Photo.TABLENAME);
//        try{
//            String uuid = FirebaseDocumentIdGenUtil.generateUUID(collectionRef);
//            photo.set_id(uuid);
//            ApiFuture<WriteResult> apiFuture = collectionRef.document(uuid).set(photo);
//
//            value.replace("id",uuid);
//
//            return value;
//        }
//        catch (Exception e){
//
//        }
//
//    }
//
//
//}
