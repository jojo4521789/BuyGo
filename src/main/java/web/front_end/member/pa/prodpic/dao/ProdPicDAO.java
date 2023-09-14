package web.front_end.member.pa.prodpic.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.prodpic.entity.PaProdPic;

public interface ProdPicDAO extends CoreDao<PaProdPic, Integer> {
	List<PaProdPic> selectByPicNo(Integer prodpicno);
	String selectProdFirstPic(Integer lpaProdNo);
}
