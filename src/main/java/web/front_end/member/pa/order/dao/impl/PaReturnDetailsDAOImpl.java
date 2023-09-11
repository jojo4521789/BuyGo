package web.front_end.member.pa.order.dao.impl;

import java.util.List;

import web.front_end.member.pa.order.dao.PaReturnDetailsDAO;
import web.front_end.member.pa.order.entity.PaReturnDetails;

public class PaReturnDetailsDAOImpl implements PaReturnDetailsDAO {

	@Override
	public int insert(PaReturnDetails paReturnDetails) {
		getSession().persist(paReturnDetails);
		return 1; // 回傳產生的識別值
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(PaReturnDetails entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaReturnDetails selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaReturnDetails> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
