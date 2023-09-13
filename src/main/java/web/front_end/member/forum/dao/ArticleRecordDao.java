package web.front_end.member.forum.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.forum.entity.ArticleRecord;
import web.front_end.member.forum.entity.ForumArticle;

public interface ArticleRecordDao extends CoreDao<ArticleRecord, Integer>{
	
	Integer queryCountByArticleNoAndRcordStat(int articleNo , int stat);
	
	ArticleRecord queryCountByArticleNoAndMemeberNo(int articleNo , int memberNo);

	int insert(ArticleRecord entity);
}
