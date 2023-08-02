package com.kimcheese.kimchidomainappserver.domain.photo.controller;

import com.kimcheese.kimchidomainappserver.core.response.BaseResponse;
import com.kimcheese.kimchidomainappserver.domain.photo.service.PhotoService;
import com.kimcheese.kimchidomainappserver.domain.user.entity.User;
import com.kimcheese.kimchidomainappserver.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Tag(name = "Photo API", description = "사진 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/domain/photos")
public class PhotoController {

    private final UserService userService;

    private final PhotoService photoService;



    @PostMapping("")
    public ResponseEntity<BaseResponse<Map<String,String>>> fileUpload(@RequestParam("file") MultipartFile file,
                                                         RedirectAttributes redirectAttributes,
                                                         HttpServletRequest request) throws Exception {
        try {
            User user = userService.getUserByRequest(request);
            Map<String,String> value = photoService.postPhoto(file,user);
            HttpStatus httpstatus = HttpStatus.CREATED;
            return new ResponseEntity<>(new BaseResponse<>(httpstatus, value), null, httpstatus);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"인증 실패");
        }
    }
}
