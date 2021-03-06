package com.pon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient // annotation to enable Eureka discovery for clients.
					// By default it will look for Eureka server running on http://localhost:8761 to
					// register

@EnableConfigServer // We should also enable the config server so that other applications will talk
					// to this config server for
					// fetching the configuration kept outside the application
@SpringBootApplication
public class MsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsConfigServerApplication.class, args);
	}

}
