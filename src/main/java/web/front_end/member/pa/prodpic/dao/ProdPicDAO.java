package web.front_end.member.pa.prodpic.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.prodpic.entity.ProdPic;

public interface ProdPicDAO extends CoreDao<ProdPic, Integer> {
	List<ProdPic> selectByPicNo(Integer prodpicno);
}
