package com.kimcheese.kimchidomainappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@EnableFeignClients
public class KimchiDomainAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KimchiDomainAppServerApplication.class, args);
	}


//	@GetMapping("/domain/login/oauth2/code/{registrationId}")
//	public String googleLogin(
//			@RequestParam("code") String code,
//			@RequestParam(value = "scope",required = false) String scope,
//			@RequestParam(value = "authuser",required = false) String authuser,
//			@RequestParam(value = "prompt",required = false) String prompt,
//			@PathVariable String registrationId){
//		System.out.println(code);
//		System.out.println(scope);
//		System.out.println(authuser);
//		System.out.println(prompt);
//		System.out.println(registrationId);
//		return code;
//	}

}
