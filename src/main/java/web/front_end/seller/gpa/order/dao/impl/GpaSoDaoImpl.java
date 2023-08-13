package web.front_end.seller.gpa.order.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.front_end.seller.gpa.order.dao.GpaSoDao;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.dao.impl.GpaReachDaoImpl;
import web.front_end.seller.gpa.prod.entity.GpaProd;
import web.front_end.seller.gpa.prod.entity.GpaReach;

public class GpaSoDaoImpl implements GpaSoDao {
	
	public static void main(String[] args) {
		GpaSoDaoImpl gpaSoDaoImpl = new GpaSoDaoImpl();

		// 測試
		// 新增insert
//		Session session = gpaSoDaoImpl.getSession();
//		
//		GpaSo gpaSo = new GpaSo();
//		gpaSo.setGpaProdNo(3);
//		gpaSo.setMemberNo(5);
//		gpaSo.setGpaProdCount(100);
//		gpaSo.setGpaProdPrice(1000);
//		gpaSo.setGpaProdTotal(100000);
//		gpaSo.setGpaSoStat(1);
//		gpaSo.setGpaBuyName("Alan");
//		gpaSo.setGpaBuyTel("0912345678");
//		gpaSo.setGpaBuyAdd("台北市");
//		gpaSo.setGpaEvaSeller(5);
//		gpaSo.setGpaEvaMember(5);
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			gpaSoDaoImpl.insert(gpaSo); // 新增
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}

		// 刪除deleteById
//		Session session = gpaSoDaoImpl.getSession();
//
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			gpaSoDaoImpl.deleteById(4); // 刪除
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}

		// 修改update
//		Session session = gpaSoDaoImpl.getSession();
//		
//		GpaSo gpaSo = new GpaSo();
//		gpaSo.setGpaSoNo(4);
//		gpaSo.setGpaProdNo(3);
//		gpaSo.setMemberNo(5);
//		gpaSo.setGpaProdCount(100);
//		gpaSo.setGpaProdPrice(1000);
//		gpaSo.setGpaProdTotal(100000);
//		gpaSo.setGpaSoStat(1);
//		gpaSo.setGpaBuyName("Alan");
//		gpaSo.setGpaBuyTel("0912345678");
//		gpaSo.setGpaBuyAdd("台中市");
//		gpaSo.setGpaEvaSeller(5);
//		gpaSo.setGpaEvaMember(5);
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(gpaSoDaoImpl.update(gpaSo));
//		transaction.commit(); // 送交，同時會結束交易

		// 查詢selectById
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(gpaSoDaoImpl.selectById(3));
//		transaction.commit(); // 送交，同時會結束交易
//		
		// 查詢selectAll
//		Session session = gpaSoDaoImpl.getSession();
//
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaSo> gpaSoList = gpaSoDaoImpl.selectAll();
//		for (GpaSo gpaSo : gpaSoList) {
//			System.out.print("gpaSoNo:" + gpaSo.getGpaSoNo() + ",");
//			System.out.print("gpaProdNo:" + gpaSo.getGpaProdNo() + ",");
//			System.out.print("memberNo:" + gpaSo.getMemberNo() + ",");
//			System.out.println("gpaProdTotal:" + gpaSo.getGpaProdTotal());
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByMemberNo
//		Session session = gpaSoDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaSo> GpaSoList = gpaSoDaoImpl.selectByMemberNo(2);
//		for (GpaSo gpaSo : GpaSoList) {
//			System.out.print("gpaSoNo:" + gpaSo.getGpaSoNo() + ",");
//			System.out.print("memberNo:" + gpaSo.getMemberNo() + ",");
//			System.out.print("gpaProdNo:" + gpaSo.getGpaProdNo() + ",");
//			System.out.println("gpaProdTotal:" + gpaSo.getGpaProdTotal());
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectBySoStat
		Session session = gpaSoDaoImpl.getSession();
		
		Transaction transaction = session.beginTransaction(); // 開始交易
		List<GpaSo> gpaSoList = gpaSoDaoImpl.selectBySoStat(2);
		for (GpaSo gpaSo : gpaSoList) {
			System.out.print("gpaSoNo:" + gpaSo.getGpaSoNo() + ",");
			System.out.print("memberNo:" + gpaSo.getMemberNo() + ",");
			System.out.print("gpaProdNo:" + gpaSo.getGpaProdNo() + ",");
			System.out.println("gpaProdTotal:" + gpaSo.getGpaProdTotal());
		}
		transaction.commit(); // 送交，同時會結束交易
	}

	@Override
	public int insert(GpaSo gpaSo) {
		getSession().persist(gpaSo);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		GpaSo gpaSo = session.get(GpaSo.class, id);
		session.remove(gpaSo);
		return 1;
	}

	@Override
	public int update(GpaSo entity) {
		final String hql = "UPDATE GpaSo SET gpaProdNo = :gpaProdNo, memberNo = :memberNo, gpaProdCount = :gpaProdCount, gpaProdPrice = :gpaProdPrice, gpaProdTotal = :gpaProdTotal, gpaSoStat = :gpaSoStat, gpaBuyName = :gpaBuyName, gpaBuyTel = :gpaBuyTel, gpaBuyAdd = :gpaBuyAdd, gpaEvaSeller = :gpaEvaSeller, gpaEvaMember = :gpaEvaMember WHERE gpaSoNo = :gpaSoNo";
		Query<?> query = getSession().createQuery(hql);
		return query.setParameter("gpaSoNo", entity.getGpaSoNo())
				.setParameter("gpaProdNo", entity.getGpaProdNo())
				.setParameter("memberNo", entity.getMemberNo())
				.setParameter("gpaProdCount", entity.getGpaProdCount())
				.setParameter("gpaProdPrice", entity.getGpaProdPrice())
				.setParameter("gpaProdTotal", entity.getGpaProdTotal())
				.setParameter("gpaSoStat", entity.getGpaSoStat())
				.setParameter("gpaBuyName", entity.getGpaBuyName())
				.setParameter("gpaBuyTel", entity.getGpaBuyTel())
				.setParameter("gpaBuyAdd", entity.getGpaBuyAdd())
				.setParameter("gpaEvaSeller", entity.getGpaEvaSeller())
				.setParameter("gpaEvaMember", entity.getGpaEvaMember())
				.executeUpdate();
	}

	@Override
	public GpaSo selectById(Integer id) {
		return getSession().get(GpaSo.class, id);
	}

	@Override
	public List<GpaSo> selectAll() {
		final String hql = "FROM GpaSo ORDER BY gpaSoNo";
		return getSession().createQuery(hql, GpaSo.class).getResultList();
	}
	
	@Override
	public List<GpaSo> selectByMemberNo(Integer memberNo){
		final String hql = "FROM GpaSo WHERE memberNo = :memberNo ORDER BY gpaSoNo";
		return getSession()
				.createQuery(hql, GpaSo.class)
				.setParameter("memberNo", memberNo)
				.getResultList();
	}
	
	@Override
	public List<GpaSo> selectBySoStat(Integer soStat){
		final String hql = "FROM GpaSo WHERE gpaSoStat = :gpaSoStat ORDER BY gpaSoNo";
		return getSession()
				.createQuery(hql, GpaSo.class)
				.setParameter("gpaSoStat", soStat)
				.getResultList();
	}
}
