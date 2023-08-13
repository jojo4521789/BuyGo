package web.front_end.member.black.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.black.entity.Blacklist;

public interface BlacklistService extends CoreService{
	Blacklist addBlack(Blacklist blacklist);
	List<Blacklist> loadBlacklistByMemberNo(Integer memberNo);
	boolean deleteBlacklistByMemberNoAndMemberNoBlack(Integer memberNo, Integer memberNoBlack);
}
