package web.front_end.member.eva.service.impl;

import java.util.List;

import web.front_end.member.eva.dao.EvaGpaProdDao;
import web.front_end.member.eva.dao.impl.EvaGpaProdDaoImpl;
import web.front_end.member.eva.service.EvaGpaProdService;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public class EvaGpaProdServiceImpl implements EvaGpaProdService {
	private EvaGpaProdDao dao;

	public EvaGpaProdServiceImpl() {
		dao = new EvaGpaProdDaoImpl();
	}

	@Override
	public List<GpaProd> loadByMemberNo(Integer memberNo) {
		return dao.selectByMemberNo(memberNo);
	}
}
