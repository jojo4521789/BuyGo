package web.back_end.member.wallet.dao;


import static core.util.HibernateUtil.getSessionFactory;

import org.hibernate.Session;

import web.back_end.member.wallet.entity.BackEndWalletTransHist;

public interface WalletTransHistDao{
	int insert(BackEndWalletTransHist walletTransHist);
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
