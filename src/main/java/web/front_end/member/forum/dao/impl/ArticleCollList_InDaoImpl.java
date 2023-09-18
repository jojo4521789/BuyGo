package web.front_end.member.forum.dao.impl;

import java.util.List;

import web.front_end.member.forum.dao.ArticleCollListDao;
import web.front_end.member.forum.dao.ArticleCollList_InDao;
import web.front_end.member.forum.dto.ArticleCollListDto;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ArticleCollList_In;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;

public class ArticleCollList_InDaoImpl implements ArticleCollList_InDao {

	@Override
	public List<ArticleCollList_In> selectByMemberNo(Integer memberNo) {
		final String hql = "FROM ArticleCollList_in WHERE memberNo = :memberNo";
		return getSession().createQuery(hql, ArticleCollList_In.class).setParameter("memberNo", memberNo)
				.getResultList();
	}

	@Override
	public int insert(ArticleCollList_In entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ArticleCollList_In entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArticleCollList_In selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleCollList_In> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
