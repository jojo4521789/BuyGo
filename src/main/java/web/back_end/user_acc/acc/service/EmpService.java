package web.back_end.user_acc.acc.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.user_acc.acc.entity.Emp;

public interface EmpService extends CoreService {
	Emp edit(Emp emp);
	Emp login(Emp emp);
	List<Emp> findall();
	List<Emp> selectEmpByNO(Integer empno);
	Emp insert(Emp emp);
	boolean remove(Integer id);
	boolean save(Emp emp);

}
