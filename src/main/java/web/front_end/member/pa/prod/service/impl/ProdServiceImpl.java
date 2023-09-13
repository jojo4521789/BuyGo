package web.front_end.member.pa.prod.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import web.front_end.member.pa.prod.dao.PaProdDAO;
import web.front_end.member.pa.prod.dao.impl.PaProdDAOImpl;
import web.front_end.member.pa.prod.entity.PaProd;
import web.front_end.member.pa.prod.service.ProdService;
@Service
public class ProdServiceImpl implements ProdService {

	private PaProdDAO dao;
	
	public ProdServiceImpl() {
		dao = new PaProdDAOImpl();
	}
	
	@Override
	public PaProd insert(PaProd prod) {
//		if(prod.getPaProdNo( )== null) {
//			prod.setMessage("商品編號未輸入");
//			prod.setSuccessful(false);
//			return prod;
//		}
		if(prod.getPaProdObjNo() == null) {
			prod.setMessage("商品類別未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getPaProdStockQty() == null) {
			prod.setMessage("商品庫存數量未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getPaProdPrice() == null) {
			prod.setMessage("商品價格未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getPaProdContent() == null) {
			prod.setMessage("商品內容未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		if(prod.getPaProdName() == null) {
			prod.setMessage("商品名稱未輸入");
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
	public PaProd updata(PaProd prod) {
		final int resultCount = dao.update(prod);
		prod.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		prod.setSuccessful(resultCount > 0);
		return prod;
	}

	@Override
	public List<PaProd> findAll(Integer paprodno) {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer paprodno) {
		return dao.deleteById(paprodno) > 0;
	}

}
