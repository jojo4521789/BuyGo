package web.front_end.member.pa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.order.entity.PaReturn;

public interface PaReturnDAO extends CoreDao<PaReturn, Integer> {
	int updateRtnSeq(Integer nextSoId, String rtnSeq);
	List<PaReturn> selectByPaSoNo(Integer paSoNo);
	int updateReturnStatus(Integer paRtnNo, Integer newStatus);
}
