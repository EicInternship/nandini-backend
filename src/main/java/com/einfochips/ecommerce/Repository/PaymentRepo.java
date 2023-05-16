package com.einfochips.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfochips.ecommerce.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

	Payment save(Payment payment);

}
