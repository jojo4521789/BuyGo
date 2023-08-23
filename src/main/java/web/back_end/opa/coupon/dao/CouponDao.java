package web.back_end.opa.coupon.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.opa.coupon.entity.Coupon;

public interface CouponDao extends CoreDao<Coupon, Integer>{
	Coupon selectByOpaCouponName(String opaCouponName);
	
	List<Coupon> selectByOpaCouponNameList(String input);
}
