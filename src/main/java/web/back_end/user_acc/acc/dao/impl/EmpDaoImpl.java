package web.back_end.user_acc.acc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.back_end.user_acc.acc.dao.EmpDAO;
import web.back_end.user_acc.acc.entity.Emp;

public class EmpDaoImpl implements EmpDAO {

	@Override
	public int insert(Emp entity) {
		getSession().persist(entity);
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
	public int update(Emp entity) {
		final StringBuilder hql = new StringBuilder().append("UPDATE Member SET");
		int offset = 0;
		final String pw = entity.getEmpPw();
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
		
		return query.setParameter("empName", entity.getEmpName())
					.setParameter("empPhone", entity.getEmpTel())
					.setParameter("empEmail", entity.getEmpMail())
					.setParameter("empGender", entity.getEmpGender())
					.executeUpdate();
	}

	@Override
	public Emp selectById(Integer empno) {
		return getSession().get(Emp.class, empno);
	}

	@Override
	public List<Emp> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emp selectByEmpNo(String empno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emp selectByEmpEmail(String empmail) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
