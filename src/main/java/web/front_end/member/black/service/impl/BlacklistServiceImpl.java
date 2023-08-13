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
	public Blacklist addBlack(Blacklist blacklist) {
		final List<Blacklist> resultList = dao.selectByMemberNoAndMemberNoBlack(blacklist.getMemberNo(),blacklist.getMemberNoBlack());
		System.out.println("resultList.size():" + resultList.size());
		if(resultList.size() > 0) {
			blacklist.setMessage("已有重複的黑名單");
			blacklist.setSuccessful(false);
			return blacklist;
		}
		if(dao.insert(blacklist) != 1) {
			blacklist.setMessage("新增黑名單錯誤，請聯繫管理員");
			blacklist.setSuccessful(false);
			return blacklist;
		}
		blacklist.setMessage("新增黑名單成功");
		blacklist.setSuccessful(true);
		return blacklist;
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
