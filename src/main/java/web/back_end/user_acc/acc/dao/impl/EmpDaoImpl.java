package web.back_end.user_acc.acc.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.back_end.user_acc.acc.dao.EmpDAO;
import web.back_end.user_acc.acc.entity.Emp;

public class EmpDaoImpl implements EmpDAO {

	@Override
	public int insert(Emp emp) {
		getSession().save(emp);;
		return 1;
	}

	@Override
	public int deleteById(Integer empAcct) {
		Session session = getSession();
		Emp emp = session.get(Emp.class,empAcct);
		session.remove(emp);
		return 1;
	}

	@Override
	public int update(Emp emp) {
		final StringBuilder hql = new StringBuilder().append("UPDATE Member SET");
		int offset = 0;
		final String pw = emp.getEmpPw();
		if(pw != null && !pw.isEmpty()) {
			hql.append("pw = :empPw,");
			offset = 1;
		}
		hql.append("EMP_NAME = :EmpName,")
		   .append("EMP_PHONE = :EmpPhone,")
		   .append("EMP_EMAIL = :EmpEmail,")
		   .append("EMP_GENDER = :EmpGender,")
		   .append("WHERE MEMBER_ACCT = :EmpAcct");
		Query<?> query = getSession().createQuery(hql.toString());
		if(pw != null && !pw.isEmpty()) {
			query.setParameter("MEMBER_PW", pw);
		}
		
		return query.setParameter("empName", emp.getEmpName())
					.setParameter("empPhone", emp.getEmpTel())
					.setParameter("empEmail", emp.getEmpMail())
					.setParameter("empGender", emp.getEmpGender())
					.executeUpdate();
	}

	@Override
	public Emp selectById(Integer empno) {
		return getSession().get(Emp.class, empno);
	}

	@Override
	public List<Emp> selectAll() {
		final String hql = "FROM emp ORDER BY empNo";
		return getSession().createQuery(hql, Emp.class).getResultList();
	}


	@Override
	public Emp selectByEmpEmail(String empmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emp> selectByEmpNo(Integer empno) {
		try {
			Query<Emp> query = getSession().createQuery("FROM Emp WHERE empNo = :empNo", Emp.class)
					.setParameter("empno", empno);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	
}
