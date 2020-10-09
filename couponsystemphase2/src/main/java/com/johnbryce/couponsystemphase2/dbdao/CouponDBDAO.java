package com.johnbryce.couponsystemphase2.dbdao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.johnbryce.couponsystemphase2.beans.Coupon;
import com.johnbryce.couponsystemphase2.repo.CouponRepository;

@Repository
public class CouponDBDAO {

	@Autowired
	private CouponRepository couponRepository;

	public void addCoupon(Coupon coupon) {
		couponRepository.save(coupon);
	}

	public void updateCoupon(Coupon coupon) {
		couponRepository.saveAndFlush(coupon);
	}

	public void deleteCoupon(Coupon coupon) {
		couponRepository.delete(coupon);
	}

	public void deleteCouponById(int id) {
		couponRepository.deleteById(id);
	}

	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}

	public Optional<Coupon> getOneCoupon(int couponID) {
		return couponRepository.findById(couponID);
	}

	public void addCouponPurchase(int customerID, int couponID) {
		couponRepository.addCouponPurchase(customerID, couponID);
	}

	public void deleteCouponPurchase(int customerID, int couponID) {
		couponRepository.deleteCouponPurchase(customerID, couponID);
	}

	public void deleteCouponPurchaseByCouponID(int couponID) {
		couponRepository.deleteCouponPurchaseByCouponID(couponID);
	}
}
