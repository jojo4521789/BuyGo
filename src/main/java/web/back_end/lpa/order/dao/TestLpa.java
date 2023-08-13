package web.back_end.lpa.order.dao;

import java.lang.reflect.Member;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.back_end.lpa.order.entity.Lpa_Prod;
import web.back_end.lpa.order.entity.Lpa_SO;

public class TestLpa {
	public static void main(String[] args) {
		TestLpa testLpa = new TestLpa();
		List<Lpa_Prod>list = testLpa.selectAll();
		
		for (Lpa_Prod lpaProd : list) {
			System.out.println("商品編號: " + lpaProd.getLpaProdNo() + " ; "
					+ "會員號碼: " + lpaProd.getMemberNo() + " ; "
					+ "商品名稱: " + lpaProd.getLpaProdName() + " ; "
					+ "商品內容: " + lpaProd.getLpaProdContent()
					);
		}

	}
	// 新增
//		Lpa_Prod lpaProd = new Lpa_Prod();
//		lpaProd.setLpaProdCatsNo(3);
//		lpaProd.setLpaProdContent(null);
//		lpaProd.setMemberNo(3);
//		lpaProd.setLpaProdName("Bottle");
//		lpaProd.setLpaProdOffTime(Timestamp.valueOf("2023-08-10 00:00:00"));
//		
//		dao.add(lpaProd);

//		// 刪除
//		dao.deleteByLpaProdNo(5);
//		
	// 修改
//		Lpa_Prod lpaProd2 = new Lpa_Prod();
//		lpaProd2.setLpaProdNo(4);
//		lpaProd2.setLpaProdContent("水瓶");
//		lpaProd2.setLpaProdStock(2);
//		lpaProd2.setLpaProdPrice(2000);
//		lpaProd2.setLpaProdStatus(2);

//		dao.updateByProdNo(lpaProd2);
//		
//		// 查詢
//		Lpa_Prod lpaProd3 = dao.findByPK(6);
//		System.out.println("商品編號: " + lpaProd3.getLpaProdNo() + " ; "
//				+ "會員號碼: " + lpaProd3.getMemberNo() + " ; "
//				+ "商品名稱: " + lpaProd3.getLpaProdName() + " ; "
//				+ "商品內容: " + lpaProd3.getLpaProdContent()
//				);
//		
//		// 查詢全部
	public List<Lpa_Prod> selectAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
	try {
		Transaction transaction = session.beginTransaction();
		Query<Lpa_Prod> query = session.createQuery(
				"FROM Lpa_Prod ORDER BY lpaProdNo", Lpa_Prod.class);
		List<Lpa_Prod> list = query.getResultList();
		transaction.commit();
		return list;
	}
	catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
		return null;
	}
}
		
}
