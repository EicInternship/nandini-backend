package com.einfochips.ecommerce.Model;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
public class UserModel {
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String country;
	private String userType;
	private LocalDate signupDate = LocalDate.now();
	private String matchingPassword;
	
	
	
}
