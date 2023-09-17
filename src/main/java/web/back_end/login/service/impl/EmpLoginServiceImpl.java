package web.back_end.login.service.impl;

import web.back_end.login.dao.EmpLoginDAO;
import web.back_end.login.dao.impl.EmpLoginDAOImpl;
import web.back_end.login.service.EmpLoginService;
import web.back_end.user_acc.acc.entity.Emp;

public class EmpLoginServiceImpl implements EmpLoginService {
	private EmpLoginDAO dao;
	public EmpLoginServiceImpl() {
		dao = new EmpLoginDAOImpl();
	}
	
	
	@Override
	public boolean checkEmpAcctPw(String empAcct, String empPw) {
		if (dao.selectByAcctPw(empAcct, empPw) != null) {
			return true;
		} else {
			return false;			
		}
	}

	@Override
	public Emp LoadEmpByEmpNo(Integer empno) {
		return dao.selectByEmpNo(empno);
	}

	@Override
	public Emp LoadEmpByAcct(String empAcct) {
		return dao.selectByAcct(empAcct);
	}

	@Override
	public Emp LoadEmpFun(Integer empFun) {
		return dao.selectByEmpFun(empFun);
	}


	@Override
	public Emp LoadEmpAcctPw(String empAcct, String empPw) {
		return dao.selectByAcctPw(empAcct, empPw);
	}


//	@Override
//	public EmpFun LoadEmpFun(Integer empNo, Integer funNo) {
//		return dao.selectByEmpFun(funNo);
//	}

}
