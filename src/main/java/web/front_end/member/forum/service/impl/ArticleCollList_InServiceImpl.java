package web.front_end.member.forum.service.impl;

import java.util.List;
import web.front_end.member.forum.dao.ArticleCollList_InDao;
import web.front_end.member.forum.dao.impl.ArticleCollList_InDaoImpl;
import web.front_end.member.forum.entity.ArticleCollList_In;
import web.front_end.member.forum.service.ArticleCollList_InService;

public class ArticleCollList_InServiceImpl implements ArticleCollList_InService {

	private ArticleCollList_InDao dao;

	public ArticleCollList_InServiceImpl() {
		dao = new ArticleCollList_InDaoImpl();
	}

	@Override
	public List<ArticleCollList_In> loadArticleCollListBymemberNo(Integer memberNo) {
		return dao.selectByMemberNo(memberNo);

	}

}
