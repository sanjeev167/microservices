package com.pon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableEurekaClient//it assures that ms-gateway is also an Eureka client
@EnableZuulProxy//It enables Zuul to act as a proxy for any auto-discovered services.
@SpringBootApplication
public class MsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}
	
	@Bean
	 public UserDetailsService userDetailsService() {
	  UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
	   .build();
	  return new InMemoryUserDetailsManager(user);
	 }

}
