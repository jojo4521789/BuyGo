package web.front_end.member.pa.prodpic.dao;

import core.dao.CoreDao;
import web.front_end.member.pa.prodpic.entity.ProdPic;

public interface ProdPicDAO extends CoreDao<ProdPic, Integer> {
	ProdPic selectByPicNo(Integer prodpicno);
}
