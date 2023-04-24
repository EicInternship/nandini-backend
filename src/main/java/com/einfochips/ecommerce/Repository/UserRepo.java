package com.einfochips.ecommerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.einfochips.ecommerce.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	User findByEmailAndPassword(String email,String password);
	boolean existsByEmail(String email);
	@Query("SELECT COUNT(DISTINCT email) FROM User")
    Long countEmail();
	
	@Query("DELETE FROM User WHERE email='email'")
	User deleteUser(String email);

			
}
