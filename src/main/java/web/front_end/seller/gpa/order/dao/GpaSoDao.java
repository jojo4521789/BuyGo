package web.front_end.seller.gpa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.seller.gpa.order.dto.GpaSoAndGpaProdDTO;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public interface GpaSoDao extends CoreDao<GpaSo, Integer> {
	List<GpaSo> selectByMemberNo(Integer memberNo);
	
	List<GpaSo> selectByMemberNoAndSoStat(Integer memberNo, Integer soStat);
	
	List<GpaSoAndGpaProdDTO> selectByGpaSoAndGpaProd(GpaSo gpaSo, GpaProd gpaProd);
	
	boolean updateGpaEvaSellerByGpaSoNo(Integer gpaSoNo, Integer gpaEvaSeller);
	
	boolean updateGpaEvaMemberByGpaSoNo(Integer gpaSoNo, Integer gpaEvaMember);
	
	boolean updateGpaSoStatByGpaSoNo(Integer gpaSoNo, Integer gpaSoStat);
	
	List<GpaSo> selectByGpaSoStat(Integer gpaSoStat);
	
	Integer selectGpaSoCountByMemberNoAndSoStatAndSearchStr(Integer memberNo, Integer soStat, String searchStr);
	
	Integer selectGpaSoCountBySellerMemberNoAndSoStatAndSearchStr(Integer memberNo, Integer soStat, String searchStr);
	
	List<GpaSo> selectByMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(Integer memberNo, Integer soStat, Integer limit, Integer offset, String searchStr);
	
	List<GpaSo> selectBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(Integer memberNo, Integer soStat, Integer limit, Integer offset, String searchStr);
}
