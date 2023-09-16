package web.front_end.member.notification.service.impl;

import java.util.List;

import web.front_end.member.notification.dao.NotificationDao;
import web.front_end.member.notification.dao.impl.NotificationDaoImpl;
import web.front_end.member.notification.entity.Notification;
import web.front_end.member.notification.service.NotificationService;

public class NotificationServiceImpl implements NotificationService{
	private NotificationDao notifi_dao;
	
	public NotificationServiceImpl() {
		notifi_dao = new NotificationDaoImpl();
	}
	
	@Override
	public Notification arrival(Notification notification) {
		return null;
	}

	@Override
	public Notification like(Notification notification) {
		return null;
	}

	@Override
	public Notification welcome(Notification notification) {
		
		return null;
	}

	@Override
	public Notification forgetCart(Notification notification) {
		return null;
	}

	@Override
	public Notification OrderCheck(Notification notification) {
		return null;
	}

	@Override
	public Notification evaluate(Notification notification) {
		return null;
	}

	@Override
	public Notification noread(Notification notification) {
		return null;
	}

	@Override
	public boolean remove(Integer notifiNo) {
		return notifi_dao.deleteById(notifiNo)>0;
	}

	@Override
	public Integer insert(Notification notification) {
		return notifi_dao.insert(notification);
	}

	@Override
	public List<Notification> findall() {
		return notifi_dao.selectAll();
	}

}
