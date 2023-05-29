package com.einfochips.ecommerce.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.einfochips.ecommerce.Repository.UserRepo;
import com.einfochips.ecommerce.entity.User;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

//	@Autowired
//	PasswordEncoder passwordEncoder;
	@Mock
	private UserRepo userrepo;

	@InjectMocks
	private UserServiceImpl userserviceimpl;

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("BeforeEach Called");
		when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
//		when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
	}
	 // Create an instance of BCryptPasswordEncoder
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Create a non-null rawPassword
    String rawPassword = "Kumud@123";

    // Encode the password
    String encodedPassword = passwordEncoder.encode(rawPassword);

	@Test
	public void testSaveUser() {
		// Create a sample user
		User user = new User();
		user.setEmail("kumud2222@gmail.com");
		user.setFirstName("Kumud");
		user.setLastName("Vasu");
		user.setCountry("Germany");
		user.setPhoneNo("8768768768");
		user.setPassword("Kumud@123");
		user.setUserType("Seller");
		user.setRole("NRG");

		// Mock the behavior of passwordEncoder
		when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

		// Mock the behavior of userRepository
		when(userrepo.save(user)).thenReturn(user);

		// Call the method under test
		ResponseEntity<User> response = userserviceimpl.saveUser(user);

		// Verify the result
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("AfterEach Called");
	}
}
