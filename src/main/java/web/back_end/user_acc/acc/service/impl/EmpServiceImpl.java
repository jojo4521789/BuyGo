package web.back_end.user_acc.acc.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import web.back_end.user_acc.acc.dao.EmpDAO;
import web.back_end.user_acc.acc.dao.impl.EmpDaoImpl;
import web.back_end.user_acc.acc.entity.Emp;
import web.back_end.user_acc.acc.service.EmpService;
@Transactional
@Service
public class EmpServiceImpl implements EmpService {

	private EmpDAO dao;
	public EmpServiceImpl() {
		dao = new EmpDaoImpl();
	}
	

	@Override
	public Emp edit(Emp emp) {
		final int resultCount = dao.update(emp);
		emp.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		emp.setSuccessful(resultCount > 0);
		return emp;
	}

	@Override
	public Emp login(Emp emp) {
		return null;
	}

	@Override
	public List<Emp> findall() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer id) {
		return dao.deleteById(id) > 0;
	}

	@Override
	public boolean save(Emp emp) {
		return dao.update(emp) > 0;
	}


	@Override
	public Emp insert(Emp emp) {
		
		final int resultCount = dao.insert(emp);
		if(resultCount < 1) {
			emp.setMessage("新增商品錯誤，請聯絡管理員!");
			emp.setSuccessful(false);
			return emp;
		}
		
		emp.setMessage("新增會員成功");
		emp.setSuccessful(true);
		return emp;
	}


	@Override
	public List<Emp> selectEmpByNO(Integer empno) {
		return dao.selectByEmpNo(empno);
	}

}
