package web.front_end.member.opa.cart.service.impl;

import java.util.List;

import web.front_end.member.opa.cart.dao.OpaCartDao;
import web.front_end.member.opa.cart.dao.impl.OpaCartDaoImpl;
import web.front_end.member.opa.cart.entity.OpaCart;
import web.front_end.member.opa.cart.entity.OpaCartId;
import web.front_end.member.opa.cart.service.OpaCartService;

public class OpaCartServiceImpl implements OpaCartService{
	
	private OpaCartDao dao;
	
	public OpaCartServiceImpl() {
		dao = new OpaCartDaoImpl();
	}
	
	@Override
	public OpaCart add(OpaCart opaCart) {
		if(opaCart.getOpaCartProductsQty() == null) {
			opaCart.setMessage("商品數量未輸入");
			opaCart.setSuccessful(false);
			return opaCart;
		}
		final int resultCount = dao.insert(opaCart);
		if(resultCount < 1) {
			opaCart.setMessage("新增商品至購物車錯誤，請聯絡管理員!");
			opaCart.setSuccessful(false);
			return opaCart;
		}
		opaCart.setMessage("新增商品至購物車成功");
		opaCart.setSuccessful(true);
		return opaCart;
	}

	@Override
	public OpaCart update(OpaCart opaCart) {
		final int resultCount = dao.update(opaCart);
		opaCart.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		opaCart.setSuccessful(resultCount > 0);
		return opaCart;
	}

	@Override
	public List<OpaCart> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(OpaCartId opaCartId) {
		return dao.deleteById(opaCartId) > 0;
	}

	@Override
	public OpaCart selectById(OpaCartId opaCartId) {
		return dao.selectById(opaCartId);
	}

	@Override
	public List<OpaCart> selectByMemberNo(Integer memberNo) {
		return dao.selectByMemberNo(memberNo);
	}

}
