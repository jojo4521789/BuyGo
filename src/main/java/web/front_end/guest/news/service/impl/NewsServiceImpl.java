package web.front_end.guest.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import web.front_end.guest.news.dao.NewsDao;
import web.front_end.guest.news.dao.impl.NewsDaoImpl;
import web.front_end.guest.news.entity.News;
import web.front_end.guest.news.service.NewsService;
@Service
public class NewsServiceImpl implements NewsService{
	
	private NewsDao dao;
	public NewsServiceImpl() {
		dao = new NewsDaoImpl();
	}
	@Override
	public News increse(News news) {
		if(news.getNewsTitle() == null) {
			news.setMessage("請輸入標題");
			news.setSuccessful(false);
			return news;
		}
		if(news.getNewsContent() == null) {
			news.setMessage("請輸入內容");
			news.setSuccessful(false);
			return news;
		}
		final int resultcount = dao.insert(news);
		if(resultcount != 1) {
			news.setMessage("新增錯誤");
			news.setSuccessful(false);
			return news;
		}
		news.setMessage("新增成功");
		news.setSuccessful(true);
		return news;
	}
	@Override
	public boolean remove(Integer news) {
		return dao.deleteById(news) > 0;
	}


	@Override
	public List<News> selectAll() {
		return dao.selectAll();
	}
	@Override
	public List<News> selectByinput(String input) {
		return dao.selectByInput(input);
	}

}
