package web.front_end.member.eva.service.impl;

import web.front_end.member.acc.dao.MemberDao;
import web.front_end.member.acc.dao.impl.MemberDaoImpl;
import web.front_end.member.acc.entity.Member;
import web.front_end.member.eva.service.EvaMemberService;

public class EvaMemberServiceImpl implements EvaMemberService {
	private MemberDao dao;

	public EvaMemberServiceImpl() {
		dao = new MemberDaoImpl();
	}

	@Override
	public Member loadMemberByMemberNo(Integer memberNo) {
		return dao.selectById(memberNo);
	}
}
