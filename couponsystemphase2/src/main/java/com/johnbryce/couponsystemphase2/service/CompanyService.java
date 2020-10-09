package com.johnbryce.couponsystemphase2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
				this.companyID = company.getId();
				return true;
			}
		}
		System.out.println("Wrong Details...");
		return false;

	}

	public void addCoupon(Coupon coupon) throws IncorrectDetailsException {
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		for (Coupon coup : coupons) {
			if (coup.getTitle().equals(coupon.getTitle())) {
				throw new IncorrectDetailsException("Cannot be the same Title for the same Company.");
			}
		}
		couponDBDAO.addCoupon(coupon);
		System.out.println("Coupon:" + coupon.getTitle() + " as added Successfully!");

	}

	public void updateCoupon(Coupon coupon) {

		Optional<Coupon> idx = couponDBDAO.getOneCoupon(coupon.getId());
		if (coupon.getCategory() != null) {
			idx.get().setCategory(coupon.getCategory());
		}
		if (coupon.getTitle() != null) {
			idx.get().setTitle(coupon.getTitle());
		}
		if (coupon.getDescription() != null) {
			idx.get().setDescription(coupon.getDescription());
		}
		if (coupon.getStartDate() != null) {
			idx.get().setStartDate(coupon.getStartDate());
		}
		if (coupon.getEndDate() != null) {
			idx.get().setEndDate(coupon.getEndDate());
		}
		if (coupon.getAmount() > 0) {
			idx.get().setAmount(coupon.getAmount());
		}
		if (coupon.getPrice() > 0) {
			idx.get().setPrice(coupon.getPrice());
		}
		if (coupon.getImage() != null) {
			idx.get().setImage(coupon.getImage());
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

	public Optional<Coupon> getOneCoupon(int couponID) {
		return couponDBDAO.getOneCoupon(couponID);
	}

	public List<Coupon> getCompanyCoupons(int id) {
		List<Coupon> idx = new ArrayList<Coupon>();
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		for (Coupon coupon : coupons) {
			if (coupon.getCompanyID() == id) {
				idx.add(coupon);
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
		Optional<Company> comp = companyDBDAO.getOneCompany(this.companyID);
		if (comp.get().getCoupons() != null) {
			return comp.get();
		}
		System.out.println("the company:" + comp.get().getName() + " does not have coupons...");
		return comp.get();
	}

	public int getCompanyID(String email) {
		return companyDBDAO.getCompanyID(email);
	}
	public Company getOneCompanyByEmail(String email) {
		return companyDBDAO.getOneCompanyByEmail(email);
	}
	

}
