package web.front_end.member.black.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.black.entity.Blacklist;

public interface BlacklistDao extends CoreDao<Blacklist, Integer> {
	List<Blacklist> selectByMemberNo(Integer memberNo);
	int deleteByMemberNoAndMemberNoBlack(Integer memberNo, Integer memberNoBlack);
}
