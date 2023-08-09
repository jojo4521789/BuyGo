package web.front_end.seller.pa.req;

import java.util.List;

import core.dao.CoreDao;

public interface PaReqDAO extends CoreDao<PaReq, Integer>{
	
	public List<PaReq> selectAll();

	public PaReq selectByPaRqNo(PaReq paRqNo);
}
