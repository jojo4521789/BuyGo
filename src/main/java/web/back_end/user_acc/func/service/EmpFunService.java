package web.back_end.user_acc.func.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.user_acc.func.entity.EmpFun;

public interface EmpFunService extends CoreService{

	EmpFun loadEmpFun (Integer empFun);
	List<EmpFun> findall();
}
