package web.back_end.member.wallet.dao;


import static core.util.HibernateUtil.getSessionFactory;

import org.hibernate.Session;

import web.back_end.member.wallet.entity.WalletTransHist;

public interface WalletTransHistDao{
	int insert(WalletTransHist walletTransHist);
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
