package web.front_end.member.forum.service.impl;

import java.util.Iterator;
import java.util.List;

import web.front_end.member.forum.dao.ForumArticleDao;
import web.front_end.member.forum.dao.impl.ForumArticleDaoImpl;
import web.front_end.member.forum.entity.ForumArticle;
import web.front_end.member.forum.service.ForumArticleService;

public class ForumArticleServiceImpl implements ForumArticleService {

	private ForumArticleDao dao;

	public ForumArticleServiceImpl() {
		dao = new ForumArticleDaoImpl();
	}

	@Override
	public ForumArticle add(Integer memberNo , ForumArticle forumArticle) {
		if (forumArticle.getArticleTitle() == null) {
			forumArticle.setMessage("文章標題未輸入");
			forumArticle.setSuccessful(false);
			return forumArticle;
		}
		if (forumArticle.getArticleContent() == null) {
			forumArticle.setMessage("文章內容未輸入");
			forumArticle.setSuccessful(false);
			return forumArticle;
		}

		forumArticle.setMemberNo(memberNo);
		forumArticle.setMessage("文章新增成功");
		forumArticle.setSuccessful(true);
		
		dao.insert(forumArticle);
		
		return forumArticle;
	}

	@Override
	public ForumArticle update(ForumArticle forumArticle) {
		final int resultCount = dao.update(forumArticle);
		forumArticle.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		forumArticle.setSuccessful(resultCount > 0);
		return forumArticle;
	}

	@Override
	public List<ForumArticle> finAll() {
		List<ForumArticle> lstArticles = dao.selectAll();
		for(ForumArticle article : lstArticles) {
			System.out.print(article.getArticleTitle() + " : " + article.getArticleContent());
		}
	
		return lstArticles;
	}

	@Override
	public boolean remove(Integer articleNo) {
		return dao.deleteById(articleNo) > 0;
	}

}
