package web.back_end.lpa.order.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.lpa.order.entity.Lpa_SO;

public interface OrderService extends CoreService{

	List<Lpa_SO> findAll(); // 查詢訂單
	
//	Lpa_SO edit(Lpa_SO lpa_SO);
	
//	boolean remove(Integer orderNo);
	
//	boolean save(Lpa_SO lpa_SO);
}
