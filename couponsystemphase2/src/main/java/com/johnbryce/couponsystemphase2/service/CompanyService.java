package com.johnbryce.couponsystemphase2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.johnbryce.couponsystemphase2.beans.Category;
import com.johnbryce.couponsystemphase2.beans.Company;
import com.johnbryce.couponsystemphase2.beans.Coupon;
import com.johnbryce.couponsystemphase2.exception.IncorrectDetailsException;

import lombok.Setter;

@Service
@Setter
@Scope("prototype")
public class CompanyService extends ClientService {

	private int companyID;

	@Override
	public boolean login(String email, String password) {
		List<Company> companies = companyDBDAO.getAllCompanies();
		for (Company company : companies) {
			if (company.getPassword().equals(password) && company.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Company login Successfully!");
				return true;
			}
		}
		System.out.println("Wrong Details...");
		return false;
	}

	public void addCoupon(Coupon coupon) throws IncorrectDetailsException {
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		for (Coupon coup : coupons) {
			if (coup.getCompanyID() == coupon.getCompanyID()) {
				throw new IncorrectDetailsException("Cannot be the same ID for the same Company.");
			}
			if (coup.getTitle().equals(coupon.getTitle())) {
				throw new IncorrectDetailsException("Cannot be the same Title for the same Company.");
			}
		}
		couponDBDAO.addCoupon(coupon);
		System.out.println("Coupon:" + coupon.getTitle() + " as added Successfully!");

	}

	public void updateCoupon(Coupon coupon) {

		Coupon idx = couponDBDAO.getOneCoupon(coupon.getId());
		if (coupon.getCategory() != null) {
			idx.setCategory(coupon.getCategory());
		}
		if (coupon.getTitle() != null) {
			idx.setTitle(coupon.getTitle());
		}
		if (coupon.getDescription() != null) {
			idx.setDescription(coupon.getDescription());
		}
		if (coupon.getStartDate() != null) {
			idx.setStartDate(coupon.getStartDate());
		}
		if (coupon.getEndDate() != null) {
			idx.setEndDate(coupon.getEndDate());
		}
		if (coupon.getAmount() > 0) {
			idx.setAmount(coupon.getAmount());
		}
		if (coupon.getPrice() > 0) {
			idx.setPrice(coupon.getPrice());
		}
		if (coupon.getImage() != null) {
			idx.setImage(coupon.getImage());
		}
		couponDBDAO.updateCoupon(coupon);
		System.out.println("Coupon:" + coupon.getTitle() + " as update Successfully!");
	}

	public void deleteCoupon(int couponID) {
		couponDBDAO.deleteCouponById(couponID);
	}

	public List<Coupon> getAllCoupons() {
		return couponDBDAO.getAllCoupons();
	}

	public Coupon getOneCoupon(int couponID) {
		return couponDBDAO.getOneCoupon(couponID);
	}

	public List<Coupon> getCompanyCoupons() {
		List<Coupon> idx = new ArrayList<Coupon>();
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		for (Coupon coupon : coupons) {
			if (coupons != null) {
				if (coupon.getId() == this.companyID) {
					idx.add(coupon);
				}
			}
		}
		return idx;
	}

	public List<Company> getAllCompanies() {
		return companyDBDAO.getAllCompanies();
	}

	public List<Coupon> getCompanyCoupons(Category category) {
		List<Coupon> idx = new ArrayList<Coupon>();
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		for (Coupon coupon : coupons) {
			if (coupons != null) {
				if (coupon.getCategory() == category) {
					idx.add(coupon);
				}
			}
		}
		return idx;
	}

	public List<Coupon> getCompanyCoupons(double maxPrice) {
		List<Coupon> idx = new ArrayList<Coupon>();
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		if (coupons != null) {
			for (Coupon coupon : coupons) {
				if (coupon.getPrice() <= maxPrice) {
					idx.add(coupon);
				}
			}
		}
		return idx;

	}

	public Company getCompanyDetails() {
		Company comp = companyDBDAO.getOneCompany(this.companyID);
		if (comp.getCoupons() != null) {
			return comp;
		}
		System.out.println("the company:" + comp.getName() + " does not have coupons...");
		return comp;
	}

	public int getCompanyID(String email) {
		return companyDBDAO.getCompanyID(email);
	}

}
