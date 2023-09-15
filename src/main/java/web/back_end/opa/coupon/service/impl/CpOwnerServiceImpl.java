package web.back_end.opa.coupon.service.impl;

import java.util.List;

import web.back_end.opa.coupon.dao.CpOwnerDao;
import web.back_end.opa.coupon.dao.impl.CpOwnerDaoImpl;
import web.back_end.opa.coupon.entity.CpOwner;
import web.back_end.opa.coupon.entity.CpOwnerId;
import web.back_end.opa.coupon.service.CpOwnerService;

public class CpOwnerServiceImpl implements CpOwnerService{

	private CpOwnerDao dao;
	
	public CpOwnerServiceImpl() {
		dao = new CpOwnerDaoImpl();
	}
	
	@Override
	public CpOwner add(CpOwner cpOwner) {
		final int resultCount = dao.insert(cpOwner);
		if(resultCount < 1) {
			cpOwner.setMessage("新增優惠券持有者明細錯誤，請聯絡管理員!");
			cpOwner.setSuccessful(false);
			return cpOwner;
		}
		cpOwner.setMessage("新增優惠券持有者成功");
		cpOwner.setSuccessful(true);
		return cpOwner;
	}

	@Override
	public CpOwner update(CpOwner cpOwner) {
		final int resultCount = dao.update(cpOwner);
		cpOwner.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		cpOwner.setSuccessful(resultCount > 0);
		return cpOwner;
	}

	@Override
	public List<CpOwner> findAll() {
		return dao.selectAll();
	}

	@Override
	public List<CpOwner> selectByMember(Integer memberNo) {
		return dao.selectByMember(memberNo);
	}

	@Override
	public boolean remove(CpOwnerId cpOwnerId) {
		return dao.deleteById(cpOwnerId) > 0;
	}

	@Override
	public CpOwner selectByMemberAndCouponNo(CpOwnerId cpOwnerId) {
		return dao.selectById(cpOwnerId);
	}

}
