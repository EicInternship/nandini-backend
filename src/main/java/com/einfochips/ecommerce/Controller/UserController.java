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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.einfochips.ecommerce.Repository.UserRepo;
import com.einfochips.ecommerce.Service.JwtService;
import com.einfochips.ecommerce.Service.UserServiceImpl;
import com.einfochips.ecommerce.entity.AuthRequest;
import com.einfochips.ecommerce.entity.Payment;
import com.einfochips.ecommerce.entity.User;
import com.einfochips.ecommerce.exception.EmailNotFoundExcaption;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/payment")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @Autowired
    UserRepo ur;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
   	private AuthenticationManager authenticationManager;
    
    private static final Logger log=LoggerFactory.getLogger(SpringBootApplication.class);

    @GetMapping("/checkuser")
    public ResponseEntity<Map<String, Boolean>> checkUsers(@RequestParam @Valid String email) throws Exception {
    	log.info("Checking email already present? ");
        return userServiceImpl.checkUsers(email);
    }
    
    
    @GetMapping("/checkuseremail")
    public ResponseEntity<Map<String, Boolean>> checkuseremail(@RequestBody @Valid String email) throws EmailNotFoundExcaption {
    	log.info("Checking email already present? ");
        return userServiceImpl.checkuseremail(email);
    }
    
    @PostMapping("/checkEmailForReset")
    public ResponseEntity<Map<String,Boolean>> checkEmailForForgetPassword(@RequestBody @Valid String email) throws Exception{
    	log.info("Checking Email For Forget Password");
    	System.out.println(email.replace("%40", "@").replace("=", ""));
    	return userServiceImpl.checkEmailForForgetPassword(email.replace("%40", "@").replace("=", ""));
    }
    @PostMapping("/saveuser")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
    	log.info("Registration of user occures");
        return userServiceImpl.saveUser(user);
    }

    @GetMapping("/viewuser")
    public List<User> getAllUsers() {
    	log.info("Customer List printed in frontend");
        return userServiceImpl.getAllUsers();
    }

//    @GetMapping("/validuser")
//    public ResponseEntity<Map<String, Object>> validateUser(@RequestParam String email, @RequestParam String password) {
//    	log.info("Validation of user to login");
//        return userServiceImpl.validateUser(email, password);
//    }
    
@PostMapping("/validateuser")
public ResponseEntity<String> authenticateAndGetToken(@RequestBody @Valid AuthRequest authRequest) {
	
	Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
	if (authentication.isAuthenticated()) {
		log.info("token genrated");
		return new ResponseEntity<String>( jwtService.generateToken(authRequest.getEmail()),HttpStatus.OK);
	} else {
		log.info("user is invalied");
//		throw new UsernameNotFoundException("invalid user request !");
		return new ResponseEntity<String>("NaNdhgfgdsi", HttpStatus.BAD_REQUEST);
	}

}
    @GetMapping("/totaluser")
    public ResponseEntity<Long> countEmail() {
    	log.info("Counting total number of Customers");
        return userServiceImpl.countEmail();
    }
    @GetMapping("/deleteuser")
    public void deleteUser(@RequestParam @Valid long id) {
        log.info("Delete Customer with email " + id);
//        userServiceImpl.deleteUser(id);
        ur.deleteById(id);
        
    }
    @PostMapping("/updateuser")
    public void updateUser(@RequestBody @Valid User user) {
      log.info("Updating user with id: ");
    	System.out.println(user);
    	ur.save(user);
    }
    
    @GetMapping("/customerdetail")
    public ResponseEntity<User> getUserDetailsById(@RequestParam @Valid Long id) {
    	log.info("Detail Of Customer with ID"+id);
        User user = ur.findById(id)
            .orElseThrow();
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/dopayment")
    public Payment doPayment(@RequestBody @Valid Payment payment) {
    	return userServiceImpl.doPayment(payment);
    }
    @GetMapping("/getuser")
    public User getUser(@RequestBody @Valid User user) {
    	return userServiceImpl.getUser(user);
    }
    @PostMapping("/getuser")
    public User getUsers(@RequestBody @Valid User user) {
    	return userServiceImpl.getUser(user);
    }
    @GetMapping("/totalseller")
    public ResponseEntity<Long> getTotalSeller(){
    	log.info("Getting Total number of Seller");
    	return userServiceImpl.getTotalSeller();
    }
    @GetMapping("/totalcustomer")
    public ResponseEntity<Long> getTotalcustomer(){
    	log.info("Getting Total Number of Customer connected with Ecommerce website");
    	return userServiceImpl.getTotalCustomer();
    }
    @GetMapping("/totaladmin")
    public ResponseEntity<Long> getTotalAdmin(){
    	log.info("Total number of Admin Display");
    	return userServiceImpl.getTotalAdmin();
    }
    @GetMapping("/sumofspent")
    public ResponseEntity<Double> getSumOfSpent(){
    	log.info("Getting Sum of Total Spent");
    	return userServiceImpl.getSumOfSpent();
    }
    
   

}
