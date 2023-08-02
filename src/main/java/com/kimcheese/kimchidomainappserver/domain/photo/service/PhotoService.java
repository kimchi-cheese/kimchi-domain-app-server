package com.kimcheese.kimchidomainappserver.domain.photo.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.kimcheese.kimchidomainappserver.core.config.AiGenFeignClient;
import com.kimcheese.kimchidomainappserver.core.config.AiGenInfo;
import com.kimcheese.kimchidomainappserver.core.redis.service.RedisService;
import com.kimcheese.kimchidomainappserver.core.util.FirebaseDocumentIdGenUtil;
import com.kimcheese.kimchidomainappserver.domain.photo.entity.Photo;
import com.kimcheese.kimchidomainappserver.domain.user.entity.User;
import com.kimcheese.kimchidomainappserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhotoService {
    @Value("${firebase.service.bucket.url}")
    private String bucket_url;

    @Value("${firebase.service.storage.url}")
    private String storage_url;

    private final AiGenFeignClient aiGenFeignClient;
    private final RedisService redisService;

    private final UserRepository userRepository;
    public Map<String,String> postPhoto(MultipartFile file, User user){
        String email = user.getEmail();
        String image_name = email+"_"+ UUID.randomUUID().toString()+".png";

        try{
            Bucket bucket = StorageClient.getInstance().bucket(bucket_url);
            InputStream content = new ByteArrayInputStream(file.getBytes());
            String save_url = "images/"+email+"/origin/"+image_name;

            Blob blob = bucket.create(save_url,content,file.getContentType());

            String save_replace_url =
                    save_url
                            .replace("/","%2F")
                            .replace("@","%40");

            String img_url = storage_url + save_replace_url + "?alt=media";

            AiGenInfo aiGenInfo = AiGenInfo.builder()
                    .id(image_name)
                    .image_url(img_url)
                    .prompt(img_url+" "+"cartoon style, pretty, korean people, seoul style --iw 2")
                    .build();

            Map<String,Object> aiInfo = aiGenFeignClient.postAiGenImages(aiGenInfo);

            if ((Boolean)aiInfo.get("is_success")){
                String item_key = "midjourney_ai:"+aiGenInfo.getId();
                Future<Map<String,String>> futureValue = redisService.getGenAiImage(item_key);
                try{
                    Map<String,String> value = futureValue.get();
                    if(value != null){
                        String id = value.get("id");
                        String result_url = value.get("result_url");
                        String created_at = value.get("created_at");
                        log.info(id+": "+result_url+"_at: "+created_at);

                        DocumentReference userDocumentRef = userRepository.getUserRef(user);

                        Photo photo = new Photo();
                        photo.setImageUrl(result_url);
                        photo.setUserId(userDocumentRef);
                        photo.setUsername(user.getNickname());

                        Firestore firestore = FirestoreClient.getFirestore();
                        CollectionReference collectionRef = firestore.collection(Photo.TABLENAME);
                        String uuid = FirebaseDocumentIdGenUtil.generateUUID(collectionRef);
                        photo.set_id(uuid);
                        ApiFuture<WriteResult> apiFuture = collectionRef.document(uuid).set(photo);

                        value.replace("id",uuid);
                        return value;
                    }
                    else{
                        throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"AI 이미지 생성 실패");
                    }
                }
                catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                    throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러");
                }
            }
            else{
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"사진 AI 실패");
            }
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러");
        }

    }
}
