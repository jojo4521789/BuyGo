package web.front_end.member.pa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.order.entity.PaSoDetails;

public interface PaSoDetailsDAO extends CoreDao<PaSoDetails, Integer>{
	int updateStatus(Integer paSoNo, Integer paProdNo, Integer status);
	PaSoDetails selectBySoNoAndProdNo(Integer paSoNo, Integer paProdNo);
}
