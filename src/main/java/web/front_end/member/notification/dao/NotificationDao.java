package web.front_end.member.notification.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.notification.entity.Notification;

public interface NotificationDao extends CoreDao<Notification, Integer>{
	List<Notification> selectBynotifiStatus (Integer notifiStatus);
}
