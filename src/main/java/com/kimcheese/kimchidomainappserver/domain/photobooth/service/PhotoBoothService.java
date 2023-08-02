package com.kimcheese.kimchidomainappserver.domain.photobooth.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.kimcheese.kimchidomainappserver.core.response.BaseResponse;
import com.kimcheese.kimchidomainappserver.core.util.DocumentIdCryptoUtil;
import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.PostPhotoBooth;
import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.PutHrate;
import com.kimcheese.kimchidomainappserver.domain.photobooth.entity.PhotoBooth;
import com.kimcheese.kimchidomainappserver.domain.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.GetPhotoBooth;
import com.kimcheese.kimchidomainappserver.domain.photobooth.repository.PhotoBoothRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhotoBoothService {

    private final PhotoBoothRepository photoBoothRepository;


     public ArrayList<GetPhotoBooth> getPhotoBooth(String fieldName, String fieldValue, String startAfterKey){
         List<QueryDocumentSnapshot> documents;
         try{
             if (startAfterKey == null){
                 documents = photoBoothRepository.findByField(fieldName,fieldValue);
             }
             else{
                 documents = photoBoothRepository.findByFieldWithStartAfter(fieldName,fieldValue,startAfterKey);
             }
             ArrayList<GetPhotoBooth> photoBooths = new ArrayList<>();
             for (DocumentSnapshot document : documents) {
                 PhotoBooth photoBooth = document.toObject(PhotoBooth.class);
                 GetPhotoBooth getPhotoBooth = GetPhotoBooth
                         .builder()
                         .username(photoBooth.getUsername())
                         .lastDocumentId(new DocumentIdCryptoUtil().encrypt(photoBooth.get_id()))
                         .hrate(photoBooth.getHrate())
                         .sns(photoBooth.getSns())
                         .imageUrls(photoBooth.getPhotos().stream().map(
                                         map ->{
                                             map.remove("location");
                                             map.remove("photoRefId");
                                             return map;
                                         }
                                 ).toList()
                         )
                         .location(photoBooth.getLocation())
                         .region(photoBooth.getRegion())
                         .build();
                 photoBooths.add(getPhotoBooth);
             }

             return photoBooths;
         }
         catch (Exception e){
             throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러");
         }

     }

     public PhotoBooth postPhotoBooth(PostPhotoBooth postPhotoBooth, User user){

         PhotoBooth photoBooth = registerPostPhotoBooth(postPhotoBooth);

         Firestore firestore = FirestoreClient.getFirestore();
         DocumentReference userDocumentRef = firestore.collection(User.TABLENAME).document(user.get_id());

         photoBooth.setUserId(userDocumentRef);
         photoBooth.setUsername(user.getNickname());
         photoBooth.setEmail(user.getEmail());

         try{
             photoBooth = photoBoothRepository.save(photoBooth).orElse(null);
             return photoBooth;
         }
         catch (Exception e){
             throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러");
         }

     }

     public Map<String,Object> putPhotoBooth(PutHrate putHrate, User user){

         try{
             Firestore firestore = FirestoreClient.getFirestore();
             DocumentReference userRef = firestore.collection(User.TABLENAME).document(user.get_id());
             String email = user.getEmail();
             String photoBoothId = new DocumentIdCryptoUtil().decrypt(putHrate.getPhotoBoothId());
             DocumentReference photoBoothDocument = photoBoothRepository.findDocumentById(photoBoothId);
             PhotoBooth photoBooth = photoBoothDocument.get().get().toObject(PhotoBooth.class);
             List<Map<String,Object>> hrateList = photoBooth.getHrate();

             if (hrateList == null) {
                 hrateList = new ArrayList<Map<String,Object>>();
             }

             Map<String,Object> hrate = hrateList.stream()
                     .filter(hr -> hr.get("email").equals(email))
                     .findFirst().orElse(null);

             if (hrate != null){
                 if (hrate.get("check").equals(true)){
                     hrate.replace("check",false);
                 }
                 else{
                     hrate.replace("check",true);
                 }
             }
             else{
                 hrate  = new HashMap<>();
//                 hrate.put("id",userRef);
                 hrate.put("email",email);
                 hrate.put("check",true);

                 hrateList.add(hrate);
             }
             ApiFuture<WriteResult> future = photoBoothDocument.update("hrate",hrateList);
             return hrate;
         }
         catch (Exception e){
             log.error(e.getMessage());
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"해당 포토부스는 없습니다.");
         }


     }

     public PhotoBooth registerPostPhotoBooth (PostPhotoBooth postPhotoBooth){
         PhotoBooth photoBooth = new PhotoBooth();
         photoBooth.setPhotos(postPhotoBooth.getPhotos());
         photoBooth.setGps(postPhotoBooth.getGps());
         photoBooth.setCountry(postPhotoBooth.getCountry());
         photoBooth.setLocation(postPhotoBooth.getLocation());
         photoBooth.setFormattedAddress(postPhotoBooth.getFormattedAddress());
         photoBooth.setRegion(postPhotoBooth.getRegion());

         return photoBooth;
     }
}