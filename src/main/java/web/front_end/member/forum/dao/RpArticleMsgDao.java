package web.front_end.member.forum.dao;

import core.dao.CoreDao;
import web.front_end.member.forum.entity.RpArticleMsg;

public interface RpArticleMsgDao extends CoreDao<RpArticleMsg, Integer> {
	int updateByRpArticleMsgNo(Integer rpMsgNo, Integer msgeStat, Integer auditResult);
}
