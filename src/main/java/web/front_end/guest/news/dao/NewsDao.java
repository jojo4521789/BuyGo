package web.front_end.guest.news.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import core.dao.CoreDao;
import web.front_end.guest.news.entity.News;

@Repository
public interface NewsDao extends CoreDao<News, Integer>{
	List<News> selectByInput (String input);
	Integer selectByIncrese (String input);
}
