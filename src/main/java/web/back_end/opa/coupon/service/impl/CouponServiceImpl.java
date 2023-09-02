package web.back_end.opa.coupon.service.impl;

import java.util.List;

import web.back_end.opa.coupon.dao.CouponDao;
import web.back_end.opa.coupon.dao.impl.CouponDaoImpl;
import web.back_end.opa.coupon.entity.Coupon;
import web.back_end.opa.coupon.service.CouponService;

public class CouponServiceImpl implements CouponService{

	private CouponDao dao;
	
	public CouponServiceImpl() {
		dao = new CouponDaoImpl();
	}
	
	@Override
	public Coupon add(Coupon coupon) {
		if(dao.selectByOpaCouponName(coupon.getOpaCouponName()) != null) {
			coupon.setMessage("優惠券名稱重複");
			coupon.setSuccessful(false);
			return coupon;
		}
		
		final int resultCount = dao.insert(coupon);
		if(resultCount < 1) {
			coupon.setMessage("新增優惠券錯誤，請聯絡管理員!");
			coupon.setSuccessful(false);
			return coupon;
		}
		coupon.setMessage("新增優惠券成功");
		coupon.setSuccessful(true);
		return coupon;
	}

	@Override
	public Coupon update(Coupon coupon) {
		final int resultCount = dao.update(coupon);
		coupon.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		coupon.setSuccessful(resultCount > 0);
		return coupon;
	}

	@Override
	public List<Coupon> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer opaCouponNo) {
		return dao.deleteById(opaCouponNo) > 0;
	}

	@Override
	public List<Coupon> findPart(String input) {
		return dao.selectByOpaCouponNameList(input);
	}
}
