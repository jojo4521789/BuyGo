package web.front_end.seller.pa.req.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.seller.pa.req.entity.PaReq;

public interface PaReqDAO extends CoreDao<PaReq, Integer>{
	
	public List<PaReq> selectAll();

	public PaReq selectByPaRqNo(PaReq paRqNo);
}
