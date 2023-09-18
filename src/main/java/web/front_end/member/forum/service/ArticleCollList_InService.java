package web.front_end.member.forum.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.forum.dao.ArticleCollListDao;
import web.front_end.member.forum.dto.ArticleCollListDto;
import web.front_end.member.forum.dto.ForumArticleDTO;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ArticleCollList_In;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;


public interface ArticleCollList_InService extends CoreService{
	
	List<ArticleCollList_In> loadArticleCollListBymemberNo(Integer memberNo);
	

	
	
	
	
}
