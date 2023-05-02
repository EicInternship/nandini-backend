package com.einfochips.ecommerce.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String firstName;
	private String lastName;
	
	@Column(length=60)
	private String password;
	private String country;
	private String userType;
	private LocalDate signupDate = LocalDate.now();
	private String phoneno;
	private String role;
	
	
	
}	
	
	