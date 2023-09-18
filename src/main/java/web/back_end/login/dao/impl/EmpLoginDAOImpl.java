package web.back_end.login.dao.impl;

import web.back_end.login.dao.EmpLoginDAO;
import web.back_end.user_acc.acc.entity.Emp;

public class EmpLoginDAOImpl implements EmpLoginDAO {

	@Override
	public Emp selectByEmpNo(Integer empNo) {
		return getSession().get(Emp.class, empNo);
	}

	@Override
	public Emp selectByAcct(String empAcct) {
		final String hql = "FROM Emp WHERE empAcct = :empAcct ORDER BY empno"; 
		return getSession()
				.createQuery(hql, Emp.class)
				.setParameter("empAcct", empAcct)
				.uniqueResult();
	}

	@Override
	public Emp selectByEmailEmp(String empEmail) {
		final String hql = "FROM Emp WHERE EmpEmail = :empEmail ORDER BY empno";
		return getSession()
				.createQuery(hql, Emp.class)
				.setParameter("empEmail", empEmail)
				.uniqueResult();
	}

	@Override
	public Emp selectByAcctPw(String empAcct, String empPw) {
		final String hql = "FROM Emp WHERE empAcct = : empAcct AND empPw = :empPw ORDER BY empNo";
		return getSession()
				.createQuery(hql, Emp.class)
				.setParameter("empAcct", empAcct)
				.setParameter("empPw", empPw)
				.uniqueResult();
	}

	@Override
	public Emp selectByEmpFun(Integer funNo) {
		final String hql = "FROM Emp WHERE empFun = : empFun ORDER BY empno";
		return getSession()
				.createQuery(hql, Emp.class)
				.setParameter("funNo", funNo)
				.uniqueResult();
	}

}
