package web.back_end.opa.req.dao;

import web.back_end.opa.req.entity.OpaRequest;

import java.util.List;

import core.dao.CoreDao;

public interface OpaRequestDao extends CoreDao<OpaRequest, Integer> {

    OpaRequest getById(Integer id);

    List<OpaRequest> getAll();

    List<OpaRequest> getByStatus(Integer status);

    void saveOpaRequest(OpaRequest opaRequest);

    void updateOpaRequest(OpaRequest opaRequest);

    void deleteOpaRequest(OpaRequest opaRequest);
}
