package web.front_end.guest.news.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.guest.news.entity.News;

public interface NewsService extends CoreService{
	News increse(News news);
	boolean remove(Integer news);
	List<News> selectAll();
	List<News> selectByinput(String input);
	
}
