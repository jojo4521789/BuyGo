package web.front_end.member.pa.prodpic.service.impl;

import java.util.List;

import web.front_end.member.pa.prodpic.dao.ProdPicDAO;
import web.front_end.member.pa.prodpic.dao.impl.ProdPicDAOImpl;
import web.front_end.member.pa.prodpic.entity.PaProdPic;
import web.front_end.member.pa.prodpic.service.ProdPicService;

public class ProdPicServiceImpl implements ProdPicService {

	private ProdPicDAO dao;
	
	public ProdPicServiceImpl() {
		dao = new ProdPicDAOImpl();
	}
	
	@Override
	public PaProdPic insert(PaProdPic prodpic) {
//		if(prodpic.getPaProdNo() == null) {
//			prodpic.setMessage("商品編號未輸入");
//			prodpic.setSuccessful(false);
//			return prodpic;
//		}
		if(prodpic.getPaProdPic() == null) {
			prodpic.setMessage("商品照片未輸入");
			prodpic.setSuccessful(false);
			return prodpic;
		}
		final int resultCount = dao.insert(prodpic);
		if(resultCount < 1) {
			prodpic.setMessage("新增商品照片錯誤，請聯絡管理員!");
			prodpic.setSuccessful(false);
			return prodpic;
		}
		prodpic.setMessage("新增商品照片成功");
		prodpic.setSuccessful(true);
		return prodpic;
	}

	@Override
	public PaProdPic update(PaProdPic prodpic) {
		final int resultCount = dao.update(prodpic);
		prodpic.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		prodpic.setSuccessful(resultCount > 0);
		return prodpic;
	}

	@Override
	public List<PaProdPic> SelectByProdId(Integer prodpicno) {
		return null;
	}

	@Override
	public List<PaProdPic> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer paprodno) {
		return dao.deleteById(paprodno) > 0;
	}

}
