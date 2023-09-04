package web.front_end.member.opa.order.controller;
import static web.front_end.member.opa.order.util.OpaOrderConstants.SERVICE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.front_end.member.opa.order.entity.OpaOrder;
import web.front_end.member.opa.order.entity.Order;
import web.front_end.member.opa.order.service.OpaOrderService;

@WebServlet("/needLoginApi/member/orders")
public class OrderServlet extends HttpServlet {
    private static int [] statusMapping = new int[] { 0, 1, 2, 3, 1, 1, 3, 4, 3, 5, 6 };
    private static String [] ORDER_STATUS_MAPPING = new String []{ "訂單成立", "第一次付款通知", "海外賣家出貨", "官方海外收貨、發貨", "商品抵台", "第二次付款通知", "官方出貨", "買家完成訂單", "未完成取貨", "取消訂單", "訂單委託失敗"};
    private static String []  ORDER_PAY_STATUS_MAPPING =  new String []{"第一次未付款", "第一次已付款", "第二次未付款", "第二次已付款"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int memberNo = 1;
        String type = request.getParameter("type");
		response.setCharacterEncoding("UTF-8");
        if (type == null) {
            response.sendRedirect(request.getContextPath() + "/front_end/order.html");
            return;
        }
        if (type.equals("order")) {
            getOrder(request, response, memberNo);
        } else if (type.equals("map")) {
            getMap(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/front_end/order.html");
        }
    }

    void getOrder(HttpServletRequest request, HttpServletResponse response, int memberNo) throws IOException {
        String id = request.getParameter("id");
        if (id == null) {
            response.sendRedirect(request.getContextPath() + "/front_end/order.html");
            return;
        }
        int query = Integer.parseInt(id);
        ArrayList<Integer> statusList = new ArrayList<>();
        for (int i = 0; i < statusMapping.length; i++) {
            if(statusMapping[i] == query || query == 0) {
                statusList.add(i);
            }
        }
        List<OpaOrder> orders = SERVICE.findAllOrderByStatus(memberNo, statusList);
        Order[] ordersArray = new Order[orders.size()];
        for (int i = 0; i < orders.size(); i++) {
            ordersArray[i] = new Order();
            ordersArray[i].setOpaSoNo(orders.get(i).getOpaSoNo());
            ordersArray[i].setOpaSoStatus(ORDER_STATUS_MAPPING[orders.get(i).getOpaSoStatus()]);
            ordersArray[i].setOpaSoDate(orders.get(i).getOpaSoDate());
            ordersArray[i].setOpaProdTotal(orders.get(i).getOpaProdTotal());
            ordersArray[i].setOpaDiscount(orders.get(i).getOpaDiscount());
            ordersArray[i].setOpaFirAmount(orders.get(i).getOpaFirAmount());
            ordersArray[i].setOpaSecAmount(orders.get(i).getOpaSecAmount());
            ordersArray[i].setOpaTotal(orders.get(i).getOpaTotal());
            ordersArray[i].setOpaRealTotal(orders.get(i).getOpaRealTotal());
            ordersArray[i].setOpaRealStatus(ORDER_PAY_STATUS_MAPPING[orders.get(i).getOpaRealStatus()]);
        }
        CommonUtil.writePojo2Json(response, ordersArray);
    }

    void getMap(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] statusMap = SERVICE.getStatusMap();
        CommonUtil.writePojo2Json(response, statusMap);
    }
}
