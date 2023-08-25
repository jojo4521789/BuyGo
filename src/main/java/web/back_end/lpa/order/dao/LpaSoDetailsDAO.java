package web.back_end.lpa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.lpa.order.entity.LpaSoDetails;

public interface LpaSoDetailsDAO extends CoreDao<LpaSoDetails, Integer>{
	int updateStatus(Integer lpaSoNo, Integer lpaProdNo, Integer status);
	LpaSoDetails selectBySoNoAndProdNo(Integer lpaSoNo, Integer lpaProdNo);
}
