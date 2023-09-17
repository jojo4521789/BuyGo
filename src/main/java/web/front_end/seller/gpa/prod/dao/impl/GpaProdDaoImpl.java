package web.front_end.seller.gpa.prod.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.front_end.member.black.dao.impl.BlacklistDaoImpl;
import web.front_end.member.black.entity.Blacklist;
import web.front_end.member.chat.entity.ChatroomMessage;
import web.front_end.seller.gpa.prod.dao.GpaProdDao;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public class GpaProdDaoImpl implements GpaProdDao{
	public static void main(String[] args) {
		GpaProdDaoImpl gpaProdDaoImpl = new GpaProdDaoImpl();
		
		// 測試
		// 新增insert
//		Session session = gpaProdDaoImpl.getSession();
//		
//		GpaProd gpaProd = new GpaProd();
//		gpaProd.setMemberNo(4);
//		gpaProd.setGpaCatsNo(3);
//		gpaProd.setGpaProdName("是我啦");
//		gpaProd.setGpaFirstPrice(10);
//		gpaProd.setGpaMiniCount(5);
//		gpaProd.setGpaMaxCount(5000);
//		gpaProd.setGpaPreProd(0);
//		gpaProd.setGpaProdContent("哈哈哈");
//		gpaProd.setGpaEndDate(Timestamp.valueOf(LocalDateTime.of(2000, 8, 7, 0, 0, 0)));
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			gpaProdDaoImpl.insert(gpaProd); // 新增
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}
		
		//刪除deleteById
//		Session session = gpaProdDaoImpl.getSession();
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			gpaProdDaoImpl.deleteById(6); // 刪除
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}
		
		// 修改update
//		Session session = gpaProdDaoImpl.getSession();
//		
//		GpaProd gpaProd = new GpaProd();
//		gpaProd.setGpaProdNo(6);
//		gpaProd.setMemberNo(4);
//		gpaProd.setGpaCatsNo(3);
//		gpaProd.setGpaProdName("是我啦");
//		gpaProd.setGpaFirstPrice(10);
//		gpaProd.setGpaMiniCount(5);
//		gpaProd.setGpaMaxCount(5000);
//		gpaProd.setGpaPreProd(0);
//		gpaProd.setGpaProdContent("哈哈哈");
//		gpaProd.setGpaEndDate(Timestamp.valueOf(LocalDateTime.of(2023, 8, 7, 0, 0, 0)));
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(gpaProdDaoImpl.update(gpaProd));
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectById
//		Session session = gpaProdDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		GpaProd gpaProd = gpaProdDaoImpl.selectById(3);
//		System.out.println("getGpaProdNo:" + gpaProd.getGpaProdNo());
//		System.out.println("getMemberNo:" + gpaProd.getMemberNo());
//		System.out.println("getGpaProdName:" + gpaProd.getGpaProdName());
//		System.out.println("getGpaFirstPrice:" + gpaProd.getGpaFirstPrice());
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectAll
//		Session session = gpaProdDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaProd> gpaProdList = gpaProdDaoImpl.selectAll();
//		for (GpaProd gpaProd : gpaProdList) {
//			System.out.print("GPA_PROD_NO:" + gpaProd.getGpaProdNo() + ",");
//			System.out.print("MEMBER_NO:" + gpaProd.getMemberNo() + ",");
//			System.out.println("GPA_PROD_NAME:" + gpaProd.getGpaProdName());
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByMemberNo
//		Session session = gpaProdDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaProd> GpaProdList = gpaProdDaoImpl.selectByMemberNo(1);
//		for (GpaProd gpaProd : GpaProdList) {
//			System.out.print("gpaProdNo:" + gpaProd.getGpaProdNo() + ",");
//			System.out.print("memberNo:" + gpaProd.getMemberNo() + ",");
//			System.out.print("gpaProdName:" + gpaProd.getGpaProdName() + ",");
//			System.out.println("gpaPreProd:" + gpaProd.getGpaPreProd());
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByProdName
//		Session session = gpaProdDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaProd> GpaProdList = gpaProdDaoImpl.selectByProdName("短傘");
//		for (GpaProd gpaProd : GpaProdList) {
//			System.out.print("gpaProdNo:" + gpaProd.getGpaProdNo() + ",");
//			System.out.print("memberNo:" + gpaProd.getMemberNo() + ",");
//			System.out.print("gpaProdName:" + gpaProd.getGpaProdName() + ",");
//			System.out.println("gpaPreProd:" + gpaProd.getGpaPreProd());
//		}
//		transaction.commit(); // 送交，同時會結束交易
	}

	@Override
	public int insert(GpaProd gpaProd) {
		getSession().save(gpaProd);
		return 1;
	}
	
	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		GpaProd gpaProd = session.get(GpaProd.class, id);
		session.remove(gpaProd);
		return 1;
	}

	@Override
	public int update(GpaProd entity) {
		final String hql = "UPDATE GpaProd SET memberNo = :memberNo, gpaCatsNo = :gpaCatsNo, gpaProdName = :gpaProdName, gpaFirstPrice = :gpaFirstPrice, gpaMiniCount = :gpaMiniCount, gpaMaxCount = :gpaMaxCount, gpaPreProd = :gpaPreProd, gpaProdContent = :gpaProdContent, gpaEndDate = :gpaEndDate WHERE gpaProdNo = :gpaProdNo";
		Query<?> query = getSession().createQuery(hql);
		return query
				.setParameter("gpaProdNo", entity.getGpaProdNo())
				.setParameter("memberNo", entity.getMemberNo())
				.setParameter("gpaCatsNo", entity.getGpaCatsNo())
				.setParameter("gpaProdName", entity.getGpaProdName())
				.setParameter("gpaFirstPrice", entity.getGpaFirstPrice())
				.setParameter("gpaMiniCount", entity.getGpaMiniCount())
				.setParameter("gpaMaxCount", entity.getGpaMaxCount())
				.setParameter("gpaPreProd", entity.getGpaPreProd())
				.setParameter("gpaProdContent", entity.getGpaProdContent())
				.setParameter("gpaEndDate", entity.getGpaEndDate())
				.executeUpdate();
	}

	@Override
	public GpaProd selectById(Integer id) {
		return getSession().get(GpaProd.class, id);
	}

	@Override
	public List<GpaProd> selectAll() {
		final String hql = "FROM GpaProd ORDER BY gpaProdNo";
		return getSession()
				.createQuery(hql, GpaProd.class)
				.getResultList();
	}
	
	@Override
	public List<GpaProd> selectByMemberNo(Integer memberNo){
		final String hql = "FROM GpaProd WHERE memberNo = :memberNo ORDER BY gpaProdNo";
		return getSession()
				.createQuery(hql, GpaProd.class)
				.setParameter("memberNo", memberNo)
				.getResultList();
	}
	
	@Override
	public List<GpaProd> selectByProdName(String prodName){
		final String hql = "FROM GpaProd WHERE gpaProdName = :gpaProdName ORDER BY gpaProdNo";
		return getSession()
				.createQuery(hql, GpaProd.class)
				.setParameter("gpaProdName", prodName)
				.getResultList();
	}
}
