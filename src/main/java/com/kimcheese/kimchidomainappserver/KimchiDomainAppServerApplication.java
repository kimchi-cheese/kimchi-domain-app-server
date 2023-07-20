package com.kimcheese.kimchidomainappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
