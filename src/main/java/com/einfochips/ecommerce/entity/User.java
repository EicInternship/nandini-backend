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
	
	
	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getCountry() {
//		return country;
//	}
//	public void setCountry(String country) {
//		this.country = country;
//	}
//	public String getUserType() {
//		return userType;
//	}
//	public void setUserType(String userType) {
//		this.userType = userType;
//	}
//	public LocalDate getSignupDate() {
//		return signupDate;
//	}
//	public void setSignupDate(LocalDate signupDate) {
//		this.signupDate = signupDate;
//	}
//	public User() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public User(Long id, String email, String firstName, String lastName, String password, String country,
//			String userType, LocalDate signupDate) {
//		super();
//		this.id = id;
//		this.email = email;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.password = password;
//		this.country = country;
//		this.userType = userType;
//		this.signupDate = signupDate;
//	}
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", password=" + password + ", country=" + country + ", userType=" + userType + ", signupDate="
//				+ signupDate + "]";
//	}

	
}	
	
	