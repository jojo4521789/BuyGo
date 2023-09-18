package web.back_end.user_acc.func.service.impl;

import java.util.List;

import web.back_end.user_acc.func.dao.EmpFunDAO;
import web.back_end.user_acc.func.dao.impl.EmpFunDAOImpl;
import web.back_end.user_acc.func.entity.EmpFun;
import web.back_end.user_acc.func.service.EmpFunService;

public class EmpFunServiceImpl implements EmpFunService {
	
	private EmpFunDAO dao;
	public EmpFunServiceImpl() {
		dao = new EmpFunDAOImpl();
	}

	@Override
	public EmpFun loadEmpFun(Integer empFun) {
		return null;
	}

	@Override
	public List<EmpFun> findall() {
		return dao.selectAll();
	}

}
