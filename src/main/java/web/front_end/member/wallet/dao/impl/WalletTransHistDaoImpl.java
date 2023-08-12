package web.front_end.member.wallet.dao.impl;

import java.util.List;
import org.hibernate.Session;

import web.front_end.member.wallet.dao.WalletTransHistDao;
import web.front_end.member.wallet.entity.WalletTransHist;

public class WalletTransHistDaoImpl implements WalletTransHistDao{

    private final Session session;

    public WalletTransHistDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public int insert(WalletTransHist walletTransHist) {
        session.persist(walletTransHist);
        return 1;
    }

    @Override
    public int deleteById(Integer id) {
        WalletTransHist walletTransHist = selectById(id);
        if (walletTransHist != null) {
            session.delete(walletTransHist);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(WalletTransHist walletTransHist) {
        session.update(walletTransHist);
        return 1;
    }

    @Override
    public WalletTransHist selectById(Integer id) {
        return session.get(WalletTransHist.class, id);
    }

    @Override
    public List<WalletTransHist> selectAll() {
        String hql = "FROM WalletTransHist";
        return session.createQuery(hql, WalletTransHist.class).getResultList();
    }

    @Override
    public List<WalletTransHist> getByMemberNo(Integer memberNo) {
        String hql = "FROM WalletTransHist WHERE memberNo = :memberNo";
        return session.createQuery(hql, WalletTransHist.class)
                      .setParameter("memberNo", memberNo)
                      .getResultList();
    }

    @Override
    public List<WalletTransHist> getByWalletStatus(Integer walletStatus) {
        String hql = "FROM WalletTransHist WHERE walletStatus = :walletStatus";
        return session.createQuery(hql, WalletTransHist.class)
                      .setParameter("walletStatus", walletStatus)
                      .getResultList();
    }

    // CoreDao methods
    @Override
    public Session getSession() {
        return session;
    }
}