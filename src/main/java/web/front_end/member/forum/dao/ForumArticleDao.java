package web.front_end.member.forum.dao;

import core.dao.CoreDao;
import web.front_end.member.forum.entity.ForumArticle;

public interface ForumArticleDao extends CoreDao<ForumArticle, Integer>{
	ForumArticle selectByForumArticleTitle(String articleTitle);
}
