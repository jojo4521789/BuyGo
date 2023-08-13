package web.back_end.opa.req.dao.impl;

import java.util.List;

import web.back_end.opa.req.dao.OpaRequestDao;
import web.back_end.opa.req.entity.OpaRequest;

public class OpaRequestDaoImpl implements OpaRequestDao {
	@SuppressWarnings("unchecked")
	@Override
    public List<OpaRequest> selectAll() {
        List<OpaRequest> list = getSession().createQuery("from " + this.getEntityClass().getName() + " order by opaRequestStatus, opaRequestNo desc").list();
        return list;
    }
    @Override
	public Class<OpaRequest> getEntityClass() {
		return OpaRequest.class;
	}
}