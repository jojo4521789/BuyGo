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
		if(prod.getOpaProdName() == null) {
			prod.setMessage("商品名稱未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getOpaPrcatsNo() == null) {
			prod.setMessage("商品類別未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getOpaProdStockQty() == null) {
			prod.setMessage("商品庫存數量未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getOpaProdPrice() == null) {
			prod.setMessage("商品價格未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getOpaProdContent() == null) {
			prod.setMessage("商品內容未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getOpaProdUrl() == null) {
			prod.setMessage("商品網址未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		
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
		return dao.SelectByOpaProdNameList(input);
	}


}
