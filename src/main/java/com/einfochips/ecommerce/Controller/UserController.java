package com.einfochips.ecommerce.Controller;

import java.io.Console;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.einfochips.ecommerce.Model.User;
import com.einfochips.ecommerce.Repository.UserRepo;


@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

	@Autowired
	private UserRepo userrepo;
	
//	@PostMapping("saveuser")
//	public User getUserInfo(@RequestBody User user) {
//		ModelAndView mv= new ModelAndView();
//		User savedUser = userrepo.save(user);
//		return savedUser;
//		
//	}
	@RequestMapping("viewuser")
	public List<User> getAllUsers(){
		return userrepo.findAll();
	}
	
	@PostMapping("/saveuser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.println("Received form data: " + user.toString());
        User savedUser = userrepo.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping("/checkuser")
    public ResponseEntity<Map<String, Boolean>> checkUser(@RequestParam String email) {
        boolean exists = userrepo.existsByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    @GetMapping("/validuser")
    public ResponseEntity<Map<String, Object>> validateUser(@RequestParam String email, @RequestParam String password) {
        try {
            boolean isValidUser = userrepo.existsByEmailAndPassword(email, password);
            Map<String, Object> response = new HashMap<>();
            response.put("isValidUser", isValidUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred while validating user credentials.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


	
    

		
		
	
}
