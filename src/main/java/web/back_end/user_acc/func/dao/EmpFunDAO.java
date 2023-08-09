package web.back_end.user_acc.func.dao;

import core.dao.CoreDao;
import web.back_end.user_acc.func.entity.EmpFun;

public interface EmpFunDAO extends CoreDao<EmpFun, Integer>{
	EmpFun selectByEmpFun (String empno);
	
}
