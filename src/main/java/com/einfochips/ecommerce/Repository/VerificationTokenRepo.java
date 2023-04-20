package com.einfochips.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfochips.ecommerce.entity.VerificationToken;




public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long>{

}
