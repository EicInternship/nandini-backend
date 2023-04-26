package com.einfochips.ecommerce.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.einfochips.ecommerce.Repository.UserRepo;
import com.einfochips.ecommerce.Service.UserServiceImpl;
import com.einfochips.ecommerce.entity.User;


import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @Autowired
    UserRepo ur;
    
    private static final Logger log=LoggerFactory.getLogger(SpringBootApplication.class);

    @GetMapping("/checkuser")
    public ResponseEntity<Map<String, Boolean>> checkUsers(@RequestParam String email) {
    	log.info("Checking email already present? ");
        return userServiceImpl.checkUsers(email);
    }

    @PostMapping("/saveuser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
    	log.info("Registration of user occures");
        return userServiceImpl.saveUser(user);
    }

    @GetMapping("/viewuser")
    public List<User> getAllUsers() {
    	log.info("Customer List printed in frontend");
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/validuser")
    public ResponseEntity<Map<String, Object>> validateUser(@RequestParam String email, @RequestParam String password) {
    	log.info("Validation of user to login");
        return userServiceImpl.validateUser(email, password);
    }
    @GetMapping("/totaluser")
    public ResponseEntity<Long> countEmail() {
    	log.info("Counting total number of Customers");
        return userServiceImpl.countEmail();
    }
    @GetMapping("/deleteuser")
    public void deleteUser(@RequestParam long id) {
        log.info("Delete Customer with email " + id);
//        userServiceImpl.deleteUser(id);
        ur.deleteById(id);
        
    }
    @PostMapping("/updateuser")
    public void updateUser(@RequestBody User user) {
//        log.info("Updating user with id: " + id);
    	System.out.println(user);
    	ur.save(user);
    }


}
