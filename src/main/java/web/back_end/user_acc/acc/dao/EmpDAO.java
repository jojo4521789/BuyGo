package web.back_end.user_acc.acc.dao;

import core.dao.CoreDao;
import web.back_end.user_acc.acc.entity.Emp;

public interface EmpDAO extends CoreDao<Emp, Integer> {
	Emp selectByEmpNo(String empno);
	Emp selectByEmpEmail(String empmail);
}
