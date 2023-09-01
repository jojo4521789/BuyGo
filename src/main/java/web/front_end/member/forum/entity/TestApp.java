package web.front_end.member.forum.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import core.util.HibernateUtil;

public class TestApp {
	public static void main(String[] arges) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		ForumArticle forumArticle = session.get(ForumArticle.class, 1);
		System.out.println(forumArticle.getArticleContent());
		HibernateUtil.shutdown();
	}

}
