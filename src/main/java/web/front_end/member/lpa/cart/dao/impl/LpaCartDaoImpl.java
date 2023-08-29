package web.front_end.member.lpa.cart.dao.impl;

import java.util.List;
import java.sql.Timestamp;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.lpa.cart.dao.LpaCartDao;
import web.front_end.member.lpa.cart.entity.LpaCart;

public class LpaCartDaoImpl implements LpaCartDao {

	@Override
	public int insert(LpaCart lpaCart) {
		getSession().persist(lpaCart);
		return 1;
	}

	@Override
	//刪除商品連線限時連線商品編號
	public int deleteById(Integer lpaProdNo) {
		Session session = getSession();
		LpaCart lpaCart = session.get(LpaCart.class, lpaProdNo);
		session.remove(lpaCart);
		return 1;
	}

	@Override
	public int update(LpaCart lpaCart) {
		Session session = getSession();
		LpaCart oldlpaCart = session.get(LpaCart.class, lpaCart.getLpaProdNo());
		
		final Integer memberNo = lpaCart.getMemberNo();
		if(memberNo != null) {
			oldlpaCart.setMemberNo(memberNo);
		}
			
		final Integer lpaProdPrice = lpaCart.getLpaProdPrice();
		if(lpaProdPrice != null) {
			oldlpaCart.setLpaProdPrice(lpaProdPrice);
		}
		final Integer lpaCartQty = lpaCart.getLpaCartQty();
		if(lpaCartQty != null) {
			oldlpaCart.setLpaCartQty(lpaCartQty);
		}
		
		return 1;
	}

	@Override
	public LpaCart selectById(Integer lpaProdNo) {
		return getSession().get(LpaCart.class, lpaProdNo);
		
	}

	@Override
	public List<LpaCart> selectAll() {
		final String hql = "FROM LpaCart ORDER BY lpaProdNo";//查詢語句
		return getSession().createQuery(hql, LpaCart.class).getResultList();
	}

	@Override
	public LpaCart SelectByLpaCartName(String lpaCartName) {
		// TODO Auto-generated method stub
		return null;
	}

}
