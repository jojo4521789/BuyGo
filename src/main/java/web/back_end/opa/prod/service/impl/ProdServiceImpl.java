package web.back_end.opa.prod.service.impl;

import java.util.List;

import web.back_end.opa.prod.dao.ProdDao;
import web.back_end.opa.prod.dao.impl.ProdDaoImpl;
import web.back_end.opa.prod.entity.Prod;
import web.back_end.opa.prod.service.ProdService;

public class ProdServiceImpl implements ProdService{
	
	private ProdDao dao;
	
	public ProdServiceImpl() {
		dao = new ProdDaoImpl();
	}

	@Override
	public Prod add(Prod prod) {
		final int resultCount = dao.insert(prod);
		if(resultCount < 1) {
			prod.setMessage("新增商品錯誤，請聯絡管理員!");
			prod.setSuccessful(false);
			return prod;
		}
		
		prod.setMessage("新增商品成功!");
		prod.setSuccessful(true);
		return prod;
	}

	@Override
	public Prod update(Prod prod) {
		final int resultCount = dao.update(prod);
		prod.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		prod.setSuccessful(resultCount > 0);
		return prod;
	}

	@Override
	public Prod prodSelectById(Integer opaProdNo) {
		return dao.selectById(opaProdNo);
	}
	
	@Override
	public List<Prod> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer opaProdNo) {
		return dao.deleteById(opaProdNo) > 0;
	}

	@Override
	public List<Prod> findPart(String input) {
		return dao.selectByOpaProdName(input);
	}

	@Override
	public Prod updateProdStatus(Prod prod) {
		final int resultCount = dao.updateProdStatus(prod);
		prod.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		prod.setSuccessful(resultCount > 0);
		return prod;
	}

	@Override
	public List<Prod> getOnOffShelfProds(Integer opaProdStatus) {
		return dao.selectByOpaProdStatus(opaProdStatus);
	}

	@Override
	public List<Prod> findAllProdWithLimit(Integer limit, Integer offset) {
		return dao.selectProdWithLimit(limit, offset);
	}

	@Override
	public List<Prod> findByOpaProdNameWithLimit(String opaProdName, Integer limit, Integer offset) {
		return dao.selectByOpaProdNameWithLimit(opaProdName, limit, offset);
	}

	@Override
	public int getProdTotalQty() {
		return dao.getProdTotalQty();
	}

	@Override
	public int getProdTotalQtySelectByOpaProdName(String opaProdName) {
		return dao.getProdTotalQtySelectByOpaProdName(opaProdName);
	}

	@Override
	public List<Prod> getRandomProdsByPrcatsWithLimit(Integer opaProdNo, Integer opaPrcatsNo, Integer limit) {
		return dao.getRandomProdsByPrcatsWithLimit(opaProdNo, opaPrcatsNo, limit);
	}

	@Override
	public List<Prod> getRandomProdsWithLimit(Integer limit) {
		return dao.getRandomProdsWithLimit(limit);
	}

}
