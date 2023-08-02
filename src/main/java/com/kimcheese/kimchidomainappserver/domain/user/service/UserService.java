package com.kimcheese.kimchidomainappserver.domain.user.service;

import com.kimcheese.kimchidomainappserver.core.security.jwt.service.JwtService;
import com.kimcheese.kimchidomainappserver.domain.user.entity.User;
import com.kimcheese.kimchidomainappserver.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public User getUserByRequest(HttpServletRequest request) throws Exception{
        String accessToken = jwtService.extractAccessToken(request).orElse(null);
        String email = jwtService.extractEmail(accessToken).orElse(null);
        User user = userRepository.findByEmail(email).orElse(null);
        return user;
    }
}
