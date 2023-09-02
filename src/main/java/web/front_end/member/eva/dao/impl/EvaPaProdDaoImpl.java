//package web.front_end.member.eva.dao.impl;
//
//import java.util.Iterator;
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import web.front_end.member.eva.dao.EvaPaProdDao;
//import web.front_end.member.eva.entity.PaProd;
//import web.front_end.member.eva.entity.PaSo;
//import web.front_end.member.eva.entity.PaSoDetails;
//
//public class EvaPaProdDaoImpl implements EvaPaProdDao{
//	public static void main(String[] args) {
//		EvaPaProdDaoImpl evaPaProdDaoImpl = new EvaPaProdDaoImpl();
//
//		// 測試
//		// 查詢selectByMemberNo
//		Session session = evaPaProdDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<PaProd> paProdList = evaPaProdDaoImpl.selectByMemberNo(10);
//		for (PaProd paProd : paProdList) {
//			System.out.print("memberNo:" + paProd.getMemberNo() + ",");
//			System.out.println("paProdNo:" + paProd.getPaProdNo());
//			for(PaSo paSo : paProd.getPaSo()) {
//				System.out.println("paSoTotal:" + paSo.getPaSoTotal());
//				for(PaSoDetails paSoDetails: paSo.getPaSoDetails()) {
//					System.out.println("paSoDetails:" + paSoDetails.getPaProdName());
//					System.out.println("PaProdQty:" + paSoDetails.getPaProdQty());
//				}
//			}
//		}
//		transaction.commit(); // 送交，同時會結束交易
//		
//	}
//
//	@Override
//	public List<PaProd> selectByMemberNo(Integer memberNo){
//		final String hql = "FROM PaProd WHERE memberNo = :memberNo ORDER BY paProdNo";
//		return getSession()
//				.createQuery(hql, PaProd.class)
//				.setParameter("memberNo", memberNo)
//				.getResultList();
//	}
//
//}
