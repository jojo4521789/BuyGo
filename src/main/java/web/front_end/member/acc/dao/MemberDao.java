package web.front_end.member.acc.dao;


import core.dao.CoreDao;
import web.front_end.member.acc.entity.Member;

public interface MemberDao extends CoreDao<Member,Integer>{
	Member selectBymemberPhone(String memberPhone);

}
