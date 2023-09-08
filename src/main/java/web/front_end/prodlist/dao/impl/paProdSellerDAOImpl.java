package web.front_end.prodlist.dao.impl;

import java.util.List;

import web.front_end.member.acc.entity.Member;
import web.front_end.prodlist.dao.paProdSellerDAO;

public class paProdSellerDAOImpl implements paProdSellerDAO{
	
	//用memberNo搜尋
	@Override
	public Member selectByMemberNo(Integer memberNo) {
	    return getSession().get(Member.class, memberNo);
	}

	@Override
	public int insert(Member entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Member entity) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Member> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
