package web.front_end.member.lpa.cart.service.impl;

import java.util.List;
import web.front_end.member.lpa.cart.dao.LpaCartDao;
import web.front_end.member.lpa.cart.dao.impl.LpaCartDaoImpl;
import web.front_end.member.lpa.cart.entity.LpaCart;
import web.front_end.member.lpa.cart.service.LpaCartService;

public class LpaCartServiceImpl implements LpaCartService {
	private LpaCartDao dao;

	public LpaCartServiceImpl() {
		dao = new LpaCartDaoImpl();
	}

	@Override
	public LpaCart add(LpaCart lpaCart) {
		if (lpaCart.getLpaProdNo() == null) {
			lpaCart.setMessage("商品編號未輸入");
			lpaCart.setSuccessful(false);
			return lpaCart;
		}
		if (lpaCart.getLpaProdPrice() == null) {
			lpaCart.setMessage("商品價格未輸入");
			lpaCart.setSuccessful(false);
			return lpaCart;
		}
		if (lpaCart.getLpaCartQty() == null) {
			lpaCart.setMessage("商品庫存數量未輸入");
			lpaCart.setSuccessful(false);
			return lpaCart;
		}
		final int resultCount = dao.insert(lpaCart);
		if (resultCount < 1) {
			lpaCart.setMessage("新增購物車商品錯誤");
			lpaCart.setSuccessful(false);
			return lpaCart;
		}
		lpaCart.setMessage("新增購物車商品成功");
		lpaCart.setSuccessful(true);
		return lpaCart;
	}

	@Override
	public LpaCart update(LpaCart lpaCart) {
		final int resultCount = dao.update(lpaCart);
		lpaCart.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		lpaCart.setSuccessful(resultCount > 0);
		return lpaCart;
	}

	@Override
	public List<LpaCart> finall() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer lpaProdNo) {
		return dao.deleteById(lpaProdNo) > 0;
	}

}
