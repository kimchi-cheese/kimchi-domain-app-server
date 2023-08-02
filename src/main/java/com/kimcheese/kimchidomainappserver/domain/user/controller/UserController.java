package com.kimcheese.kimchidomainappserver.domain.user.controller;

import com.kimcheese.kimchidomainappserver.core.redis.service.RedisService;
import com.kimcheese.kimchidomainappserver.core.response.BaseResponse;
import com.kimcheese.kimchidomainappserver.core.security.jwt.service.JwtService;
import com.kimcheese.kimchidomainappserver.domain.user.entity.User;
import com.kimcheese.kimchidomainappserver.domain.user.repository.UserRepository;
import com.kimcheese.kimchidomainappserver.domain.user.service.UserService;
import com.kimcheese.kimchidomainappserver.domain.user.vo.Role;
import com.kimcheese.kimchidomainappserver.domain.user.vo.SocialType;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "User API", description = "사용자 관련 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/domain/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<String>> logout(HttpServletRequest request){
        try{
            User user = userService.getUserByRequest(request);
            user.setRefreshToken(null);
            userRepository.update(user);

            HttpStatus httpstatus = HttpStatus.OK;
            return new ResponseEntity<>(new BaseResponse<>(httpstatus, "Succeed in Logout"), null, httpstatus);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"권한 없음");
        }
    }

//    @GetMapping("")
//    public String getTest() throws Exception{
//        User user1 = repository.findByEmail("test2345@gmail.com").orElse(null);
//        User user2 = repository.findByRefreshToken("kdjflawjeiojkwlnflakjdsklaejkw").orElse(null);
//        User user3 = repository.findBySocialTypeAndSocialId(SocialType.GOOGLE,"112550440252317315496").orElse(null);
//
//        log.info(user1.get_id());
//        log.info(user2.get_id());
//        log.info(user3.get_id());
//
//        return "Hello World";
//    }

//    @GetMapping("/save")
//    public String saveTest() throws Exception{
//        User user = new User();
//        user.setEmail("test2345@gmail.com");
//        user.setPassword("test2345");
//        user.setRole(Role.ROLE_USER);
//        user.setSocialType(SocialType.GOOGLE);
//        user.setSocialId("190390139393");
//
//        repository.save(user);
//
//        return "Hello Save";
//    }


//    @GetMapping("/redis/hash/test")
//    public String redisHashTest(){
//        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
//        Map<String, Object> map = new HashMap<>();
//        map.put("firstName", "Gyunny");
//        map.put("lastName", "Choi");
//        map.put("gender", "Man");
//        String uuid = UUID.randomUUID().toString();
//        hashOperations.putAll("test:"+uuid, map);
//
//        String firstName = (String) redisTemplate.opsForHash().get("key", "firstName");
//        String lastName = (String) redisTemplate.opsForHash().get("key", "lastName");
//        String gender = (String) redisTemplate.opsForHash().get("key", "gender");
//        System.out.println(firstName);
//        System.out.println(lastName);
//        System.out.println(gender);
//
//        return "good";
//    }
}
