package web.front_end.member.gpa.order.Service.impl;

import java.util.List;

import web.front_end.member.gpa.order.Service.GpaProdRpService;
import web.front_end.member.gpa.order.dao.GpaProdRpDao;
import web.front_end.member.gpa.order.dao.impl.GpaProdRpDaoImpl;
import web.front_end.member.gpa.order.entity.GpaProdRp;

public class GpaProdRpServiceImpl implements GpaProdRpService{
	private GpaProdRpDao dao;
	public GpaProdRpServiceImpl() {
		dao = new GpaProdRpDaoImpl();
	}
	@Override
	public GpaProdRp increse(GpaProdRp gpaProdRp) {
		final int reasultCount = dao.insert(gpaProdRp);
		if(reasultCount != 1) {
			gpaProdRp.setMessage("新增失敗");
			gpaProdRp.setSuccessful(false);
		}
		gpaProdRp.setMessage("新增成功");
		gpaProdRp.setSuccessful(true);
		return gpaProdRp;
	}

	@Override
	public List<GpaProdRp> selectAll(GpaProdRp gpaProdRp) {
		
		return dao.selectAll();
	}
	
}
