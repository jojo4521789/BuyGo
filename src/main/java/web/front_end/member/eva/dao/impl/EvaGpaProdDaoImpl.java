package web.front_end.member.eva.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import web.front_end.member.eva.dao.EvaGpaProdDao;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public class EvaGpaProdDaoImpl implements EvaGpaProdDao{
	public static void main(String[] args) {
		EvaGpaProdDaoImpl evaGpaProdDaoImpl = new EvaGpaProdDaoImpl();

		// 測試
		// 查詢selectByMemberNo
		Session session = evaGpaProdDaoImpl.getSession();
		
		Transaction transaction = session.beginTransaction(); // 開始交易
		List<GpaProd> gpaProdList = evaGpaProdDaoImpl.selectByMemberNo(2);
		for (GpaProd gpaProd : gpaProdList) {
			System.out.print("memberNo:" + gpaProd.getMemberNo() + ",");
			System.out.println("gpaProdNo:" + gpaProd.getGpaProdNo());
			for(GpaSo gpaSo : gpaProd.getGpaSo()) {
				System.out.println("gpaSo>MemberNo:" + gpaSo.getMemberNo());
				System.out.println("gpaSo>GpaEvaSeller:" + gpaSo.getGpaEvaSeller());
			}
		}
		transaction.commit(); // 送交，同時會結束交易
		
	}

	@Override
	public List<GpaProd> selectByMemberNo(Integer memberNo){
		final String hql = "FROM GpaProd WHERE memberNo = :memberNo ORDER BY gpaProdNo";
		return getSession()
				.createQuery(hql, GpaProd.class)
				.setParameter("memberNo", memberNo)
				.getResultList();
	}

}
