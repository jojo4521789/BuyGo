package web.back_end.user_acc.acc.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.user_acc.acc.entity.Emp;

public interface EmpDAO extends CoreDao<Emp, Integer> {
	List<Emp> selectByEmpNo(Integer empno);
	Emp selectByEmpEmail(String empmail);
}
