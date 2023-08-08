package web.front_end.guest.news.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import web.front_end.guest.news.dao.NewsDao;
import web.front_end.guest.news.entity.News;

public class NewsDaoImpl implements NewsDao{

	@Override
	public int insert(News entity) {
		getSession().persist(entity);
		return 1;
	}

	@Override
	public int deleteById(Integer newsNo) {
		Session session = getSession();
		News news = session.get(News.class,newsNo);
		session.remove(news);
		return 1;
	}

	@Override
	public int update(News news) {
		Session session = getSession();
		News oldNews = session.get(News.class,news.getNewsNo());
		final String newsTitle = news.getNewsTitle();
		if(newsTitle != null) {
			oldNews.setNewsTitle(newsTitle);
		}
		final String newsContent = news.getNewsContent();
		 if(newsContent != null) {
			 oldNews.setNewsContent(newsContent);
		 }
		 final Timestamp newsTime = news.getNewsTime();
		 if(newsTime != null) {
			 oldNews.setNewsTime(newsTime);
		 }
		return 0;
	}

	@Override
	public News selectById(Integer newsNo) {
		return getSession().get(News.class,newsNo);
	}

	@Override
	public List<News> selectAll() {
		final String hql = "FROM News ORDER BY NEWS_NO";
		return getSession().createQuery(hql,News.class).getResultList();
	}

}
