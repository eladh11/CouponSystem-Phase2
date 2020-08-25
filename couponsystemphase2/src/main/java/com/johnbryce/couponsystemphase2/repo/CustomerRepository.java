package com.johnbryce.couponsystemphase2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnbryce.couponsystemphase2.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String email);

}
