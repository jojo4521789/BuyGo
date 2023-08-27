package web.back_end.lpa.product.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import web.back_end.lpa.product.dao.LpaProdDAO;
import web.back_end.lpa.product.entity.LpaProd;

public class LpaProdDAOImpl implements LpaProdDAO {
	private static final long serialVersionUID = 1L;

	@Override
	public int insert(LpaProd entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(LpaProd entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LpaProd selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LpaProd> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public LpaProd selectByMemberNo(Integer memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
