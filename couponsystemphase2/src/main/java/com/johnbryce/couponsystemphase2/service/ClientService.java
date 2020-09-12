package com.johnbryce.couponsystemphase2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.couponsystemphase2.dbdao.CompanyDBDAO;
import com.johnbryce.couponsystemphase2.dbdao.CouponDBDAO;
import com.johnbryce.couponsystemphase2.dbdao.CustomerDBDAO;

@Service
public abstract class ClientService {

	@Autowired
	protected CompanyDBDAO companyDBDAO;
	@Autowired
	protected CouponDBDAO couponDBDAO;
	@Autowired
	protected CustomerDBDAO customerDBDAO;

	public abstract boolean login(String email, String password);
}
