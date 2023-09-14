package web.front_end.member.gpa.order.Service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.gpa.order.entity.GpaProdRp;
import web.front_end.member.gpa.order.entity.SelectProdRp;

public interface GpaProdRpService extends CoreService{
	GpaProdRp increse(GpaProdRp gpaProdRp);
	List<SelectProdRp> selectByProdRps();
	
}
