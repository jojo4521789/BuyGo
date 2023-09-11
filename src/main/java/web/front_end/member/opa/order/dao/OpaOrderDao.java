package web.front_end.member.opa.order.dao;

import core.dao.CoreDao;
import web.front_end.member.opa.order.entity.OpaOrder;

import java.util.List; 

public interface OpaOrderDao extends CoreDao<OpaOrder, Integer> {

	public List<OpaOrder> selectAllByMember(int memberNo);
	public List<OpaOrder> selectAllByStatus(int memberNo, int status);
}