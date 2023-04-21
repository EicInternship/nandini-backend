package com.einfochips.ecommerce.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.einfochips.ecommerce.Service.UserService;
import com.einfochips.ecommerce.entity.User;


import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/checkuser")
    public ResponseEntity<Map<String, Boolean>> checkUsers(@RequestParam String email) {
        return userService.checkUsers(email);
    }

    @PostMapping("/saveuser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/viewuser")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/validuser")
    public ResponseEntity<Map<String, Object>> validateUser(@RequestParam String email, @RequestParam String password) {
        return userService.validateUser(email, password);
    }
}
