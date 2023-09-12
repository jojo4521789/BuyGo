package web.front_end.prodlist.service.Impl;

import java.util.List;

import web.front_end.prodlist.dao.impl.PaProdlistDAOImpl;
import web.front_end.member.pa.prod.entity.PaProd;
import web.front_end.prodlist.service.ProdlistService;

public class ProdlistServiceImpl implements ProdlistService {

	private PaProdlistDAOImpl dao;

	public ProdlistServiceImpl() {
		dao = new PaProdlistDAOImpl();
	}

	@Override
	public PaProd ProdInfoSelectByNo(Integer paProdNo) {
		return dao.selectById(paProdNo);

	}
	
	@Override
	public List<PaProd> RangetProdNo(Integer limit, Integer paProdObjNo) {
		
		return dao.SelectByPaProdNo(paProdObjNo);
	}

}
