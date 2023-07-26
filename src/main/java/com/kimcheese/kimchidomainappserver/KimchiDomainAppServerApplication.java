package com.kimcheese.kimchidomainappserver;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class KimchiDomainAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KimchiDomainAppServerApplication.class, args);
	}

	@GetMapping("/domain/info")
	public ResponseEntity<String> info(){
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}

	@GetMapping("/domain/login/oauth2/code/{registrationId}")
	public String googleLogin(
			@RequestParam("code") String code,
			@RequestParam(value = "scope",required = false) String scope,
			@RequestParam(value = "authuser",required = false) String authuser,
			@RequestParam(value = "prompt",required = false) String prompt,
			@PathVariable String registrationId){
		System.out.println(code);
		System.out.println(scope);
		System.out.println(authuser);
		System.out.println(prompt);
		System.out.println(registrationId);
		return code;
	}

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			// 파일 처리 로직을 구현합니다.
			// 예를 들어, 파일을 저장하거나 다른 작업을 수행할 수 있습니다.
			// 여기서는 간단히 파일 이름을 반환합니다.
			return "Uploaded file: " + file.getOriginalFilename();
		} catch (Exception e) {
			return "Error uploading file: " + e.getMessage();
		}
	}
}
