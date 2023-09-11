package web.back_end.user_acc.func.dao;

import core.dao.CoreDao;
import web.back_end.user_acc.func.entity.FunList;

public interface FunListDAO extends CoreDao<FunList, Integer> {
	
	FunList selectByFunList (String funno);
}
