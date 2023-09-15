package web.front_end.seller.gpa.order.service.impl;

import java.util.LinkedList;
import java.util.List;

import web.front_end.seller.gpa.order.dao.GpaSoDao;
import web.front_end.seller.gpa.order.dao.impl.GpaSoDaoImpl;
import web.front_end.seller.gpa.order.dto.GpaSoAndGpaProdDTO;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.order.service.GpaSoService;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public class GpaSoServiceImpl implements GpaSoService{
	private GpaSoDao dao;
	
	public GpaSoServiceImpl(){
		dao = new GpaSoDaoImpl();
	}
	
	@Override
	public List<GpaSo> loadSoByMemberNoAndSoStat(Integer memberNo, Integer soStat) {
		return dao.selectByMemberNoAndSoStat(memberNo, soStat);
	}

	@Override
	public boolean updateGpaEvaSellerByGpaSoNo(Integer gpaSoNo, Integer gpaEvaSeller) {
		return dao.updateGpaEvaSellerByGpaSoNo(gpaSoNo, gpaEvaSeller);
	}
	
	@Override
	public boolean updateGpaEvaMemberByGpaSoNo(Integer gpaSoNo, Integer gpaEvaMember) {
		return dao.updateGpaEvaMemberByGpaSoNo(gpaSoNo, gpaEvaMember);
	}
	
	@Override
	public boolean updateGpaSoStatByGpaSoNo(Integer gpaSoNo, Integer gpaSoStat) {
		return dao.updateGpaSoStatByGpaSoNo(gpaSoNo, gpaSoStat);
	}

	@Override
	public List<GpaSoAndGpaProdDTO> loadGpaSoAndGpaProdDTOByGpaSoAndGpaProd(GpaSo gpaSo, GpaProd gpaProd) {
		return dao.selectByGpaSoAndGpaProd(gpaSo, gpaProd);
	}

	@Override
	public List<GpaSo> loadSoBySellerMemberNoAndSoStat(Integer memberNo, Integer gpaSoStat) {
		List<GpaSo> gpaSoList = dao.selectByGpaSoStat(gpaSoStat); // 依指定的gpaSoStat取回所有符合條件的訂單
		List<GpaSo> newGpaSoList = new LinkedList<GpaSo>(); 
		for(GpaSo gpaSo : gpaSoList) {
			Integer sellerMemberNo = gpaSo.getGpaProd().getMemberNo(); // 取得該訂單商品所屬賣家的memberNo
			if(sellerMemberNo.equals(memberNo)) { // 如果該訂單所屬賣家的memberNo等於指定的賣家memberNo
				newGpaSoList.add(gpaSo);
			}
		}
		return newGpaSoList;
	}

	@Override
	public Integer loadGpaSoCountByMemberNoAndSoStatAndSearchStr(Integer memberNo, Integer soStat, String searchStr) {
		return dao.selectGpaSoCountByMemberNoAndSoStatAndSearchStr(memberNo, soStat, searchStr);
	}
	
	@Override
	public Integer loadGpaSoCountBySellerMemberNoAndSoStatAndSearchStr(Integer memberNo, Integer soStat, String searchStr) {
		return dao.selectGpaSoCountBySellerMemberNoAndSoStatAndSearchStr(memberNo, soStat, searchStr);
	}

	@Override
	public List<GpaSo> loadByMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(Integer memberNo, Integer soStat, Integer limit,
			Integer offset, String searchStr) {
		List<GpaSo> gpaSoList = dao.selectByMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(memberNo, soStat, limit, offset, searchStr);
		return gpaSoList;
	}
	
	@Override
	public List<GpaSo> loadBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(Integer memberNo, Integer soStat, Integer limit,
			Integer offset, String searchStr) {
		List<GpaSo> gpaSoList = dao.selectBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStr(memberNo, soStat, limit, offset, searchStr);
		return gpaSoList;
	}

	@Override
	public Boolean addGpaSo(GpaSo gpaSo) {
		int resultNum = dao.insert(gpaSo);
		if(resultNum == 1) {
			return true;
		}
		else {
			return false;
		}
	}
}
