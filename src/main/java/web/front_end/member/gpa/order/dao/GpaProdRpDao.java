package web.front_end.member.gpa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.gpa.order.entity.GpaProdRp;

public interface GpaProdRpDao extends CoreDao<GpaProdRp, Integer> {
	List<GpaProdRp> selectByProdNo ();
	
}
