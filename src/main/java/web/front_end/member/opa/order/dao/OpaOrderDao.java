package web.front_end.member.opa.order.dao;

import core.dao.CoreDao;
import web.front_end.member.opa.order.entity.OpaOrder;
import web.front_end.member.opa.order.entity.OpaOrderDetails;

import java.util.List; 

public interface OpaOrderDao extends CoreDao<OpaOrder, Integer> {

    OpaOrder getOrderById(Integer orderId);

    List<OpaOrder> getAllOrders();

    void saveOrder(OpaOrder order);

    void updateOrder(OpaOrder order);

    void deleteOrder(OpaOrder order);

    List<OpaOrderDetails> getOrderDetailsByOrderId(Integer orderId);

    void saveOrderDetails(OpaOrderDetails orderDetails);

    void updateOrderDetails(OpaOrderDetails orderDetails);

    void deleteOrderDetails(OpaOrderDetails orderDetails);
}