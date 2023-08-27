package web.front_end.member.acc.dao;


import org.springframework.stereotype.Repository;

import core.dao.CoreDao;
import web.front_end.member.acc.entity.Member;
@Repository
public interface MemberDao extends CoreDao<Member,Integer>{
	Member selectBymemberPhone(String memberPhone);
	Member selectByMemberAcct(String memberAcct);
	Member selectByMemberEmail(String memberEmail);
}
