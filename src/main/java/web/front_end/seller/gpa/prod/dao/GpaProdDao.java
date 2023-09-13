package web.front_end.seller.gpa.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.black.entity.Blacklist;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public interface GpaProdDao extends CoreDao<GpaProd, Integer> {
	List<GpaProd> selectByMemberNo(Integer memberNo);
	
	List<GpaProd> selectByProdName(String prodName);
	
	List<GpaProd> randomSelectByGpaCatsNo(Integer gpaCatsNo);
	
	int updateGpaPreProdByGpaProdNo(Integer gpaProdNo, Integer gpaPreProd);
}
