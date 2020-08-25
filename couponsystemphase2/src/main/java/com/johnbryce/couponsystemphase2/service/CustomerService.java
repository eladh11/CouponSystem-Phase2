package com.johnbryce.couponsystemphase2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.johnbryce.couponsystemphase2.beans.Category;
import com.johnbryce.couponsystemphase2.beans.Coupon;
import com.johnbryce.couponsystemphase2.beans.Customer;
import com.johnbryce.couponsystemphase2.exception.IncorrectDetailsException;

import lombok.Setter;

@Service
@Setter
@Scope("prototype")
public class CustomerService extends ClientService {

	private int customerID;

	@Override
	public boolean login(String email, String password) {
		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getPassword().equals(password) && customer.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Customer login Successfully!");
				return true;
			}
		}
		System.out.println("Wrong Details...");
		return false;
	}

	public void purchaseCoupon(int customerID, int couponID) throws IncorrectDetailsException {

		// cannot buy the same coupon more than once
		List<Coupon> coupons = customerDBDAO.getOneCustomer(customerID).getCoupons();
		if (coupons != null) {
			for (Coupon coup : coupons) {
				if (coup.getId() == couponID) {
					throw new IncorrectDetailsException("cannot buy tha same coupon more than once...");
				}
			}
		}
		// cannot buy coupon that is amount=0
		Coupon c = couponDBDAO.getOneCoupon(couponID);
		if (c.getAmount() == 0) {
			throw new IncorrectDetailsException("cannot buy the coupon: amount = 0");
		}
		// cannot buy coupon if date expired
		if (c.getEndDate().before(new Date())) {
			throw new IncorrectDetailsException("the date of the coupon expired...");
		}
		// amount - 1
		System.out.println("coupon: " + c.getTitle() + " is available.");
		c.setAmount(c.getAmount() - 1);
		couponDBDAO.updateCoupon(c);
		couponDBDAO.addCouponPurchase(customerID, couponID);
		System.out.println("Enjoy your coupon :->");

	}

	public List<Coupon> getCustomerCoupons() {
		List<Coupon> coupons = customerDBDAO.getOneCustomer(this.customerID).getCoupons();
		if (coupons == null) {
			System.out.println("Coupons not found...");
			return null;
		}
		return coupons;
	}

	public List<Coupon> getCustoemrCoupons(Category category) {
		List<Coupon> idx = new ArrayList<Coupon>();
		List<Coupon> coupons = customerDBDAO.getOneCustomer(this.customerID).getCoupons();
		for (Coupon coupon : coupons) {
			if (coupons != null) {
				if (coupon.getCategory() == category) {
					idx.add(coupon);
				}
			}
		}
		return idx;
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon> idx = new ArrayList<Coupon>();
		List<Coupon> coupons = customerDBDAO.getOneCustomer(this.customerID).getCoupons();
		for (Coupon coupon : coupons) {
			if (coupons != null) {
				if (coupon.getPrice() <= maxPrice) {
					idx.add(coupon);
				}
			}
		}
		return idx;
	}

	public Customer getCustomerDetails() {
		Customer customer = customerDBDAO.getOneCustomer(this.customerID);
		if (customer.getCoupons() == null) {
			System.out.println("Customer:" + customer.getFirst() + " does not have Coupons");
			return customer;
		}
		return customer;
	}

	public int getCustomerID(String email) {
		return customerDBDAO.getCustomerID(email);
	}
}
