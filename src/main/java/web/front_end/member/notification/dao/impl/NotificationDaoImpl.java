package web.front_end.member.notification.dao.impl;

import java.util.List;

import org.hibernate.Session;

import web.front_end.member.notification.dao.NotificationDao;
import web.front_end.member.notification.entity.Notification;


public class NotificationDaoImpl implements NotificationDao{

	@Override
	public int insert(Notification entity) {
		getSession().persist(entity);
		return 1;
	}

	@Override
	public int deleteById(Integer notifiNo) {
		Session session = getSession();
		Notification notification = session.get(Notification.class, notifiNo);
		session.remove(notification);
		return 1;
	}

	@Override
	public int update(Notification entity) { 
		return 0;
	}

	@Override
	public Notification selectById(Integer id) {
		
		return null;
	}

	@Override
	public List<Notification> selectAll() {
		final String hql = "FROM Notification ORDER BY MEMBER_NO";
		return getSession().createQuery(hql,Notification.class).getResultList();
	}

	@Override
	public List<Notification> selectBynotifiStatus(Integer notifiStatus) {
		final String hql = "FROM Notification WHERE notifiStatus=" + notifiStatus;
		return getSession().createQuery(hql,Notification.class).getResultList();
	}

	

}
