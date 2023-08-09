package web.front_end.member.pa.order.requst.dao;

import core.dao.CoreDao;
import web.front_end.member.pa.order.requst.entity.PARequest;

public interface PARequestDAO extends CoreDao<PARequest, Integer> {
	PARequest selectByPaRqNo(String parqno);
}
