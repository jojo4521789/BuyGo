package web.front_end.seller.gpa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.black.entity.Blacklist;
import web.front_end.seller.gpa.order.entity.GpaSo;

public interface GpaSoDao extends CoreDao<GpaSo, Integer> {
	List<GpaSo> selectByMemberNo(Integer memberNo);
	
	List<GpaSo> selectBySoStat(Integer soStat);
}
