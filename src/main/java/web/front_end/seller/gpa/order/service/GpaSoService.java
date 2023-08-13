package web.front_end.seller.gpa.order.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.seller.gpa.order.entity.GpaSo;

public interface GpaSoService extends CoreService{
	List<GpaSo> loadBySoStat(Integer soStat);
}
