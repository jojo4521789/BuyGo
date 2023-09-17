package web.back_end.login.service;

import web.back_end.user_acc.acc.entity.Emp;
import web.back_end.user_acc.func.entity.EmpFun;

public interface EmpLoginService {

	boolean checkEmpAcctPw (String empAcct, String empPw);
	Emp LoadEmpByEmpNo (Integer empno);
	Emp LoadEmpByAcct (String empAcct);
	Emp LoadEmpFun (Integer empFun);
	Emp LoadEmpAcctPw (String empAcct, String empPw);
//	EmpFun LoadEmpFun(Integer empNo, Integer funNo);
	
}
