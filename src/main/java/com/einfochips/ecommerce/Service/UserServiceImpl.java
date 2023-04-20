package com.einfochips.ecommerce.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.einfochips.ecommerce.Model.UserModel;
import com.einfochips.ecommerce.Repository.UserRepo;
import com.einfochips.ecommerce.Repository.VerificationTokenRepo;
import com.einfochips.ecommerce.entity.User;
import com.einfochips.ecommerce.entity.VerificationToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private final VerificationTokenRepo verificationTokenRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public void saveVerificationToken(String token, User user) {
		VerificationToken verificationToken=new VerificationToken(user,token);
		verificationTokenRepo.save(verificationToken);
	}

	@Override
	public User registerUser(UserModel userModel) {
		return userrepo.save(User.builder().firstName(userModel.getFirstName())
				.email(userModel.getEmail()).country(userModel.getCountry())
				.lastName(userModel.getLastName()).password(passwordEncoder.encode(userModel.getPassword()))
				.userType(userModel.getUserType()).signupDate(LocalDate.now()).build());
	}
	
	
	 

//	    @Override
//	    public String validateVerificationToken(String token) {
//	        VerificationToken verificationToken
//	                = verificationTokenRepository.findByToken(token);
//
//	        if (verificationToken == null) {
//	            return "invalid";
//	        }
//
//	        User user = verificationToken.getUser();
//	        Calendar cal = Calendar.getInstance();
//
//	        if ((verificationToken.getExpirationTime().getTime()
//	                - cal.getTime().getTime()) <= 0) {
//	            verificationTokenRepository.delete(verificationToken);
//	            return "expired";
//	        }
//
//	        user.setEnabled(true);
//	        userRepository.save(user);
//	        return "valid";
//	    }
//
//	    @Override
//	    public VerificationToken generateNewVerificationToken(String oldToken) {
//	        VerificationToken verificationToken
//	                = verificationTokenRepository.findByToken(oldToken);
//	        verificationToken.setToken(UUID.randomUUID().toString());
//	        verificationTokenRepository.save(verificationToken);
//	        return verificationToken;
//	    }
//
//	    @Override
//	    public User findUserByEmail(String email) {
//	        return userRepository.findByEmail(email);
//	    }
//
//	    @Override
//	    public void createPasswordResetTokenForUser(User user, String token) {
//	        PasswordResetToken passwordResetToken
//	                = new PasswordResetToken(user,token);
//	        passwordResetTokenRepository.save(passwordResetToken);
//	    }
//
//	    @Override
//	    public String validatePasswordResetToken(String token) {
//	        PasswordResetToken passwordResetToken
//	                = passwordResetTokenRepo.findByToken(token);
//
//	        if (passwordResetToken == null) {
//	            return "invalid";
//	        }
//
//	        User user = passwordResetToken.getUser();
//	        Calendar cal = Calendar.getInstance();
//
//	        if ((passwordResetToken.getExpirationTime().getTime()
//	                - cal.getTime().getTime()) <= 0) {
//	            passwordResetTokenRepo.delete(passwordResetToken);
//	            return "expired";
//	        }
//
//	        return "valid";
//	    }
//
//	    @Override
//	    public Optional<User> getUserByPasswordResetToken(String token) {
//	        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
//	    }
//
//	    @Override
//	    public void changePassword(User user, String newPassword) {
//	        user.setPassword(passwordEncoder.encode(newPassword));
//	        userRepository.save(user);
//	    }
//
//	    @Override
//	    public boolean checkIfValidOldPassword(User user, String oldPassword) {
//	        return passwordEncoder.matches(oldPassword, user.getPassword());
//	    }
//	}
}
