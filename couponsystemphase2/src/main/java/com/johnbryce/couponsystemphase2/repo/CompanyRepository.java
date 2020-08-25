package com.johnbryce.couponsystemphase2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnbryce.couponsystemphase2.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findByEmail(String email);

}
