package com.einfochips.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfochips.ecommerce.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User findByEmail(String email);
	User findByEmailAndPassword(String email,String password);
	boolean existsByEmail(String email);
	boolean existsByEmailAndPassword(String email,String password);
}
