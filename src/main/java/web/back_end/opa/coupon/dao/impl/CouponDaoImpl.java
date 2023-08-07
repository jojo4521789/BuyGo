package web.back_end.opa.coupon.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.back_end.opa.coupon.dao.CouponDao;
import web.back_end.opa.coupon.entity.Coupon;

public class CouponDaoImpl implements CouponDao{

	@Override
	public int insert(Coupon coupon) {
		getSession().persist(coupon);
		return 1;
	}

	@Override
	public int deleteById(Integer opaCouponNo) {
		Session session = getSession();
		Coupon coupon = session.get(Coupon.class, opaCouponNo);
		session.remove(coupon);
		return 1;
	}

	@Override
	public int update(Coupon coupon) {
		Session session = getSession();
		Coupon oldCoupon = session.get(Coupon.class, coupon.getOpaCouponNo());
		final String opaCouponName = coupon.getOpaCouponName();
		if(opaCouponName != null) {
			oldCoupon.setOpaCouponName(opaCouponName);
		}
		final Integer opaDiscountAmo = coupon.getOpaDiscountAmo();
		if(opaDiscountAmo != null) {
			oldCoupon.setOpaDiscountAmo(opaDiscountAmo);
		}
		final Integer opaMinAmount = coupon.getOpaMinAmount();
		if(opaMinAmount != null) {
			oldCoupon.setOpaMinAmount(opaMinAmount);
		}
		final Timestamp opaExpDate = coupon.getOpaExpDate();
		if(opaExpDate != null) {
			oldCoupon.setOpaExpDate(opaExpDate);
		}
		return 1;
	}

	@Override
	public Coupon selectById(Integer id) {
		return getSession().get(Coupon.class, id);
	}

	@Override
	public List<Coupon> selectAll() {
		final String hql = "FROM Coupon ORDER BY opaCouponNo";
		return getSession().createQuery(hql, Coupon.class).getResultList();
	}

	@Override
	public Coupon selectByOpaCouponName(String opaCouponName) {
		Query<Coupon> query = getSession().createQuery("FROM Coupon WHERE opaCouponName=" + opaCouponName, Coupon.class);
		return query.getSingleResult();
	}

}
