package web.front_end.seller.gpa.prod.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.front_end.member.black.dao.impl.BlacklistDaoImpl;
import web.front_end.member.black.entity.Blacklist;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.dao.GpaReachDao;
import web.front_end.seller.gpa.prod.entity.GpaReach;

public class GpaReachDaoImpl implements GpaReachDao {

	public static void main(String[] args) {
		GpaReachDaoImpl gpaReachDaoImpl = new GpaReachDaoImpl();
		
		// 測試
		// 新增insert
//		Session session = gpaReachDaoImpl.getSession();
//		
//		GpaReach gpaReach = new GpaReach();
//		gpaReach.setGpaProdNo(3);
//		gpaReach.setGpaLevelCount(500);
//		gpaReach.setGpaLevelPrice(100);
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			gpaReachDaoImpl.insert(gpaReach); // 新增
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}

		// 刪除deleteById
//		Session session = gpaReachDaoImpl.getSession();
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			gpaReachDaoImpl.deleteById(4); // 刪除
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}

		// 修改update
//		Session session = gpaReachDaoImpl.getSession();
//		
//		GpaReach gpaReach = new GpaReach();
//		gpaReach.setGpaReachNo(4);
//		gpaReach.setGpaProdNo(3);
//		gpaReach.setGpaLevelCount(1000);
//		gpaReach.setGpaLevelPrice(10);
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(gpaReachDaoImpl.update(gpaReach));
//		transaction.commit(); // 送交，同時會結束交易

		// 查詢selectById
//		Session session = gpaReachDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(gpaReachDaoImpl.selectById(3));
//		transaction.commit(); // 送交，同時會結束交易
//		
		// 查詢selectAll
//		Session session = gpaReachDaoImpl.getSession();
//
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaReach> gpaReachList = gpaReachDaoImpl.selectAll();
//		for (GpaReach gpaReach : gpaReachList) {
//			System.out.print("gpaReachNo:" + gpaReach.getGpaReachNo() + ",");
//			System.out.print("gpaProdNo:" + gpaReach.getGpaProdNo() + ",");
//			System.out.print("gpaLevelCount:" + gpaReach.getGpaLevelCount() + ",");
//			System.out.println("gpaLevelPrice:" + gpaReach.getGpaLevelPrice());
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByProdNo
		Session session = gpaReachDaoImpl.getSession();
		
		Transaction transaction = session.beginTransaction(); // 開始交易
		List<GpaReach> gpaReachList = gpaReachDaoImpl.selectByProdNo(1);
		for (GpaReach gpaReach : gpaReachList) {
			System.out.print("gpaReachNo:" + gpaReach.getGpaReachNo() + ",");
			System.out.print("gpaProdNo:" + gpaReach.getGpaProdNo() + ",");
			System.out.print("gpaLevelCount:" + gpaReach.getGpaLevelCount() + ",");
			System.out.println("gpaLevelPrice:" + gpaReach.getGpaLevelPrice());
		}
		transaction.commit(); // 送交，同時會結束交易
	}

	@Override
	public int insert(GpaReach gpaReach) {
		getSession().persist(gpaReach);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		GpaReach gpaReach = session.get(GpaReach.class, id);
		session.remove(gpaReach);
		return 1;
	}

	@Override
	public int update(GpaReach entity) {
		final String hql = "UPDATE GpaReach SET gpaProdNo = :gpaProdNo, gpaLevelCount = :gpaLevelCount, gpaLevelPrice = :gpaLevelPrice WHERE gpaReachNo = :gpaReachNo";
		Query<?> query = getSession().createQuery(hql);
		return query
				.setParameter("gpaReachNo", entity.getGpaReachNo())
				.setParameter("gpaProdNo", entity.getGpaProdNo())
				.setParameter("gpaLevelCount", entity.getGpaLevelCount())
				.setParameter("gpaLevelPrice", entity.getGpaLevelPrice())
				.executeUpdate();
	}

	@Override
	public GpaReach selectById(Integer id) {
		return getSession().get(GpaReach.class, id);
	}

	@Override
	public List<GpaReach> selectAll() {
		final String hql = "FROM GpaReach ORDER BY gpaReachNo";
		return getSession().createQuery(hql, GpaReach.class).getResultList();
	}
	
	@Override
	public List<GpaReach> selectByProdNo(Integer ProdNo){
		final String hql = "FROM GpaReach WHERE gpaProdNo = :gpaProdNo ORDER BY gpaReachNo";
		return getSession()
				.createQuery(hql, GpaReach.class)
				.setParameter("gpaProdNo", ProdNo)
				.getResultList();
	}
}
