package web.front_end.member.pa.cart.dao.impl;

import java.util.List;

import org.hibernate.Session;

import web.front_end.member.pa.cart.dao.PaCartDAO;
import web.front_end.member.pa.cart.entity.PaCart;

public class PaCartDAOImpl implements PaCartDAO{

	@Override
	public int insert(PaCart paCart) {
		getSession().persist(paCart);
		return 1;
	}

	@Override
	public int deleteByPaProdNo(PaCart paProdNo) {
		Session session = getSession();
		PaCart paCart = session.get(PaCart.class, paProdNo);
		session.remove(paCart);
		return 1;
	}

	@Override
	public int update(PaCart pojo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaCart selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaCart> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
