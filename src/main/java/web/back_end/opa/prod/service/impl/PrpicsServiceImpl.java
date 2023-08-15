package web.back_end.opa.prod.service.impl;

import java.util.List;

import web.back_end.opa.prod.dao.PrpicsDao;
import web.back_end.opa.prod.dao.impl.PrpicsDaoImpl;
import web.back_end.opa.prod.entity.Prpics;
import web.back_end.opa.prod.service.PrpicsService;

public class PrpicsServiceImpl implements PrpicsService{

	private PrpicsDao dao;
	
	public PrpicsServiceImpl() {
		dao = new PrpicsDaoImpl();
	}
	
	@Override
	public Prpics add(Prpics prpics) {
		if(prpics.getOpaProdNo() == null) {
			prpics.setMessage("商品編號未輸入");
			prpics.setSuccessful(false);
			return prpics;
		}
		if(prpics.getOpaProdPicture() == null) {
			prpics.setMessage("商品照片未輸入");
			prpics.setSuccessful(false);
			return prpics;
		}
		
		final int resultCount = dao.insert(prpics);
		if(resultCount < 1) {
			prpics.setMessage("新增商品照片錯誤，請聯絡管理員!");
			prpics.setSuccessful(false);
			return prpics;
		}
		prpics.setMessage("新增商品照片成功");
		prpics.setSuccessful(true);
		return prpics;
	}

	@Override
	public Prpics update(Prpics prpics) {
		final int resultCount = dao.update(prpics);
		prpics.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		prpics.setSuccessful(resultCount > 0);
		return prpics;
	}

	@Override
	public List<Prpics> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer opaPrpicsNo) {
		return dao.deleteById(opaPrpicsNo) > 0;
	}

	@Override
	public List<Prpics> SelectByProdId(Integer opaProdNo) {
		return dao.SelectByOpaProdNo(opaProdNo);
	}

	
}
