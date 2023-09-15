package web.front_end.prodlist.service.Impl;

import web.front_end.member.acc.entity.Member;
import web.front_end.prodlist.dao.impl.paProdSellerDAOImpl;
import web.front_end.prodlist.service.ProdlistSellerService;

public class ProdlistSellerServiceImpl implements ProdlistSellerService{
	
	private paProdSellerDAOImpl dao;
	
	public ProdlistSellerServiceImpl() {
		dao = new paProdSellerDAOImpl();
	}
	
	@Override
	public Member getSellerName(Integer memberNo) {
		return dao.selectByMemberNo(memberNo);
		
	}
	

}
