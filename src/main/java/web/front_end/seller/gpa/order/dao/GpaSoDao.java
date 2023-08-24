package web.front_end.seller.gpa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.seller.gpa.order.entity.GpaSo;

public interface GpaSoDao extends CoreDao<GpaSo, Integer> {
	List<GpaSo> selectByMemberNo(Integer memberNo);
	
	List<GpaSo> selectByMemberNoAndSoStat(Integer memberNo, Integer soStat);
	
	boolean updateGpaEvaSellerByGpaSoNo(Integer gpaSoNo, Integer gpaEvaSeller);
}
