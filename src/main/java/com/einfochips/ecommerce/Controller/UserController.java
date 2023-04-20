package com.einfochips.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.einfochips.ecommerce.Config.WebSecurityConfig;
import com.einfochips.ecommerce.Model.UserModel;
import com.einfochips.ecommerce.Repository.UserRepo;
import com.einfochips.ecommerce.Service.UserService;
import com.einfochips.ecommerce.entity.User;
import com.einfochips.ecommerce.event.UserEvent;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
   private ApplicationEventPublisher publisher;
	
	

	
	
	@PostMapping("/checkuser")
	public String registerUser(@RequestBody UserModel userModel,final HttpServletRequest request) {
		User user= userService.registerUser(userModel);
		publisher.publishEvent(new UserEvent(
				user,
				applicationUrl(request)
	    ));
		return "success";
	}

	private String applicationUrl(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "http://"+
				request.getServerName()+
				":"+
				request.getServerPort()+
				request.getContextPath();
	}
//	@GetMapping("/verifyRegistration")
//    public String verifyRegistration(@RequestParam("token") String token) {
//        String result = userService.validateVerificationToken(token);
//        if(result.equalsIgnoreCase("valid")) {
//            return "User Verified Successfully";
//        }
//        return "Bad User";
//    }
//
//
//    @GetMapping("/resendVerifyToken")
//    public String resendVerificationToken(@RequestParam("token") String oldToken,
//                                          HttpServletRequest request) {
//        VerificationToken verificationToken
//                = userService.generateNewVerificationToken(oldToken);
//        User user = verificationToken.getUser();
//        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
//        return "Verification Link Sent";
//    }
//
//    @PostMapping("/resetPassword")
//    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
//        User user = userService.findUserByEmail(passwordModel.getEmail());
//        String url = "";
//        if(user!=null) {
//            String token = UUID.randomUUID().toString();
//            userService.createPasswordResetTokenForUser(user,token);
//            url = passwordResetTokenMail(user,applicationUrl(request), token);
//        }
//        return url;
//    }
//
//    @PostMapping("/savePassword")
//    public String savePassword(@RequestParam("token") String token,
//                               @RequestBody PasswordModel passwordModel) {
//        String result = userService.validatePasswordResetToken(token);
//        if(!result.equalsIgnoreCase("valid")) {
//            return "Invalid Token";
//        }
//        Optional<User> user = userService.getUserByPasswordResetToken(token);
//        if(user.isPresent()) {
//            userService.changePassword(user.get(), passwordModel.getNewPassword());
//            return "Password Reset Successfully";
//        } else {
//            return "Invalid Token";
//        }
//    }
//
//    @PostMapping("/changePassword")
//    public String changePassword(@RequestBody PasswordModel passwordModel){
//        User user = userService.findUserByEmail(passwordModel.getEmail());
//        if(!userService.checkIfValidOldPassword(user,passwordModel.getOldPassword())) {
//            return "Invalid Old Password";
//        }
//        //Save New Password
//        userService.changePassword(user,passwordModel.getNewPassword());
//        return "Password Changed Successfully";
//    }
//
//    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
//        String url =
//                applicationUrl
//                        + "/savePassword?token="
//                        + token;
//
//        //sendVerificationEmail()
//        log.info("Click the link to Reset your Password: {}",
//                url);
//        return url;
//    }
//
//
//    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
//        String url =
//                applicationUrl
//                        + "/verifyRegistration?token="
//                        + verificationToken.getToken();
//
//        //sendVerificationEmail()
//        log.info("Click the link to verify your account: {}",
//                url);
//    }
//
//
//    private String applicationUrl(HttpServletRequest request) {
//        return "http://" +
//                request.getServerName() +
//                ":" +
//                request.getServerPort() +
//                request.getContextPath();
//    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void saveuser(User user) {
//		 String hashedPassword = passwordEncoder.encode(user.getPassword());
//	        user.setPassword(hashedPassword);
//	        userrepo.save(user);
//	}
//	@RequestMapping("viewuser")
//	public List<User> getAllUsers(){
//		return userrepo.findAll();
//	}
	
//	@PostMapping("/saveuser")
//	public ResponseEntity<User> saveUser(@RequestBody User user) {
//	    System.out.println("Received form data: " + user.toString());
//	    // Encode the user's password with BCrypt
//	    String encodedPassword = passwordEncoder.encode(user.getPassword());
//	    user.setPassword(encodedPassword);
//	    // Save the user to the database
//	    User savedUser = userrepo.save(user);
//	    
//	    return new ResponseEntity<>(savedUser, HttpStatus.OK);
//	}
//
//
//    @GetMapping("/checkuser")
//    public ResponseEntity<Map<String, Boolean>> checkUser(@RequestParam String email) {
//        boolean exists = userrepo.existsByEmail(email);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("exists", exists);
//        
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @GetMapping("/validuser")
//    public ResponseEntity<Map<String, Object>> validateUser(@RequestParam String email, @RequestParam String password) {
//        try {
//            Optional<User> user = Optional.ofNullable(userrepo.findByEmail(email));
//            if(user.isPresent()) {
//                String hashedPassword = user.get().getPassword();
//                if(passwordEncoder.matches(password, hashedPassword)) {
//                    // Passwords match, return success response
//                    Map<String, Object> response = new HashMap<>();
//                    response.put("isValidUser", true);
//                    return ResponseEntity.ok(response);
//                } else {
//                    // Passwords do not match, return error response
//                    Map<String, Object> errorResponse = new HashMap<>();
//                    errorResponse.put("isValidUser", false);
////                    return ResponseEntity.badRequest().body(errorResponse);
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//                }
//            } else {
//                // User not found, return error response
//                Map<String, Object> errorResponse = new HashMap<>();
//                errorResponse.put("error", "User not found");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//            }
//        } catch (Exception e) {
//            // Handle any exception that occurs during the validation process
//            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("error", e.getMessage());
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//            return ResponseEntity.badRequest().body(errorResponse);
//        }
//    }





	
    

		
		
	
}
