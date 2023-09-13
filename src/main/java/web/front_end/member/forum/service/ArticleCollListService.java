package web.front_end.member.forum.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.forum.dao.ArticleCollListDao;
import web.front_end.member.forum.dto.ArticleCollListDto;
import web.front_end.member.forum.dto.ForumArticleDTO;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;


public interface ArticleCollListService extends CoreService{
	
	List<ArticleCollList> loadArticleCollListBymemberNo(Integer memberNo);
	
	boolean remove(Integer memberNo, Integer articleNo);
	
	ArticleCollList add(Integer memberNo, ArticleCollList articleCollList);
	
	List<ArticleCollListDto> loadAllArticleInfo(int selfMemeberNo);//DTO寫法
	
}
