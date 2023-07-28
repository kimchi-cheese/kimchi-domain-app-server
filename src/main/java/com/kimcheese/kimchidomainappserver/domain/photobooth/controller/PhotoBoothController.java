package com.kimcheese.kimchidomainappserver.domain.photobooth.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.kimcheese.kimchidomainappserver.core.response.BaseResponse;
import com.kimcheese.kimchidomainappserver.core.security.jwt.service.JwtService;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Country;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Region;
import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.MultiTestDTO;
import com.kimcheese.kimchidomainappserver.domain.photobooth.entity.PhotoBooth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.GetPhotoBooth;
import com.kimcheese.kimchidomainappserver.domain.photobooth.service.PhotoBoothService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/domain/photobooths")
public class PhotoBoothController {

    private final PhotoBoothService photoBoothService;
    private final JwtService jwtService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<List<GetPhotoBooth>>> getPhotoBoothsWithRegion(@RequestParam Region region, @RequestParam(required = false) String startAfter) throws Exception{

        List<GetPhotoBooth> getPhotoBooths = photoBoothService.getPhotoBooth("region",region.name(),startAfter);

        System.out.println(getPhotoBooths);
        HttpStatus httpstatus = HttpStatus.OK;
        return new ResponseEntity<>(new BaseResponse<>(httpstatus, getPhotoBooths), null, httpstatus);
    }

    @PostMapping(value = "",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String postPhotoBooths(
            @RequestHeader("Authorization") String accessToken,
            @ModelAttribute MultiTestDTO data) throws Exception{

        System.out.println(data.getImage());


        return "Hello Image";
    }

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<List<GetPhotoBooth>>> getPhotoBoothsWithMe(
            @RequestHeader("Authorization") String accessToken,
            @RequestParam(required = false) String startAfter) throws Exception{

        Optional<String> extractEmail = jwtService.extractEmail(accessToken);
        String email = extractEmail.get();
        System.out.println(email);
        List<GetPhotoBooth> getPhotoBooths = photoBoothService.getPhotoBooth("email",email,startAfter);
        System.out.println(getPhotoBooths);
        HttpStatus httpstatus = HttpStatus.OK;
        return new ResponseEntity<>(new BaseResponse<>(httpstatus, getPhotoBooths), null, httpstatus);
    }

}
