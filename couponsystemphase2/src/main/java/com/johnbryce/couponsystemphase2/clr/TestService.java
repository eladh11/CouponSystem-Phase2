package com.johnbryce.couponsystemphase2.clr;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.couponsystemphase2.beans.Category;
import com.johnbryce.couponsystemphase2.beans.Company;
import com.johnbryce.couponsystemphase2.beans.Coupon;
import com.johnbryce.couponsystemphase2.beans.Customer;
import com.johnbryce.couponsystemphase2.exception.AlreadyExitException;
import com.johnbryce.couponsystemphase2.exception.IncorrectDetailsException;
import com.johnbryce.couponsystemphase2.security.ClientType;
import com.johnbryce.couponsystemphase2.security.LoginManager;
import com.johnbryce.couponsystemphase2.service.AdminService;
import com.johnbryce.couponsystemphase2.service.CompanyService;
import com.johnbryce.couponsystemphase2.service.CustomerService;

@Component
@Order(2)
public class TestService implements CommandLineRunner {
	@Autowired
	private LoginManager loginManager;

	@Override
	public void run(String... args) throws Exception {

		testAll();

	}

	public void testAll() throws AlreadyExitException, IncorrectDetailsException {
		space();
		space();
		AdminService admin = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);

		Company company = Company.builder().name("stam Company").email("stam@gmail.com").password("stam1234").build();
		admin.addCompany(company);
		System.out.println(admin.getOneCompany(company.getId()));
		space();
		company.setName("blabla");
		admin.updateCompany(company);
		System.out.println(admin.getAllCompanies());
		space();
		admin.deleteCompany(company);
		System.out.println(admin.getAllCompanies());
		space();

		admin.addCompany(company);

		CompanyService wiskey = (CompanyService) loginManager.login("Wiskey@gmail.com", "Wiskey1234",
				ClientType.Company);

		Coupon coupon = Coupon.builder().id(6).companyID(5).category(Category.VACATION).title("stam Coupon")
				.description("bla bla").startDate(convertUtilDateToSQL(new Date(2020, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2021, 01, 01))).amount(100).price(9.99).image("url").build();

		company.setCoupons(Arrays.asList(coupon));
		admin.updateCompany(company);

		System.out.println(wiskey.getCompanyCoupons());
		space();
		coupon.setTitle("bla bla");
		wiskey.updateCoupon(coupon);
		System.out.println(wiskey.getCompanyCoupons(Category.VACATION));
		space();
		wiskey.deleteCoupon(6);
		System.out.println(wiskey.getCompanyCoupons(30));
		space();

		Customer customer = Customer.builder().first("e").last("h").email("e@gmail.com").password("e1234").build();
		admin.addCustomer(customer);
		space();
		CustomerService elad = (CustomerService) loginManager.login("e@gmail.com", "e1234", ClientType.Customer);
		elad.purchaseCoupon(22, 2);
		space();
		elad.purchaseCoupon(22, 3);
		space();
		elad.purchaseCoupon(22, 4);
		space();
		elad.purchaseCoupon(22, 5);
		space();
	}

	public void space() {
		System.out.println();
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println();
	}

	// Change from java.util.Date - to - java.sql.Date
	@SuppressWarnings("deprecation")
	public java.sql.Date convertUtilDateToSQL(java.util.Date date) {
		return new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDay() + 1);
	}

}
