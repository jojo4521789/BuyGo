package web.front_end.prodlist.dao;

import core.dao.CoreDao;
import web.front_end.member.acc.entity.Member;

public interface paProdSellerDAO extends CoreDao<Member, Integer>{

	Member selectByMemberNo(Integer memberNo) ;

}
