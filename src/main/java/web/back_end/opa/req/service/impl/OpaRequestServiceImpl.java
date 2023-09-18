package web.back_end.opa.req.service.impl;

import java.util.List;
import java.sql.Timestamp;

import web.back_end.opa.req.dao.OpaRequestDao;
import web.back_end.opa.req.dao.impl.OpaRequestDaoImpl;
import web.back_end.opa.req.entity.OpaRequest;
import web.back_end.opa.req.service.OpaRequestService;
import web.front_end.member.notification.entity.Notification;


public class OpaRequestServiceImpl implements OpaRequestService {
    public static String [] failedReasonMap = new String[] { "藥品, 醫療器材", "酒類 / 菸類商品", "武器 / 彈藥 / 軍事用品", "活體動物, 保育動物及其製品", "導向外部資訊或交易", "此商品可能令人感到不適或違反善良風俗","仿冒品","重覆刊登/複製他人商品圖文","違反鑑賞期","疫區/國外肉製, 蛋, 海鮮, 寵物食品","刷單製造不實銷量","濫用文字誤導搜尋","NCC/BSMI認證字號未填寫","誇大不實療效/涉及醫療效能","其他","無庫存" };
	private OpaRequestDao opaRequestDao;

    public OpaRequestServiceImpl() {
        opaRequestDao = new OpaRequestDaoImpl();
    }

    public Integer save(OpaRequest request) {
        return (Integer) opaRequestDao.save(request);
    }

    public List<OpaRequest> findAllRequests() {
        List<OpaRequest> requests = opaRequestDao.selectAll();
        return requests;
    }

     public List<OpaRequest> findAllRequestsByMember(int memberNo) {
        List<OpaRequest> requests = opaRequestDao.selectByMember(memberNo);
        return requests;
    }

    public boolean updateStatus(int id, int status, Integer failed) {
        OpaRequest request = opaRequestDao.selectById(id);
        if (request == null) {
            return false;
        }
        request.setOpaRequestStatus((byte) status);
        if(failed != null)
            request.setOpaFailedReason(failed);
        opaRequestDao.update(request);
        return true;
    }

    public Notification getNotification(int id) throws RuntimeException {
        OpaRequest request = opaRequestDao.selectById(id);
        if(request == null)
            throw new RuntimeException("Request not found");
        if(request.getOpaRequestStatus() == 1) {
            String message = "委託單#" + String.valueOf(id) + "審核通過, 請前往下單";
            Notification notification = new Notification(0);
            notification.setNotifiTitle("委託單審核通過");
            notification.setNotifiContent(message);
            notification.setMemberNo(request.getMemberNo());
            notification.setNotifiTime(new Timestamp(System.currentTimeMillis()));
            return notification;
        } else if(request.getOpaRequestStatus() == 2) {
            Integer opaFailedReason = request.getOpaFailedReason();
            if(opaFailedReason == null)
                throw new RuntimeException("No request found");

            String message = "委託單#" + String.valueOf(id) + "審核不通過, 原因: " + failedReasonMap[opaFailedReason];
            Notification notification = new Notification(0);
            notification.setNotifiTitle("委託單審核不通過");
            notification.setNotifiContent(message);
            notification.setMemberNo(request.getMemberNo());
            notification.setNotifiTime(new Timestamp(System.currentTimeMillis()));
            return notification;
        } else {
            throw new RuntimeException("Request is still under review");
        }
    }
}
