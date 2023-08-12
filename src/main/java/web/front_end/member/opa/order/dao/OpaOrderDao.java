package web.front_end.member.opa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.opa.order.entity.OpaOrder;
import web.front_end.member.opa.order.entity.OpaOrderDetails;

public interface OpaOrderDao extends CoreDao<OpaOrder, Integer> {

    OpaOrder getOrderById(Integer orderId);

    void saveOrder(OpaOrder order);

    List<OpaOrderDetails> getOrderDetailsByOrderId(Integer orderId);

    void saveOrderDetails(OpaOrderDetails orderDetails);
}