package web.front_end.member.forum.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.forum.entity.RpArticleMsg;

public interface RpArticleMsgService extends CoreService {

	RpArticleMsg add(Integer rpmemberNo, RpArticleMsg rpArticlerpMsg);

	RpArticleMsg update(RpArticleMsg rpArticlerpMsg);

	List<RpArticleMsg> finAll();

	boolean remove(Integer rpMsgNo);

	boolean updateByRpArticleMsgNo(Integer rpMsgNo, Integer msgeStat, Integer auditResult);

}
