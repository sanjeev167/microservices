package com.pon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer// This is the only annotation we need for this to become a Server
@SpringBootApplication
public class MsDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDiscoveryApplication.class, args);
	}

}
