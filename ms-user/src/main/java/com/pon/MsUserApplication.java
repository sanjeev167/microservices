package com.pon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient // the @EnableDiscoveryClient allows it to be registered in Eureka.
// By default it will look for Eureka server running on http://localhost:8761 to register itself.

@SpringBootApplication
public class MsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUserApplication.class, args);
	}
	//This will fetch MsUserApplication configuration from the centralized location through ms-config. The centralized location has been configured
	// in ms-config application. MsUserApplication sends its control to mas-config at the time of starting up this application using bootstrap.yml configuration
	//
	
	 
	@RefreshScope 
	@RestController
	class MessageRestController {
		@Value("${kafka.topic.userCreated}")
		  private String message;
	 

	  @RequestMapping("/message")
	  String getMessage() {
		  System.out.println("message "+message);
	    return message;
	  }
	}

}
