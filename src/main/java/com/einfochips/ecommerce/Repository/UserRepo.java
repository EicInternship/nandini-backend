package com.einfochips.ecommerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.einfochips.ecommerce.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	User findByEmailAndPassword(String email,String password);
	boolean existsByEmail(String email);
	@Query("SELECT COUNT(DISTINCT email) FROM User")
    Long countEmail();
	
	@Query("DELETE FROM User WHERE id=:id")
	void deleteUser(long id);

//	@Query("UPDATE user SET firstName='firstName',lastName='lastName',email='email',password='password',country='country',userType='userType' where id=:id")
//	void updateUser(long id,User user);
	
//	@Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.email = :email, u.password = :password, u.country = :country, u.userType = :userType WHERE u.id = :id")
//	void updateUser(@Param("id") long id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("password") String password, @Param("country") String country, @Param("userType") String userType);

	
	@Modifying
	@Query("UPDATE User u SET u.firstName = :#{#user.firstName}, u.lastName = :#{#user.lastName}, u.email = :#{#user.email}, u.password = :#{#user.password}, u.country = :#{#user.country}, u.userType = :#{#user.userType} WHERE u.id = :id")
	void updateUser(@Param("id") long id, @Param("user") User user);

	

			
}
