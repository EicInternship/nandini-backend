package com.einfochips.ecommerce.event;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.einfochips.ecommerce.Service.UserService;
import com.einfochips.ecommerce.entity.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEventListener implements ApplicationListener<UserEvent> {

	@Autowired
	private UserService userService;
	@Override
	public void onApplicationEvent(UserEvent event) {
		//Create the Verification token for the User with Link
		User user = event.getUser();
		String token=UUID.randomUUID().toString();
		userService.saveVerificationToken(token,user);
		//Save Mail to user
		String url=
		       event.getApplicationUrl()
		       +"/verifyRegistration?token="
		    		   +token;
        

         //sendVerificationEmail()
         log.info("Click the link to verify your account: {}",url);
		
	}

}
