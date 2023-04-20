package com.einfochips.ecommerce.event;

import org.springframework.context.ApplicationEvent;
import com.einfochips.ecommerce.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEvent extends ApplicationEvent{
	
	private User user;
	private String applicationUrl;
	public UserEvent(User user,String applicationUrl) {
		super(user);
		this.user=user;
		this.applicationUrl=applicationUrl;
	}

}
