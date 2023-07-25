package com.kimcheese.kimchidomainappserver.domain.photobooth.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.kimcheese.kimchidomainappserver.core.response.BaseResponse;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Country;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Region;
import com.kimcheese.kimchidomainappserver.domain.photobooth.entity.PhotoBooth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.GetPhotoBooth;
import com.kimcheese.kimchidomainappserver.domain.photobooth.service.PhotoBoothService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/domain/photobooths")
public class PhotoBoothController {

    private final PhotoBoothService photoBoothService;

    @GetMapping("info")
    public String getInfo(){
        return "Hello PhotoBooth";
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse<List<GetPhotoBooth>>> getPhotoBooths(@RequestParam Region region, @RequestParam(required = false) String startAfter) throws Exception{

        List<GetPhotoBooth> getPhotoBooths = photoBoothService.getPhotoBooth(region.name(),startAfter);

        System.out.println(getPhotoBooths);
        HttpStatus httpstatus = HttpStatus.OK;
        return new ResponseEntity<>(new BaseResponse<>(httpstatus, getPhotoBooths), null, httpstatus);
    }
}
