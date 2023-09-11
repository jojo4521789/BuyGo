package web.front_end.member.eva.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import web.front_end.member.eva.dao.EvaPaSoDao;
import web.front_end.member.eva.entity.PaSo;
import web.front_end.member.eva.entity.PaSoDetails;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public class EvaPaSoDaoImpl implements EvaPaSoDao{
	public static void main(String[] args) {
		EvaPaSoDaoImpl evaPaSoDaoImpl = new EvaPaSoDaoImpl();

		// 測試
		// 查詢selectAll
		Session session = evaPaSoDaoImpl.getSession();
		
		Transaction transaction = session.beginTransaction(); // 開始交易
		List<PaSo> PaSoList = evaPaSoDaoImpl.selectAll();
		for (PaSo paSo : PaSoList) {
			System.out.print("paSoNo:" + paSo.getPaSoNo() + ",");
			for(PaSoDetails paSoDetails : paSo.getPaSoDetails()) {
				System.out.println("paProdName:" + paSoDetails.getPaProdName());
				System.out.println("paProdQty:" + paSoDetails.getPaProdQty());
			}
		}
		transaction.commit(); // 送交，同時會結束交易
		
	}

	@Override
	public List<PaSo> selectAll() {
		final String hql = "FROM PaSo ORDER BY paSoNo DESC";
		return getSession()
				.createQuery(hql, PaSo.class)
				.getResultList();
	}

}
