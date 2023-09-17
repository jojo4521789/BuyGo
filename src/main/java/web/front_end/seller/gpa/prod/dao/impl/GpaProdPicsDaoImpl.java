package web.front_end.seller.gpa.prod.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.front_end.seller.gpa.prod.dao.GpaProdPicsDao;
import web.front_end.seller.gpa.prod.entity.GpaProdPics;
import web.front_end.seller.gpa.prod.entity.GpaReach;

public class GpaProdPicsDaoImpl implements GpaProdPicsDao{

	public static void main(String[] args) {
		GpaProdPicsDaoImpl gpaProdPicsDaoImpl = new GpaProdPicsDaoImpl();
		
		// 測試
		// 新增insert
//		Session session = gpaProdPicsDaoImpl.getSession();
//		
//		GpaProdPics gpaProdPics = new GpaProdPics();
//		gpaProdPics.setGpaProdNo(3);
//		
//		try {
//			gpaProdPics.setGpaProdPic(getPictureByteArray("C:/Users/T14 Gen 3/Desktop/TV.jpg"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//			System.out.println("圖片設置失敗");
//		}
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			gpaProdPicsDaoImpl.insert(gpaProdPics); // 新增
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}

		// 刪除deleteById
//		Session session = gpaProdPicsDaoImpl.getSession();
//
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			gpaProdPicsDaoImpl.deleteById(4); // 刪除
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}

		// 修改update
//		Session session = gpaProdPicsDaoImpl.getSession();
//		
//		GpaProdPics gpaProdPics = new GpaProdPics();
//		gpaProdPics.setGpaProdPicNo(5);
//		gpaProdPics.setGpaProdNo(2);
//		try {
//			gpaProdPics.setGpaProdPic(getPictureByteArray("C:/Users/T14 Gen 3/Desktop/umbrella.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("圖片設置失敗");
//		}
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(gpaProdPicsDaoImpl.update(gpaProdPics));
//		transaction.commit(); // 送交，同時會結束交易

		// 查詢selectById
//		Session session = gpaProdPicsDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(gpaProdPicsDaoImpl.selectById(3));
//		transaction.commit(); // 送交，同時會結束交易
//		
		// 查詢selectAll
//		Session session = gpaProdPicsDaoImpl.getSession();
//
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<GpaProdPics> gpaProdPicsList = gpaProdPicsDaoImpl.selectAll();
//		for (GpaProdPics gpaProdPics : gpaProdPicsList) {
//			System.out.print("gpaProdPicNo:" + gpaProdPics.getGpaProdPicNo() + ",");
//			System.out.print("gpaProdNo:" + gpaProdPics.getGpaProdNo() + ",");
//			System.out.println("gpaProdPic:" + gpaProdPics.getGpaProdPic());
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByProdNo
		Session session = gpaProdPicsDaoImpl.getSession();
		
		Transaction transaction = session.beginTransaction(); // 開始交易
		List<GpaProdPics> gpaProdPicsList = gpaProdPicsDaoImpl.selectByProdNo(2);
		for (GpaProdPics gpaProdPics : gpaProdPicsList) {
			System.out.print("gpaProdPicNo:" + gpaProdPics.getGpaProdPicNo() + ",");
			System.out.print("gpaProdNo:" + gpaProdPics.getGpaProdNo() + ",");
			System.out.println("gpaProdPic:" + gpaProdPics.getGpaProdPic());
		}
		transaction.commit(); // 送交，同時會結束交易
	}

	@Override
	public int insert(GpaProdPics gpaProdPics) {
		getSession().save(gpaProdPics);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		GpaProdPics gpaProdPics = session.get(GpaProdPics.class, id);
		session.remove(gpaProdPics);
		return 1;
	}

	@Override
	public int update(GpaProdPics entity) {
		final String hql = "UPDATE GpaProdPics SET gpaProdNo = :gpaProdNo, gpaProdPic = :gpaProdPic WHERE gpaProdPicNo = :gpaProdPicNo";
		Query<?> query = getSession().createQuery(hql);
		return query.setParameter("gpaProdPicNo", entity.getGpaProdPicNo())
				.setParameter("gpaProdNo", entity.getGpaProdNo())
				.setParameter("gpaProdPic", entity.getGpaProdPic())
				.executeUpdate();
	}

	@Override
	public GpaProdPics selectById(Integer id) {
		return getSession().get(GpaProdPics.class, id);
	}

	@Override
	public List<GpaProdPics> selectAll() {
		final String hql = "FROM GpaProdPics ORDER BY gpaProdPicNo";
		return getSession().createQuery(hql, GpaProdPics.class).getResultList();
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		
		byte[] buffer = fis.readAllBytes();
		
		fis.close();
		return buffer;
	}
	
	@Override
	public List<GpaProdPics> selectByProdNo(Integer ProdNo){
		final String hql = "FROM GpaProdPics WHERE gpaProdNo = :gpaProdNo ORDER BY gpaProdPicNo";
		return getSession()
				.createQuery(hql, GpaProdPics.class)
				.setParameter("gpaProdNo", ProdNo)
				.getResultList();
	}
}
