package web.back_end.login.dao;

import static core.util.HibernateUtil.getSessionFactory;

import org.hibernate.Session;

import web.back_end.user_acc.acc.entity.Emp;
import web.back_end.user_acc.func.entity.EmpFun;

public interface EmpLoginDAO {

	Emp selectByEmpNo (Integer empNo);
	Emp selectByAcct (String empAcct);
	Emp selectByEmailEmp (String empEmail);
	Emp selectByAcctPw (String empAcct, String empPw);
	Emp selectByEmpFun (Integer funNo);
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	};	
}
