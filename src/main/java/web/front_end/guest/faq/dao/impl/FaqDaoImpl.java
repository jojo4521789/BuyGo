package web.front_end.guest.faq.dao.impl;

import java.util.List;

import org.hibernate.Session;

import web.front_end.guest.faq.dao.FaqDao;
import web.front_end.guest.faq.entity.Faq;

public class FaqDaoImpl implements FaqDao {

	@Override
	public int insert(Faq entity) {
		getSession().persist(entity);
		return 1;
	}

	@Override
	public int deleteById(Integer faqNo) {
		Session session = getSession();
		Faq faq = session.get(Faq.class, faqNo);
		session.remove(faq);
		return 1;
	}

	@Override
	public int update(Faq faq) {
		Session session = getSession();
		Faq oldFaq = session.get(Faq.class,faq.getFaqNo());
		final String faqTitle = faq.getFaqTitle();
		if(faqTitle != null) {
			oldFaq.setFaqTitle(faqTitle);
		}
		final String faqContent = faq.getFaqContent();
		if(faqContent != null) {
			oldFaq.setFaqContent(faqContent);
		}
		return 1;
	}

	@Override
	public Faq selectById(Integer faqNo) {
		return getSession().get(Faq.class, faqNo);
	}

	@Override
	public List<Faq> selectAll() {
		final String hql = "FROM Faq ORDER BY FAQ_NO";
		return getSession().createQuery(hql,Faq.class).getResultList();
	}

	@Override
	public Faq selectByFaqTitle(String faqTitle) {
		
		return null;
	}

}
