package web.front_end.guest.faq.dao;

import core.dao.CoreDao;
import web.front_end.guest.faq.entity.Faq;

public interface FaqDao extends CoreDao<Faq, Integer>{
	Faq selectByFaqTitle(String faqTitle);
}
