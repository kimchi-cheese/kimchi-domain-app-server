package com.kimcheese.kimchidomainappserver.domain.photobooth.controller;

import com.kimcheese.kimchidomainappserver.core.response.BaseResponse;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Region;
import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.PostPhotoBooth;
import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.PutHrate;
import com.kimcheese.kimchidomainappserver.domain.photobooth.entity.PhotoBooth;
import com.kimcheese.kimchidomainappserver.domain.user.entity.User;
import com.kimcheese.kimchidomainappserver.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kimcheese.kimchidomainappserver.domain.photobooth.dto.GetPhotoBooth;
import com.kimcheese.kimchidomainappserver.domain.photobooth.service.PhotoBoothService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Tag(name = "PhotoBooth API", description = "포토 부스 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/domain/photobooths")
public class PhotoBoothController {

    private final PhotoBoothService photoBoothService;
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<List<GetPhotoBooth>>> getPhotoBoothsWithRegion(@RequestParam Region region, @RequestParam(required = false) String startAfterKey) throws Exception{

        List<GetPhotoBooth> getPhotoBooths = photoBoothService.getPhotoBooth("region",region.name(),startAfterKey);

        System.out.println(getPhotoBooths);
        HttpStatus httpstatus = HttpStatus.OK;
        return new ResponseEntity<>(new BaseResponse<>(httpstatus, getPhotoBooths), null, httpstatus);
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<PostPhotoBooth>> postPhotoBooth(
            @RequestBody PostPhotoBooth postPhotoBooth, HttpServletRequest request){

        try{
            User user = userService.getUserByRequest(request);
            PhotoBooth photoBooth = photoBoothService.postPhotoBooth(postPhotoBooth,user);

            if (photoBooth != null){
                HttpStatus httpstatus = HttpStatus.CREATED;
                return new ResponseEntity<>(new BaseResponse<>(httpstatus, postPhotoBooth), null, httpstatus);
            }
            else{
                log.error("photoBooth NullPointerException");
                throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러");
            }
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"인증 실패");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<List<GetPhotoBooth>>> getPhotoBoothsWithMe(
            HttpServletRequest request,
            @RequestParam(required = false) String startAfter){

        try{
            User user = userService.getUserByRequest(request);
            String email = user.getEmail();
            List<GetPhotoBooth> getPhotoBooths = photoBoothService.getPhotoBooth("email",email,startAfter);
            HttpStatus httpstatus = HttpStatus.OK;
            return new ResponseEntity<>(new BaseResponse<>(httpstatus, getPhotoBooths), null, httpstatus);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"서버 에러");
        }

    }

    @PutMapping("/hrate")
    public ResponseEntity<BaseResponse<Map<String,Object>>> putPhotoBoothByHrate(
            @RequestBody PutHrate putHrate,
            HttpServletRequest request){

        User user;
        try {
            user = userService.getUserByRequest(request);
            String email = user.getEmail();
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"인증 실패");
        }

        Map<String,Object> hrate = photoBoothService.putPhotoBooth(putHrate,user);
        HttpStatus httpstatus = HttpStatus.OK;
        return new ResponseEntity<>(new BaseResponse<>(httpstatus, hrate), null, httpstatus);

    }

}
