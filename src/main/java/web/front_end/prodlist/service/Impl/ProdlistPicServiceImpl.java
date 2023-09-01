package web.front_end.prodlist.service.Impl;

import java.util.List;

import web.front_end.prodlist.dao.impl.PaProdPicDAOImpl;
import web.front_end.prodlist.entity.PaProdPic;
import web.front_end.prodlist.service.ProdlistPicService;

public class ProdlistPicServiceImpl implements ProdlistPicService{
	
	private PaProdPicDAOImpl dao;

	public ProdlistPicServiceImpl() {
		dao = new PaProdPicDAOImpl();
	}
	
	@Override
	public List<PaProdPic> selectByPaProdId(Integer paProdNo) {
		return dao.SelectByPaProdNo(paProdNo);

	}
	
}
