package com.einfochips.ecommerce.Service;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.einfochips.ecommerce.EcommerceWebsiteApplication;
import com.einfochips.ecommerce.Repository.PaymentRepo;
import com.einfochips.ecommerce.Repository.UserRepo;
import com.einfochips.ecommerce.entity.AuthRequest;
import com.einfochips.ecommerce.entity.Payment;
import com.einfochips.ecommerce.entity.User;
import com.einfochips.ecommerce.exception.EmailNotFoundExcaption;
import com.einfochips.ecommerce.userLoads.UserResponse;

@Service
public class UserServiceImpl  {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
	private EmailSendingService mailService;
	
    ModelMapper modelMapper = new ModelMapper();


    public ResponseEntity<Map<String,Boolean>>checkuseremail(String email) throws EmailNotFoundExcaption{
    	boolean userExist=userRepo.existsByEmail(email);
    	Map<String,Boolean> response=new HashMap<>();
    	if (!userExist) {
    	    throw new EmailNotFoundExcaption("User with this email '" + email + "' is not found");
    	    
    	}
    	
    	return new ResponseEntity<>(response,HttpStatus.OK);
    } 
    
    public ResponseEntity<Map<String, Boolean>> checkUsers(String email) throws Exception {
        boolean exists = userRepo.existsByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        if(!exists) {
        	throw new Exception("No user found");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

   
    public ResponseEntity<Map<String, Boolean>> checkEmailForForgetPassword(String email) throws Exception {
        boolean emailExist = userRepo.existsByEmail(email);
//        if(!emailExist) {
//        	throw new Exception("User with this email:"+ email+" is not found");
//        }
//        else {
//        	throw new Exception("Email: "+email+" is found Successfully");
//        }
       Map<String, Boolean> response = new HashMap<>();
        response.put("emailExist", emailExist);

        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "@$%&*";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(10);

        // Append one uppercase letter
        sb.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));

      // Append one lowercase letter
        sb.append(lowercaseLetters.charAt(random.nextInt(lowercaseLetters.length())));
        // Append one number
       sb.append(numbers.charAt(random.nextInt(numbers.length())));

       // Append one special character
        sb.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        // Generate remaining characters randomly
        for (int i = 4; i < 10; i++) {
           String randomChars = uppercaseLetters + lowercaseLetters + numbers + specialCharacters;
            sb.append(randomChars.charAt(random.nextInt(randomChars.length())));
        }

        System.out.println(sb);
        System.out.println(emailExist);

        if (!emailExist) {
            System.out.println("123");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            System.out.println("12379879879");
            String plainTextPassword = sb.toString();
            String encodedPassword = passwordEncoder.encode(plainTextPassword);
            System.out.println(encodedPassword);
            userRepo.changePassword(email, encodedPassword);

            System.out.println("456");
            mailService.sendEmail(email, "For Reset Password", "Your Email id is=" + email +
                    " and Password is=" + plainTextPassword);

           return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<Map<String,String>> changeUserPassword(String email,String password){
    	Map<String, String> response = new HashMap<>();
        response.put("Change Password","Change Password Successfullsy");
    	String encodedPassword = passwordEncoder.encode(password);
    	userRepo.changePassword(email, encodedPassword);
    	return new ResponseEntity<> (response, HttpStatus.OK);
    }


    public ResponseEntity<User> saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword) ;
        User savedUser = userRepo.save(user);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    public Page<User> getAllUsers(Integer pageNumber, Integer pageSize) {
       
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepo.findAll(pageable);
System.out.println(pageNumber);
        return userPage;
    }
      

   


    public ResponseEntity<Long> countEmail() {
        Long count = userRepo.countEmail();
        return ResponseEntity.ok(count);
    }
   

    public boolean deleteUser(long id) {
        User user= userRepo.findById(id).orElse(null);
        userRepo.delete(user);
        return true;
    }

//	public Payment doPayment(Payment payment) {
//	payment.setPaymentStatus(paymentProcessing());
//	payment.setTransactionId(UUID.randomUUID().toString());
//		return paymentRepo.save(payment);
//	}
//	public String paymentProcessing() {
//		//api should be 3rd party payment gateway
//		return new Random().nextBoolean()?"success":"false";
//	}


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

	
	public int getTotalProfit(){
		int sum=userRepo.sumOfSpent();
		return sum;
	}

    public int add(int a ,int b)
    {
    	return a+b;
    }
    
    public Object checkNull(Object Obj) {
//    	
    	if(Obj!=null) {
    		return Obj;
    	}
    	return null;
    }
    
    
    public String checksameString() {
    	String str1="coding";
    	return str1;
    }
    
    public String checkeequalString() {
    	String str2="code";
    	return str2;
    }
    public Boolean isGreater(int m1,int m2) {
    	if(m1>m2) {
    		return true;
    	}
    	return false;
    }
    
    public String[] checkArray() {
    	String demoArray[]={"a","b","c"};
    	return demoArray;
    }
    
    public List<String> checkArrayList(){
    	List<String> listOfArray=List.of("code","einfochips","1611");
    	return listOfArray;
    }
    
    public List<String> linematch(){
    	List<String> arrayList=List.of("one","two","three","four");
    	return arrayList;
    }
    
    public String throwException(int n) throws Exception {
    	if(n<0) {
    		throw new Exception("Value is less than zero");
    	}
    	return "Value is greater than zero";
    }
    
    public void checkTimeout() throws InterruptedException{
    	System.out.println("I am Going to Sleep");
    	Thread.sleep(2000);
    	System.out.println("Sleeping Over");
    }
    
    
    
	
	


	
}
