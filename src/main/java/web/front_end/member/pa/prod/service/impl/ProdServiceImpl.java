package web.front_end.member.pa.prod.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import web.front_end.member.pa.prod.dao.ProdDAO;
import web.front_end.member.pa.prod.dao.impl.ProdDAOImpl;
import web.front_end.member.pa.prod.entity.Prod;
import web.front_end.member.pa.prod.service.ProdService;
@Service
public class ProdServiceImpl implements ProdService {

	private ProdDAO dao;
	
	public ProdServiceImpl() {
		dao = new ProdDAOImpl();
	}
	
	@Override
	public Prod add(Prod prod) {
		if(prod.getPaProdNo( )== null) {
			prod.setMessage("商品編號未輸入");
			prod.setSuccessful(false);
			return prod;
		}
		
		prod.setMessage("新增商品成功!");
		prod.setSuccessful(true);
		return prod;
	} 

	@Override
	public Prod updata(Prod prod) {
		final int resultCount = dao.update(prod);
		prod.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		prod.setSuccessful(resultCount > 0);
		return prod;
	}

	@Override
	public List<Prod> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer prodno) {
		return dao.deleteById(prodno) > 0;
	}

}
