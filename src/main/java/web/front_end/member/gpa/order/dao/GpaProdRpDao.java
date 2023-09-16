package web.front_end.member.gpa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.gpa.order.entity.GpaProdRp;
import web.front_end.member.gpa.order.entity.SelectProdRp;

public interface GpaProdRpDao extends CoreDao<GpaProdRp, Integer> {
	List<SelectProdRp> selectByProdNo ();
	List<GpaProdRp> selectByGPA ();
	List<GpaProdRp> selectByPA ();
	
}
