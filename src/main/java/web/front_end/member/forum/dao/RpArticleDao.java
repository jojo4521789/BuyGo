package web.front_end.member.forum.dao;

import core.dao.CoreDao;
import web.front_end.member.forum.entity.RpArticle;

public interface RpArticleDao extends CoreDao<RpArticle, Integer> {
	int updateByRpArticleNo(Integer rpArticleNo, Integer articleStatus, Integer auditResult);
}
