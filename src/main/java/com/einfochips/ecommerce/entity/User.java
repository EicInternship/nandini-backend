package com.einfochips.ecommerce.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "First Name should not be empty")
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty")
    private String lastName;

    @Column(length = 60)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",message = "Password should"
    		+ " contain at least one digit, one capital letter, one small letter, and one special character")
    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty(message = "Country should not be empty")
    private String country;

    @NotNull
    @Pattern(regexp = "^[0-9]{10,10}$", message = "Phone number should be exactly 10 digits")
    private String phoneNo;

    @NotBlank
    private String userType;

    private LocalDate signupDate = LocalDate.now();

    private String role;

    private double spent;
	
	
}	
	
	