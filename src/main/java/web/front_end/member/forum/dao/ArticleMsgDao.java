package web.front_end.member.forum.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.forum.entity.ArticleMsg;

public interface ArticleMsgDao extends CoreDao<ArticleMsg, Integer> {
	
	List<ArticleMsg> selectByArticleNo(Integer articleNo);
	
	int deleteByIdAll(Integer articleNo);
}
