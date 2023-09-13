package web.front_end.member.forum.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.forum.entity.ArticleCollList_In;


public interface ArticleCollList_InDao extends CoreDao<ArticleCollList_In, Integer>  {
	
	List<ArticleCollList_In> selectByMemberNo(Integer memberNo); 
	
}
