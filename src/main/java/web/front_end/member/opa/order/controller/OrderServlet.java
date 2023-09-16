package web.front_end.member.opa.order.controller;
import static web.front_end.member.opa.order.util.OpaOrderConstants.SERVICE;
import web.front_end.member.opa.order.service.impl.OpaOrderServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.util.CommonUtil;
import web.front_end.member.opa.order.entity.OpaOrder;
import web.front_end.member.opa.order.entity.Order;
import web.front_end.member.opa.order.service.OpaOrderService;

@WebServlet("/needLoginApi/member/order")
public class OrderServlet extends HttpServlet {
    private static int [] statusMapping = new int[] { 0, 1, 2, 3, 1, 1, 3, 4, 3, 5, 6 };
    private static String [] ORDER_STATUS_MAPPING = new String []{ "訂單成立", "第一次付款通知", "海外賣家出貨", "官方海外收貨、發貨", "商品抵台", "第二次付款通知", "官方出貨", "買家完成訂單", "未完成取貨", "取消訂單", "訂單委託失敗"};
    private static String []  ORDER_PAY_STATUS_MAPPING =  new String []{"第一次未付款", "第一次已付款", "第二次未付款", "第二次已付款"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		HttpSession session = request.getSession();
        int memberNo = (Integer)session.getAttribute("memberNo");
        String _id = request.getParameter("id");
        Integer id;
        response.setCharacterEncoding("UTF-8");
        if (_id == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        id = Integer.parseInt(_id);
        OpaOrder order = SERVICE.findById(id);
        if (order == null || order.getMemberNo() != memberNo) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        order.setOpaOrderdetailses(null);
        CommonUtil.writePojo2Json(response, order);
    }
}
