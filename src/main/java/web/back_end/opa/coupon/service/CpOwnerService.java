package web.back_end.opa.coupon.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.opa.coupon.entity.CpOwner;
import web.back_end.opa.coupon.entity.CpOwnerId;

public interface CpOwnerService extends CoreService{
	CpOwner add(CpOwner cpOwner);
	
	CpOwner update(CpOwner cpOwner);
	
	List<CpOwner> findAll();
	
	List<CpOwner> selectByMember(Integer memberNo);
	
	boolean remove(CpOwnerId cpOwnerId);
}
