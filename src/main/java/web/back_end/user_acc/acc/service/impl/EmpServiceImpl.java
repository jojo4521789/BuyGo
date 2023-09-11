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
		// TODO Auto-generated method stub
		return null;
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

}
