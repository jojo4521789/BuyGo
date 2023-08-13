package web.back_end.lpa.order.service;

import java.util.List;

import web.back_end.lpa.order.dao.LpaOrderDAO;
import web.back_end.lpa.order.dao.LpaOrderDAOImpl;
import web.back_end.lpa.order.entity.Lpa_SO;

public class OrderServiceImpl implements OrderService {
	private LpaOrderDAO dao;
	
	public OrderServiceImpl() {
		dao = new LpaOrderDAOImpl();
	}
	
//	public Member edit(Member member) {
//		final Member oMember = dao.selectByUsername(member.getUsername());
//		member.setPass(oMember.getPass());
//		member.setRoleId(oMember.getRoleId());
//		member.setUpdater(member.getUsername());
//		final int resultCount = dao.update(member);
//		member.setSuccessful(resultCount > 0);
//		member.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
//		return member;
//	}

	@Override
	public List<Lpa_SO> findAll() {
		System.out.println("執行查詢");
		return dao.selectAll();
	}

//	@Override
//	public boolean remove(Integer id) {
//		// 交易機制由Filter掌管，這邊不執行交易
//		return dao.deleteById(id) > 0;
//		try {
//			beginTransaction();
//			final int resultCount = dao.deleteById(id);
//			commit();
//			return resultCount >0;
//		} catch(Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}

//	@Override
//	public boolean save(Member member) {
//		return dao.update(member) > 0;
//	}
}
