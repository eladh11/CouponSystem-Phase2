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
import com.johnbryce.couponsystemphase2.dbdao.CouponDBDAO;
import com.johnbryce.couponsystemphase2.exception.AlreadyExitException;
import com.johnbryce.couponsystemphase2.exception.IncorrectDetailsException;
import com.johnbryce.couponsystemphase2.service.AdminService;
import com.johnbryce.couponsystemphase2.service.CompanyService;
import com.johnbryce.couponsystemphase2.service.CustomerService;

@Component
@Order(2)
public class TestService implements CommandLineRunner {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;

	@Override
	public void run(String... args) throws Exception {

		testAll();

		// Start the Daily Thread:
		// CouponExpirationDailyJob.startTheDailyThread();

//	Stop the Daily Thread: 
//		CouponExpirationDailyJob.stopTheDailyThread();
	}

	public void testAll() throws AlreadyExitException, IncorrectDetailsException {
		adminService.login("admin@admin.com", "admin");
		Company company = Company.builder().name("stam Company").email("stam@gmail.com").password("stam1234").build();
		adminService.addCompany(company);
		System.out.println(adminService.getOneCompany(company.getId()));
		space();
		company.setName("blabla");
		adminService.updateCompany(company);
		System.out.println(adminService.getAllCompanies());
		space();
		adminService.deleteCompany(company);
		System.out.println(adminService.getAllCompanies());
		space();

		adminService.addCompany(company);

		companyService.login("Trivago@gmail.com", "trivago1234");

		Coupon coupon = Coupon.builder().id(6).companyID(5).category(Category.VACATION).title("stam Coupon")
				.description("bla bla").startDate(convertUtilDateToSQL(new Date(2020, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2021, 01, 01))).amount(100).price(9.99).image("url").build();

		company.setCoupons(Arrays.asList(coupon));
		adminService.updateCompany(company);

		System.out.println(companyService.getCompanyCoupons());
		space();
		coupon.setTitle("bla bla");
		companyService.updateCoupon(coupon);
		System.out.println(companyService.getCompanyCoupons(Category.VACATION));
		space();
		companyService.deleteCoupon(6);
		System.out.println(companyService.getCompanyCoupons(30));
		space();

		Customer customer = Customer.builder().first("e").last("h").email("e@gmail.com").password("e1234").build();
		adminService.addCustomer(customer);

		customerService.login("e@gmail.com", "e1234");
		customerService.purchaseCoupon(22, 3);
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
