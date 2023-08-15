package web.front_end.seller.gpa.prod.service.impl;

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
}
