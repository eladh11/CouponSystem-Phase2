package com.johnbryce.couponsystemphase2.daily;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.johnbryce.couponsystemphase2.beans.Coupon;
import com.johnbryce.couponsystemphase2.dbdao.CouponDBDAO;

@Component
public class CouponExpirationDailyJob implements Runnable {

	private static boolean quit = false;
	private static CouponDBDAO couponDBDAO = new CouponDBDAO();

	public CouponExpirationDailyJob() {

	}

	// implements the Runnable Method
	@Override
	public void run() {
		while (!quit) {
			List<Coupon> coupons = null;
			try {
				coupons = couponDBDAO.getAllCoupons();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			for (Coupon coupon : coupons) {
				if (coupon.getEndDate().before(new Date())) {
					// the time to delete the is come
					couponDBDAO.deleteCouponPurchaseByCouponID(coupon.getId());
					try {
						couponDBDAO.deleteCouponById(coupon.getId());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
			try {
				// Sleep to 1 minute except 1 day
				Thread.sleep(60 * 1000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void stopTheDailyThread() {
		quit = true;
	}

	public static void startTheDailyThread() {
		Thread thread = new Thread(new CouponExpirationDailyJob());
		thread.start();
	}
}
