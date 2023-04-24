package com.einfochips.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EcommerceWebsiteApplication {
	
	private static final Logger log=LoggerFactory.getLogger(SpringBootApplication.class);
	@GetMapping("/test/{name}")
	public String getFirstName(@PathVariable String name) {
		log.info("test method called ");
		String response="Hi "+name +" Welcome please";
		log.info("test method executed");
		log.debug("Response", name);
		
		return response ;
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceWebsiteApplication.class, args);
	}

}
