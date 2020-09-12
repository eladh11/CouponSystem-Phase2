package com.johnbryce.couponsystemphase2.clr;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.couponsystemphase2.beans.Category;
import com.johnbryce.couponsystemphase2.beans.Company;
import com.johnbryce.couponsystemphase2.beans.Coupon;
import com.johnbryce.couponsystemphase2.beans.Customer;
import com.johnbryce.couponsystemphase2.dbdao.CompanyDBDAO;
import com.johnbryce.couponsystemphase2.dbdao.CustomerDBDAO;
import com.johnbryce.couponsystemphase2.exception.AlreadyExitException;
import com.johnbryce.couponsystemphase2.exception.IncorrectDetailsException;

@Component
@Order(1)
public class TestDBDAO implements CommandLineRunner {

	@Autowired
	private CompanyDBDAO companyDBDAO;
	@Autowired
	private CustomerDBDAO customerDBDAO;

	public final static int AMOUNT = 100;

	@Override
	public void run(String... args) throws Exception {
		// Create All Companies And Coupons();
		createCompaniesAndCoupons();
		// Create All Customers
		createCusromers();
	}

	// Create Customers
	public void createCusromers() throws IncorrectDetailsException {
		Customer customer = Customer.builder().first("elad").last("hakmon").email("elad@gmail.com").password("elad1234")
				.build();
		customerDBDAO.addCustomer(customer);
		for (int i = 0; i < 20; i++) {
			customerDBDAO.addCustomer(generateOneCustomer());
			System.out.println("new Customer as added :-)");
		}
		System.out.println("all Customers as added Successfully!");
		space();
	}

	// Generate one Customer:
	public Customer generateOneCustomer() {
		Customer customer = Customer.builder().first(generateFirstName()).last(generateLastName()).build();
		customer.setEmail(generateEmail(customer));
		customer.setPassword(generatePassword(customer));
		return customer;
	}

	// Generate Random Customers - First Name:
	public String generateFirstName() {
		int rand = (int) (Math.random() * 25);
		switch (rand) {
		case 0:
			return "elad";
		case 1:
			return "kobi";
		case 2:
			return "dan";
		case 3:
			return "haki";
		case 4:
			return "heli";
		case 5:
			return "natali";
		case 6:
			return "omer";
		case 7:
			return "karin";
		case 8:
			return "danit";
		case 9:
			return "yossi";
		case 10:
			return "hen";
		case 11:
			return "ben";
		case 12:
			return "yoni";
		case 13:
			return "kfir";
		case 14:
			return "sharon";
		case 15:
			return "yaeli";
		case 16:
			return "dana";
		case 17:
			return "sapir";
		case 18:
			return "moshe";
		case 19:
			return "rotem";
		case 20:
			return "eden";
		case 21:
			return "israel";
		case 22:
			return "noha";
		case 23:
			return "malachi";
		case 24:
			return "tchahi";

		default:
			return null;
		}
	}

	// Generate Random Customers - Last Name:
	public String generateLastName() {
		String string = " ";
		Random random = new Random();
		int r = (int) (Math.random() * 8) + 2;
		String abc = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < r; i++) {
			string += abc.charAt(random.nextInt(abc.length()));
		}
		return string;
	}

	// Generate Random Customers - Email:
	public String generateEmail(Customer customer) {
		return customer.getFirst() + (int) (Math.random() * 1000 + 100) + "@gmail.com";
	}

	// Generate Random Customers - Password:
	public String generatePassword(Customer customer) {
		return customer.getLast() + "12345";
	}

	// Change from java.util.Date - to - java.sql.Date
	@SuppressWarnings("deprecation")
	public java.sql.Date convertUtilDateToSQL(java.util.Date date) {
		return new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDay() + 1);
	}

	// Create Companies:
	public void createCompaniesAndCoupons() throws AlreadyExitException {

		Company c1 = Company.builder().name("Electricity").email("Electricity@gmail.com").password("electricity1234")
				.build();

		Coupon coupon1 = Coupon.builder().companyID(1).category(Category.ELECTRICITY).title("Electricity")
				.description("get 50%").startDate(convertUtilDateToSQL(new Date(2020, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2021, 01, 01))).amount(AMOUNT).price(11).image("url").build();

		c1.setCoupons(generateCouponsForCompany(coupon1));

		Company c2 = Company.builder().name("Coca-cola").email("cola@gmail.com").password("cola1234").build();

		Coupon coupon2 = Coupon.builder().companyID(2).category(Category.FOOD).title("1+1").description("buy 1 get 2")
				.startDate(convertUtilDateToSQL(new Date(2020, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2021, 01, 01))).amount(AMOUNT).price(5.5).image("url").build();

		c2.setCoupons(generateCouponsForCompany(coupon2));

		Company c3 = Company.builder().name("Wiskey-bar").email("Wiskey@gmail.com").password("Wiskey1234").build();

		Coupon coupon3 = Coupon.builder().companyID(3).category(Category.RESTAURANT).title("Wiskey Bar")
				.description("Free Dessert").startDate(convertUtilDateToSQL(new Date(2020, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2021, 01, 01))).amount(AMOUNT).price(39.99).image("url").build();

		c3.setCoupons(generateCouponsForCompany(coupon3));

		Company c4 = Company.builder().name("Spa-Hotel").email("spa@gmail.com").password("spa1234").build();

		Coupon coupon4 = Coupon.builder().companyID(4).category(Category.SPA).title("Spa")
				.description("receive an hour and a half massage")
				.startDate(convertUtilDateToSQL(new Date(2020, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2021, 01, 01))).amount(AMOUNT).price(150).image("url").build();

		c4.setCoupons(generateCouponsForCompany(coupon4));

		Company c5 = Company.builder().name("Trivago").email("Trivago@gmail.com").password("trivago1234").build();

		Coupon coupon5 = Coupon.builder().companyID(5).category(Category.VACATION).title("Trivago")
				.description("chip upgrade").startDate(convertUtilDateToSQL(new Date(2020, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2021, 01, 01))).amount(AMOUNT).price(200).image("url").build();

		c5.setCoupons(generateCouponsForCompany(coupon5));

		companyDBDAO.addCompany(c1);
		checkCompanyMethod(c1.getName());
		companyDBDAO.addCompany(c2);
		checkCompanyMethod(c2.getName());
		companyDBDAO.addCompany(c3);
		checkCompanyMethod(c3.getName());
		companyDBDAO.addCompany(c4);
		checkCompanyMethod(c4.getName());
		companyDBDAO.addCompany(c5);
		checkCompanyMethod(c5.getName());

		System.out.println("all Companies as added Successfully!");
		space();
	}

	public List<Coupon> generateCouponsForCompany(Coupon coupon) {
		List<Coupon> coupons = new ArrayList<Coupon>();
		coupons.add(coupon);
		return coupons;

	}

	public void checkCompanyMethod(String name) {
		System.out.println("the Company:" + name + " as added Successfully!");
	}

	public void space() {
		System.out.println();
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println();
	}

}
