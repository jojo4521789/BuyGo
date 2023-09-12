package web.front_end.member.opa.order.dao;

import java.util.List; 
import java.io.Serializable;

import core.dao.CoreDao;
import web.front_end.member.opa.order.entity.*;

public interface OpaOrderDao extends CoreDao<OpaOrder, Integer> {

	public List<OpaOrder> selectAllByMember(int memberNo);
	public List<OpaOrder> selectAllByStatus(int memberNo, int status);
    public Serializable save(OpaOrder entity);
	public Serializable save(OpaOrderdetails entity);
}