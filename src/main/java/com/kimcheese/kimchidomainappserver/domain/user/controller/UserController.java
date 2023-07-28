package com.kimcheese.kimchidomainappserver.domain.user.controller;

import com.kimcheese.kimchidomainappserver.domain.user.entity.User;
import com.kimcheese.kimchidomainappserver.domain.user.repository.UserRepository;
import com.kimcheese.kimchidomainappserver.domain.user.vo.Role;
import com.kimcheese.kimchidomainappserver.domain.user.vo.SocialType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/domain/user")
public class UserController {
    private final UserRepository repository;
    @GetMapping("")
    public String getTest() throws Exception{
        User user1 = repository.findByEmail("test2345@gmail.com").orElse(null);
        User user2 = repository.findByRefreshToken("kdjflawjeiojkwlnflakjdsklaejkw").orElse(null);
        User user3 = repository.findBySocialTypeAndSocialId(SocialType.GOOGLE,"112550440252317315496").orElse(null);

        log.info(user1.get_id());
        log.info(user2.get_id());
        log.info(user3.get_id());

        return "Hello World";
    }

    @GetMapping("/save")
    public String saveTest() throws Exception{
        User user = new User();
        user.setEmail("test2345@gmail.com");
        user.setPassword("test2345");
        user.setRole(Role.ROLE_USER);
        user.setSocialType(SocialType.GOOGLE);
        user.setSocialId("190390139393");

        repository.save(user);

        return "Hello Save";
    }
}
