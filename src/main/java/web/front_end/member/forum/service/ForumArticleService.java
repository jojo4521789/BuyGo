package web.front_end.member.forum.service;

import java.util.List;

import core.service.CoreService;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;
import web.front_end.member.forum.dto.ForumArticleDTO;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;

public interface ForumArticleService extends CoreService {
	
	List<ForumArticle> loadForumArticleByArticleNo(Integer articleNo);
	
	List<ForumArticle> loadForumArticleBymemberNo(Integer memberNo);
	
	ForumArticle add(Integer memberNo , ForumArticle forumArticle);
	
	ForumArticle update(ForumArticle forumArticle);
	
	List<ForumArticle> finAll();
	
	List<ForumArticleDTO> loadAllArticleInfo(int selfMemeberNo);//DTO寫法
	
	boolean remove(Integer articleNo);
	
	boolean updateByArticleNo(Integer articleNo, Integer articleStatus);
	
	
	List<ForumArticle> loadForumArticleBymemberNo_test(Integer memberNo);

}
