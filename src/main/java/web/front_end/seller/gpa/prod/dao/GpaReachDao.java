package web.front_end.seller.gpa.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.entity.GpaReach;

public interface GpaReachDao extends CoreDao<GpaReach, Integer> {
	List<GpaReach> selectByProdNo(Integer ProdNo);
}
