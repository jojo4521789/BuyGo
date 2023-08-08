package web.front_end.member.black.service.impl;

import java.util.List;

import web.front_end.member.black.dao.BlacklistDao;
import web.front_end.member.black.dao.impl.BlacklistDaoImpl;
import web.front_end.member.black.entity.Blacklist;
import web.front_end.member.black.service.BlacklistService;

public class BlacklistServiceImpl implements BlacklistService{
	private BlacklistDao dao;
	
	public BlacklistServiceImpl() {
		dao = new BlacklistDaoImpl();
	}

	@Override
	public boolean addBlack(Blacklist blacklist) {
		return dao.insert(blacklist) > 0;
	}

	@Override
	public List<Blacklist> loadBlacklistByMemberNo(Integer memberNo) {
		return dao.selectByMemberNo(memberNo);
	}

	@Override
	public boolean deleteBlacklistByMemberNoAndMemberNoBlack(Integer memberNo, Integer memberNoBlack) {
		return dao.deleteByMemberNoAndMemberNoBlack(memberNo, memberNoBlack) > 0;
	}
}
