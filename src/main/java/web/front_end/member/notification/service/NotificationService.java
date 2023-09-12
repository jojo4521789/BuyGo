package web.front_end.member.notification.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.notification.entity.Notification;

public interface NotificationService extends CoreService {
	Notification arrival (Notification notification);  	//商品到貨
	Notification like (Notification notification);		//商品追蹤到期
	Notification welcome (Notification notification);	//歡迎加入會員(只在註冊成功後發出)
	Notification forgetCart (Notification notification);//購物車尚未結帳
	Notification OrderCheck (Notification notification);//成立訂單
	Notification evaluate (Notification notification);	//請評價商品
	Notification noread (Notification notification);
	boolean remove (Integer notifiNo);
	Integer insert(Notification notification);
	List<Notification> findall();
	
}
