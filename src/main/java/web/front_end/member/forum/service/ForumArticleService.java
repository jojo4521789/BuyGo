package web.front_end.member.forum.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.forum.entity.ForumArticle;

public interface ForumArticleService extends CoreService {
	ForumArticle add(Integer memberNo , ForumArticle forumArticle);
	
	ForumArticle update(ForumArticle forumArticle);
	
	List<ForumArticle> finAll();
	
	boolean remove(Integer articleNo);

}
