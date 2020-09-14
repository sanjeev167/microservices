package com.pon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient // annotation to enable Eureka discovery for clients.
//By default it will look for Eureka server running on http://localhost:8761 to
//register
@SpringBootApplication
public class MsMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMailApplication.class, args);
	}

}
