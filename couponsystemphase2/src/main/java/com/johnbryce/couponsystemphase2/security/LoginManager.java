package com.johnbryce.couponsystemphase2.security;

import org.springframework.beans.factory.annotation.Autowired;

import com.johnbryce.couponsystemphase2.security.ClientType;
import com.johnbryce.couponsystemphase2.service.AdminService;
import com.johnbryce.couponsystemphase2.service.ClientService;
import com.johnbryce.couponsystemphase2.service.CompanyService;
import com.johnbryce.couponsystemphase2.service.CustomerService;

public class LoginManager {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;

	public ClientService login(String email, String password, ClientType clientType) {
		switch (clientType) {
		case Administrator:
			if (adminService.login(email, password) == true) {
				return adminService;
			}
			System.out.println("Wrong Details...");
			return null;
		case Company:
			if (companyService.login(email, password)) {
				return companyService;
			}
			System.out.println("Wrong Details...");
			return null;
		case Customer:
			if (customerService.login(email, password)) {
				return customerService;
			}
			System.out.println("Wrong Details...");
			return null;
		default:
			System.out.println("the Client type has not found...");
			break;
		}
		return null;
	}
}
