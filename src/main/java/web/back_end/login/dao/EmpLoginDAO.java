package web.back_end.login.dao;

import static core.util.HibernateUtil.getSessionFactory;

import org.hibernate.Session;

import web.back_end.user_acc.acc.entity.Emp;

public interface EmpLoginDAO {

	Emp selectByIdEmp (Integer empno);
	Emp selectByAcct (String empAcct);
	Emp selectByEmailEmp (String empEmail);
	Emp selectByAcctPw (String empAcct, String empPw);
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	};	
}
