package web.back_end.opa.prod.service.impl;

import java.util.List;

import web.back_end.opa.prod.dao.PrcatsDao;
import web.back_end.opa.prod.dao.impl.PrcatsDaoImpl;
import web.back_end.opa.prod.entity.Prcats;
import web.back_end.opa.prod.service.PrcatsService;

public class PrcatsServiceImpl implements PrcatsService{

	private PrcatsDao dao;
	
	public PrcatsServiceImpl() {
		dao = new PrcatsDaoImpl();
	}
	
	@Override
	public Prcats add(Prcats prcats) {
		if(prcats.getOpaPrcatsName() == null) {
			prcats.setMessage("商品類別未輸入");
			prcats.setSuccessful(false);
			return prcats;
		}
		
		if(dao.SelectByOpaPrcatsName(prcats.getOpaPrcatsName()) != null) {
			prcats.setMessage("商品類別名稱重複");
			prcats.setSuccessful(false);
			return prcats;
		}
		
		final int resultCount = dao.insert(prcats);
		if(resultCount < 1) {
			prcats.setMessage("新增商品類別錯誤，請聯絡管理員!");
			prcats.setSuccessful(false);
			return prcats;
		}
		prcats.setMessage("新增商品類別成功");
		prcats.setSuccessful(true);
		return prcats;
	}

	@Override
	public Prcats update(Prcats prcats) {
		final int resultCount = dao.update(prcats);
		prcats.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		prcats.setSuccessful(resultCount > 0);
		return prcats;
	}

	@Override
	public List<Prcats> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer opaPrcatsNo) {
		return dao.deleteById(opaPrcatsNo) > 0;
	}

	@Override
	public List<Prcats> findPart(String input) {
		return dao.SelectByOpaPrcatsNameList(input);
	}
	
	
}
