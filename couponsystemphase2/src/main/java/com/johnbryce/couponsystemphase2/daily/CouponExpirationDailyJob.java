package com.johnbryce.couponsystemphase2.daily;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.johnbryce.couponsystemphase2.beans.Coupon;
import com.johnbryce.couponsystemphase2.dbdao.CouponDBDAO;

@Component
public class CouponExpirationDailyJob {

	@Autowired
	private CouponDBDAO couponDBDAO;

	// Sleep to 1 minute except 1 day
	@Scheduled(fixedRate = 60 * 1000*60*24)
	public void deleteCouponDaily() {
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		for (Coupon coupon : coupons) {
			if (coupon.getEndDate().before(new Date())) {
				couponDBDAO.deleteCouponPurchaseByCouponID(coupon.getId());
				try {
					couponDBDAO.deleteCouponById(coupon.getId());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

}
