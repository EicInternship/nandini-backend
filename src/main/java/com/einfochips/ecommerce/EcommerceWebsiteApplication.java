package com.einfochips.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.einfochips.ecommerce.Service.EmailSendingService;

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

//	@Autowired
//	private EmailSendingService mailService;
//	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceWebsiteApplication.class, args);
	}
//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail() {
//		mailService.sendEmail("jdjethwa28@gmail.com","Cheking Email Sending Service", "Nothing in Mail Body");
//	}

}
