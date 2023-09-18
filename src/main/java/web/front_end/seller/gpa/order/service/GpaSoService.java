package web.front_end.seller.gpa.order.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.seller.gpa.order.dto.GpaSoAndGpaProdDTO;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public interface GpaSoService extends CoreService{
	List<GpaSo> loadSoByMemberNoAndSoStat(Integer memberNo, Integer soStat);
	List<GpaSoAndGpaProdDTO> loadGpaSoAndGpaProdDTOByGpaSoAndGpaProd(GpaSo gpaSo, GpaProd gpaProd);
	boolean updateGpaEvaSellerByGpaSoNo(Integer gpaSoNo, Integer gpaEvaSeller);
	boolean updateGpaEvaMemberByGpaSoNo(Integer gpaSoNo, Integer gpaEvaMember);
	List<GpaSo> loadSoBySellerMemberNoAndSoStat(Integer memberNo, Integer gpaSoStat);
	boolean updateGpaSoStatByGpaSoNo(Integer gpaSoNo, Integer gpaSoStat);
	Integer loadGpaSoCountByMemberNoAndSoStatAndSearchStr(Integer memberNo, Integer soStat, String searchStr);
	Integer loadGpaSoCountBySellerMemberNoAndSoStatAndSearchStr(Integer memberNo, Integer soStat, String searchStr);
	List<GpaSo> loadByMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(Integer memberNo, Integer soStat, Integer limit, Integer offset, String searchStr);
	List<GpaSo> loadBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(Integer memberNo, Integer soStat, Integer limit, Integer offset, String searchStr);
	Boolean addGpaSo(GpaSo gpaSo);
}
