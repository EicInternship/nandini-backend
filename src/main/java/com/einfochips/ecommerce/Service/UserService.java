package com.einfochips.ecommerce.Service;

import java.util.Optional;

import com.einfochips.ecommerce.Model.UserModel;
import com.einfochips.ecommerce.entity.User;
import com.einfochips.ecommerce.entity.VerificationToken;

public interface UserService {

	User registerUser(UserModel userModel);

    void saveVerificationToken(String token, User user);

//    String validateVerificationToken(String token);
//
//    VerificationToken generateNewVerificationToken(String oldToken);
//
//    User findUserByEmail(String email);
//
//    void createPasswordResetTokenForUser(User user, String token);
//
//    String validatePasswordResetToken(String token);
//
//    Optional<User> getUserByPasswordResetToken(String token);
//
//    void changePassword(User user, String newPassword);
//
//    boolean checkIfValidOldPassword(User user, String oldPassword);

}
