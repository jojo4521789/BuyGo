package web.back_end.member.wallet.dao.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import web.back_end.member.wallet.dao.WalletTransHistDao;
import web.back_end.member.wallet.entity.WalletTransHist;

public class WalletTransHistDaoImpl implements WalletTransHistDao {
	public static void main(String[] args) {
		WalletTransHistDaoImpl walletTransHistDaoImpl = new WalletTransHistDaoImpl();
		// 新增 insert
		Session session = walletTransHistDaoImpl.getSession();

		WalletTransHist walletTransHist = new WalletTransHist();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 指定日期格式
		String currentTimeString = dateFormat.format(new Date());
		Timestamp currentTime = Timestamp.valueOf(currentTimeString);
		walletTransHist.setMemberNo(2);
		walletTransHist.setWalletDetail("客服人員改動");
		walletTransHist.setWalletStatus(0);
		walletTransHist.setWalletTime(currentTime);
		walletTransHist.setWalletAmount((double)500);
		
		Transaction transaction = session.beginTransaction(); // 開始交易
		
		System.out.println(walletTransHistDaoImpl.insert(walletTransHist));
		
		transaction.commit(); // 送交，同時會結束交易
	}

	@Override
	public int insert(WalletTransHist walletTransHist) {
		getSession().persist(walletTransHist);
		return 1;
	}
}
