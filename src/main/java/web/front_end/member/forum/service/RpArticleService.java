package web.front_end.member.forum.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.forum.entity.RpArticle;

public interface RpArticleService extends CoreService {
	
	RpArticle add(Integer rpmemberNo, RpArticle rpArticle);

	RpArticle update(RpArticle rpArticle);

	List<RpArticle> finAll();

	boolean remove(Integer reportArticleNo);
	
	boolean updateByRpArticleNo(Integer rpArticleNo, Integer articleStatus, Integer auditResult);
}
