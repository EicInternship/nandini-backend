package com.einfochips.ecommerce.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.einfochips.ecommerce.Repository.PaymentRepo;
import com.einfochips.ecommerce.Repository.UserRepo;
import com.einfochips.ecommerce.entity.AuthRequest;
import com.einfochips.ecommerce.entity.Payment;
import com.einfochips.ecommerce.entity.User;
import com.einfochips.ecommerce.exception.EmailNotFoundExcaption;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<Map<String, Boolean>> checkUsers(String email) throws Exception {
        boolean exists = userRepo.existsByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        if(!exists) {
        	throw new Exception("No user found");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    
    public ResponseEntity<Map<String,Boolean>>checkuseremail(String email) throws EmailNotFoundExcaption{
    	boolean userExist=userRepo.existsByEmail(email);
    	Map<String,Boolean> response=new HashMap<>();
    	if (!userExist) {
    	    throw new EmailNotFoundExcaption("User with this email '" + email + "' is not found");
    	}
    	

    	return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword) ;
        User savedUser = userRepo.save(user);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }
    public ResponseEntity<Long> countEmail() {
        Long count = userRepo.countEmail();
        return ResponseEntity.ok(count);
    }

//    public ResponseEntity<Map<String, Object>> validateUser(String email, String password) {
//        try {
//            Optional<User> userOptional = userRepo.findByEmail(email);
//            if (userOptional.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(Collections.singletonMap("error", "User not found"));
//            }
//
//            User user = userOptional.get();
//            if (!passwordEncoder.matches(password, user.getPassword())) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(Collections.singletonMap("error", "Incorrect password"));
//            }
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("isValidUser", true);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }
    
    
    public void deleteUser(long id) {
        userRepo.deleteUser(id);
    }

	public Payment doPayment(Payment payment) {
	payment.setPaymentStatus(paymentProcessing());
	payment.setTransactionId(UUID.randomUUID().toString());
		return paymentRepo.save(payment);
	}
	public String paymentProcessing() {
		//api should be 3rd party payment gateway
		return new Random().nextBoolean()?"success":"false";
	}


	public User getUser(User user) {
		return userRepo.save(user);
	}
	public ResponseEntity<Long> getTotalSeller(){
		Long count=userRepo.countSeller();
		return ResponseEntity.ok(count);
	}

	public ResponseEntity<Long> getTotalAdmin() {
		Long count=userRepo.countAdmin();
		return ResponseEntity.ok(count);
	}
	public ResponseEntity<Long> getTotalCustomer() {
		Long count=userRepo.countCustomer();
		return ResponseEntity.ok(count);
	}

	@Override
	public ResponseEntity<Double> getSumOfSpent() {
		Double sum=userRepo.sumOfSpent();
		return ResponseEntity.ok(sum);
	}


	
	


	
}
