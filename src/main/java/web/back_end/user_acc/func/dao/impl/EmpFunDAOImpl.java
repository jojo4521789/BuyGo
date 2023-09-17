package web.back_end.user_acc.func.dao.impl;

import java.util.List;

import org.hibernate.Session;

import web.back_end.user_acc.func.dao.EmpFunDAO;
import web.back_end.user_acc.func.entity.EmpFun;

public class EmpFunDAOImpl implements EmpFunDAO{

	@Override
	public int insert(EmpFun entity) {
		getSession().persist(entity);
		return 1;
	}

	@Override
	public int deleteById(Integer empFun) {
		Session session = getSession();
		EmpFun empfun = session.get(EmpFun.class,empFun);
		session.remove(empfun);
		return 1;
	}

	@Override
	public int update(EmpFun pojo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EmpFun selectById(Integer empFunNo) {
		return getSession().get(EmpFun.class, empFunNo);
	}

	@Override
	public List<EmpFun> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpFun selectByEmpFun(String empno) {
		// TODO Auto-generated method stub
		return null;
	}

}
