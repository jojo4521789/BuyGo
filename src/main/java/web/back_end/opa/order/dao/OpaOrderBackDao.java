package web.back_end.opa.order.dao;

import core.dao.CoreDao;
import web.back_end.opa.order.entity.OpaOrderBack;
import web.back_end.opa.order.entity.OpaOrderDetails;

import java.util.List;

public interface OpaOrderBackDao extends CoreDao<OpaOrderBack, Integer> {

	OpaOrderBack getById(Integer orderId);

    List<OpaOrderBack> getAllOrderBacks();

    void saveOrderBack(OpaOrderBack orderBack);

    void updateOrderBack(OpaOrderBack orderBack);

    void deleteOrderBack(OpaOrderBack orderBack);

    List<OpaOrderDetails> getOrderDetailsByOrderBackId(Integer orderBackId);

    void saveOrderDetails(OpaOrderDetails orderDetails);

    void updateOrderDetails(OpaOrderDetails orderDetails);

    void deleteOrderDetails(OpaOrderDetails orderDetails);
}
