package web.front_end.member.forum.service;

import java.util.List;
import core.service.CoreService;
import web.front_end.member.forum.entity.ArticleMsg;

public interface ArticleMsgService extends CoreService {

	List<ArticleMsg> loadArticleMsgByArticleNo(Integer articleNo);

	ArticleMsg add(Integer memberNo, ArticleMsg articleMsg);

	ArticleMsg update(ArticleMsg articleMsg);

	List<ArticleMsg> finAll();

	boolean remove(Integer messageNumber);

}
