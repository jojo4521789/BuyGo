package web.front_end.seller.gpa.order.service.impl;

import java.util.List;

import web.front_end.seller.gpa.order.dao.GpaSoDao;
import web.front_end.seller.gpa.order.dao.impl.GpaSoDaoImpl;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.order.service.GpaSoService;

public class GpaSoServiceImpl implements GpaSoService{
	private GpaSoDao dao;
	
	public GpaSoServiceImpl(){
		dao = new GpaSoDaoImpl();
	}

	@Override
	public List<GpaSo> loadBySoStat(Integer soStat) {
		return dao.selectBySoStat(soStat);
	}
}
