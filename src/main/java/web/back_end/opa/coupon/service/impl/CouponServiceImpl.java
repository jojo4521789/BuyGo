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
		if(coupon.getOpaCouponName() == null) {
			coupon.setMessage("優惠券名稱未輸入");
			coupon.setSuccessful(false);
			return coupon;
		}
		if(coupon.getOpaDiscountAmo() == null) {
			coupon.setMessage("優惠券折扣金額未輸入");
			coupon.setSuccessful(false);
			return coupon;
		}
		if(coupon.getOpaMinAmount() == null) {
			coupon.setMessage("最低訂單金額未輸入");
			coupon.setSuccessful(false);
			return coupon;
		}
		if(coupon.getOpaExpDate() == null) {
			coupon.setMessage("優惠券有效期限未輸入");
			coupon.setSuccessful(false);
			return coupon;
		}
		
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
	public Coupon edit(Coupon coupon) {
		if(dao.selectByOpaCouponName(coupon.getOpaCouponName()) != null) {
			coupon.setMessage("優惠券名稱重複");
			coupon.setSuccessful(false);
			return coupon;
		}
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
	public boolean save(Coupon coupon) {
		return dao.update(coupon) > 0;
	}
	
}
