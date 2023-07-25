package com.kimcheese.kimchidomainappserver.domain.photobooth.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.kimcheese.kimchidomainappserver.core.util.DocumentIdCryptoUtil;
import com.kimcheese.kimchidomainappserver.domain.photobooth.entity.PhotoBooth;
import org.springframework.stereotype.Service;

import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.GetPhotoBooth;
import com.kimcheese.kimchidomainappserver.domain.photobooth.repository.PhotoBoothRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoBoothService {

    private final PhotoBoothRepository repository;

     public ArrayList<GetPhotoBooth> getPhotoBooth(String regionValue, String startAfter) throws Exception{
         List<QueryDocumentSnapshot> documents;
         if (startAfter == null){
             documents = repository.findByField("region",regionValue);
         }
         else{
             documents = repository.findByFieldWithStartAfter("region",regionValue,startAfter);
         }
         ArrayList<GetPhotoBooth> photoBooths = new ArrayList<>();
         for (DocumentSnapshot document : documents) {
             PhotoBooth photoBooth = document.toObject(PhotoBooth.class);
             GetPhotoBooth getPhotoBooth = GetPhotoBooth
                     .builder()
                     .username(photoBooth.getUsername())
                     .lastDocumentId(new DocumentIdCryptoUtil().encrypt(photoBooth.get_id()))
                     .hRate(photoBooth.getHRate())
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
}