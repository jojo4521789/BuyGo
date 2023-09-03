package web.back_end.opa.req.service.impl;

import java.util.List;

import web.back_end.opa.req.dao.OpaRequestDao;
import web.back_end.opa.req.dao.impl.OpaRequestDaoImpl;
import web.back_end.opa.req.entity.OpaRequest;
import web.back_end.opa.req.service.OpaRequestService;

public class OpaRequestServiceImpl implements OpaRequestService {
	private OpaRequestDao opaRequestDao;
//    private NotificationDao notificationDao;

    public OpaRequestServiceImpl() {
        opaRequestDao = new OpaRequestDaoImpl();
//        notificationDao = new NotificationDao();
    }

    public List<OpaRequest> findAllRequests() {
        List<OpaRequest> requests = opaRequestDao.selectAll();
        return requests;
    }

    public boolean updateStatus(int id, int status) {
        OpaRequest request = opaRequestDao.selectById(id);
        if (request == null) {
            return false;
        }
        request.setOpaRequestStatus((byte) status);
        opaRequestDao.update(request);
        return true;
    }

    public boolean sendNotification(int id) {
        OpaRequest request = opaRequestDao.selectById(id);
        if (request == null) {
            return false;
        }
//        Notification notification = new Notification();
//        notification.setMemberNo(request.getMemberNo());
//        notification.setNotifiTitle("您的Opa申請已經處理");
//        notification.setNotifiContent("您的OPA申請已經處理完畢，請至會員中心查看");
//        notificationDao.save(notification);
        return true;
    }
}
