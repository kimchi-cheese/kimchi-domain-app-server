package com.kimcheese.kimchidomainappserver.core.config;

//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
////    private final JwtTokenProvid jwtTokenProvider;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http
//                .httpBasic((basic) -> basic.disable())
//                .csrf((csrf) -> csrf.disable())
//                .sessionManagement((session)->
//                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authorizeHttpRequests((authorizeRequests) ->
//                        authorizeRequests
//                                .requestMatchers("/**").hasRole("USER")
//                                .requestMatchers("/**").permitAll()
//                                .requestMatchers("/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin((user)->
//                        user
//                        .usernameParameter("login")
//                        .passwordParameter("password")
//                        .loginPage("/security-login/login")
//                        .defaultSuccessUrl("/security-login")
//                        .failureUrl("/security-login/login")
//                )
//                .logout((logout) -> logout
//                        .logoutUrl("/security-login/logout")
//                )
//
//
//                .build();
//
//
//
//    }
//}
