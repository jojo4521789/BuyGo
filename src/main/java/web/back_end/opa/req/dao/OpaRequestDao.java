package web.back_end.opa.req.dao;

import web.back_end.opa.req.entity.OpaRequest;

import java.util.List;
import java.io.Serializable;

import core.dao.CoreDao;

public interface OpaRequestDao extends CoreDao<OpaRequest, Integer> {

	public List<OpaRequest> selectAll();
    
    public Serializable save(OpaRequest entity);
    
    public List<OpaRequest> selectByMember(Integer memberNo);
}
