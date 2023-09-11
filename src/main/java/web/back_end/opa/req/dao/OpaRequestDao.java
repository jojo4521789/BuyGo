package web.back_end.opa.req.dao;

import web.back_end.opa.req.entity.OpaRequest;

import java.util.List;

import core.dao.CoreDao;

public interface OpaRequestDao extends CoreDao<OpaRequest, Integer> {

	public List<OpaRequest> selectAll();
	
}
