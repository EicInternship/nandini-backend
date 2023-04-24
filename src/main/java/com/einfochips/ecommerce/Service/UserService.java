package com.einfochips.ecommerce.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.einfochips.ecommerce.entity.User;

public interface UserService {

	ResponseEntity<Map<String, Boolean>> checkUsers(String email);
	ResponseEntity<User> saveUser(User user);
	List<User> getAllUsers();
	ResponseEntity<Map<String, Object>> validateUser(String email, String password);
//	ResponseEntity<Long> countEmail();
}
