package web.back_end.opa.coupon.dao.impl;

import java.util.List;

import web.back_end.opa.coupon.dao.CouponDao;
import web.back_end.opa.coupon.pojo.Coupon;

public class CouponDaoImpl implements CouponDao{

	@Override
	public int insert(Coupon coupon) {
		getSession().persist(coupon);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Coupon coupon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Coupon selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coupon selectByOpaCouponName(String opaCouponName) {
		// TODO Auto-generated method stub
		return null;
	}

}
