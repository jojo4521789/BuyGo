package web.front_end.seller.gpa.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.seller.gpa.prod.entity.GpaProdPics;
import web.front_end.seller.gpa.prod.entity.GpaReach;

public interface GpaProdPicsDao extends CoreDao<GpaProdPics, Integer> {
	List<GpaProdPics> selectByProdNo(Integer ProdNo);
}
