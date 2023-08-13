package web.front_end.seller.pa.req;

import java.util.List;

public class PaReqDAOImpl implements PaReqDAO{

	@Override
	public int insert(PaReq paReq) {
		getSession().persist(paReq);
		return 1;
	}
	
	@Override
	public List<PaReq> selectAll() {
	    final String hql = "FROM PA_RQ_PROD_NAME";
	    return getSession().createQuery(hql, PaReq.class).getResultList();
	}
	
	public PaReq selectByPaRqNo(PaReq paRqNo) {
		return getSession().get(PaReq.class, paRqNo);
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int update(PaReq entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public PaReq selectById(Integer id) {
		return null;
	}

}
