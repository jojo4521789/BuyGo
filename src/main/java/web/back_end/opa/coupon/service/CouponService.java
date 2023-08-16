package web.back_end.opa.coupon.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.opa.coupon.entity.Coupon;

public interface CouponService extends CoreService{
	Coupon add(Coupon coupon);
	
	Coupon update(Coupon coupon);
	
	List<Coupon> findAll();
	
	boolean remove(Integer opaCouponNo);
}
