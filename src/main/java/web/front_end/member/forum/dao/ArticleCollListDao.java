package web.front_end.member.forum.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.forum.dto.ArticleCollListDto;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ForumArticle;

public interface ArticleCollListDao extends CoreDao<ArticleCollList, Integer>  {
	
	List<ArticleCollList> selectByMemberNo(Integer memberNo); 
	
	int deleteById(Integer memberNo , Integer articleNo);
	
	int selectById(Integer memberNo , Integer articleNo);

}
