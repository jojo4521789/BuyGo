package web.front_end.guest.faq.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.guest.faq.entity.Faq;

public interface FaqDao extends CoreDao<Faq, Integer>{
	List<Faq> selectByInput (String input);
}
