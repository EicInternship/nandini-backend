package com.einfochips.ecommerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.einfochips.ecommerce.entity.User;
import com.einfochips.ecommerce.userLoads.UserResponse;

import jakarta.transaction.Transactional;

@Transactional
public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	User findByEmailAndPassword(String email,String password);
	
	
	
	boolean existsByEmail(String email);
	
	@Query("SELECT COUNT(*) FROM User")
    Long countEmail();
	
	@Query("SELECT COUNT(*) FROM User WHERE userType='Customer'")
	Long countCustomer();
	
	@Query("SELECT COUNT(*) FROM User WHERE userType = 'Seller'")
	Long countSeller();
	
	@Query("SELECT COUNT(*) FROM User WHERE userType='Admin'")
	Long countAdmin();
	
	@Query("SELECT SUM(spent) FROM User")
	int sumOfSpent();
	

	@Modifying
	@Query("UPDATE User SET password = :password WHERE email = :email")
	void changePassword(@Param("email") String email, @Param("password") String password);

	
	@Query("DELETE FROM User WHERE id=:id")
	void deleteUser(long id);

	Optional<User> findById(Long id);

	
	@Modifying
	@Query("UPDATE User u SET u.firstName = :#{#user.firstName}, u.lastName = :#{#user.lastName}, u.email = :#{#user.email}, u.password = :#{#user.password}, u.country = :#{#user.country}, u.userType = :#{#user.userType} WHERE u.id = :id")
	void updateUser(@Param("id") long id, @Param("user") User user);

	

			
}
