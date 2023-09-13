package web.front_end.seller.gpa.prod.service.impl;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.front_end.seller.gpa.prod.dao.GpaProdDao;
import web.front_end.seller.gpa.prod.dao.impl.GpaProdDaoImpl;
import web.front_end.seller.gpa.prod.entity.GpaProd;
import web.front_end.seller.gpa.prod.service.GpaProdService;

public class GpaProdServiceImpl implements GpaProdService{
	private GpaProdDao dao;
	
	public GpaProdServiceImpl() {
		dao = new GpaProdDaoImpl();
	}

	@Override
	public GpaProd loadByGpaProdNo(Integer gpaProdNo) {
		return dao.selectById(gpaProdNo);
	}

	@Override
	public List<GpaProd> randomLoadByGpaCatsNoAndFilterGpaProdNo(Integer gpaCatsNo, Integer gpaProdNo) {
		List<GpaProd> gpaProdList = dao.randomSelectByGpaCatsNo(gpaCatsNo);
		List<GpaProd> newGpaProdList = new LinkedList<GpaProd>();
		for(GpaProd gpaProd : gpaProdList) {
			if(gpaProd.getGpaProdNo() == gpaProdNo) { // 過濾指定的GpaProdNo
				continue;
			}
			Timestamp currentTime = new Timestamp(System.currentTimeMillis()); // 取得當前時間(timestamp)
			if(gpaProd.getGpaEndDate().before(currentTime)) { // 如果取得商品的揪團截止時間在當前時間之前(揪團已截止)，則不將此資料傳遞
				continue;
			}
			
			newGpaProdList.add(gpaProd);
		}
		return newGpaProdList;
	}

	@Override
	public boolean changeGpaPreProdByGpaProdNo(Integer gpaProdNo, Integer gpaPreProd) {
		int result = dao.updateGpaPreProdByGpaProdNo(gpaProdNo, gpaPreProd);
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}
}
