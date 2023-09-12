package web.front_end.member.pa.cart.service.impl;


import java.util.List;

import web.front_end.member.pa.cart.dao.PaCartDAO;
import web.front_end.member.pa.cart.dao.impl.PaCartDAOImpl;
import web.front_end.member.pa.cart.entity.PaCart;
import web.front_end.member.pa.cart.entity.PaCartId;
import web.front_end.member.pa.cart.service.PaCartService;

public class paCartServiceImpl implements PaCartService{
	
	private PaCartDAO dao;
	
	public paCartServiceImpl() {
		dao = new PaCartDAOImpl();
	}
	
	
	@Override
	public PaCart add(PaCart paCart) {
		if(paCart.getPaOrdQty() == null) {
			paCart.setMessage("未輸入商品數量");
			paCart.setSuccessful(false);
			return paCart;
		}
		final int resultCount = dao.insert(paCart);
		if(resultCount < 1) {
			paCart.setMessage("新增商品置購物車發生錯誤，請聯繫管理員!");
			paCart.setSuccessful(false);
			return paCart;
		}
		paCart.setMessage("於購物車新增商品成功");
		paCart.setSuccessful(true);
		return paCart;
	}
	
	@Override
	public PaCart update(PaCart paCart) {
		final int resultCount = dao.update(paCart);
		paCart.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		paCart.setSuccessful(resultCount > 0);
		return paCart;
	}
	
	@Override
	public List<PaCart> findAll() {
		return dao.selectAll();
	}
	
	@Override
	public boolean remove(PaCartId paCartId) {
		return dao.deleteById(paCartId) > 0;
	}

	@Override
	public PaCart selectById(PaCartId paCartId) {
		return dao.selectById(paCartId);
	}
	
	@Override
	public List<PaCart> selectByMemberNo(Integer memberNo) {
		return dao.selectByMemberNo(memberNo);
	}
	
	//還要加上依照賣家選取的dao
	
	
	
}
