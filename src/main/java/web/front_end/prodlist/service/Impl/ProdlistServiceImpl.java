package web.front_end.prodlist.service.Impl;

import web.front_end.prodlist.dao.impl.ProdlistDAOImpl;
import web.front_end.prodlist.entity.PaProdlist;
import web.front_end.prodlist.service.ProdlistService;

public class ProdlistServiceImpl implements ProdlistService {

	private ProdlistDAOImpl dao;

	public ProdlistServiceImpl() {
		dao = new ProdlistDAOImpl();
	}

	@Override
	public PaProdlist ProdInfo(Integer paProdNo) {
		return dao.selectByPaProdNo(paProdNo);

	}

}
