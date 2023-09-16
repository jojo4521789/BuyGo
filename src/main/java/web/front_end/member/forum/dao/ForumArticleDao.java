package web.front_end.member.forum.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.forum.entity.ForumArticle;

public interface ForumArticleDao extends CoreDao<ForumArticle, Integer>{
	
	ForumArticle selectByForumArticleTitle(String articleTitle);
	
	List<ForumArticle> selectByArticleNo(Integer articleNo); 
	
	List<ForumArticle> selectByMemberNo(Integer memberNo); 
	
	List<ForumArticle> selectByMemberNo_test(Integer memberNo); 
	
	int updateByArticleNo(Integer articleNo, Integer articleStatus);
}
