package web.front_end.member.wallet.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import web.front_end.member.wallet.dao.WalletTransHistDao;
import web.front_end.member.wallet.entity.WalletTransHist;

public class WalletTransHistDaoImpl implements WalletTransHistDao {

    private SessionFactory sessionFactory;

    public WalletTransHistDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public WalletTransHist getById(Integer id) {
        return getSession().get(WalletTransHist.class, id);
    }

    @Override
    public List<WalletTransHist> getAll() {
        final String hql = "from WalletTransHist";
        return executeQuery(hql, WalletTransHist.class);
    }

    @Override
    public void saveWalletTransHist(WalletTransHist walletTransHist) {
        getSession().persist(walletTransHist);
    }

    @Override
    public void updateWalletTransHist(WalletTransHist walletTransHist) {
        getSession().update(walletTransHist);
    }

    @Override
    public void deleteWalletTransHist(WalletTransHist walletTransHist) {
        getSession().delete(walletTransHist);
    }

    @Override
    public List<WalletTransHist> selectAll() {
        final String hql = "from WalletTransHist";
        return executeQuery(hql, WalletTransHist.class);
    }

    @Override
    public int insert(WalletTransHist walletTransHist) {
        getSession().persist(walletTransHist);
        return 1;
    }

    // 刪除和更新方法暫未實現

    @Override
    public int deleteById(Integer id) {
        // 在這裡實現刪除
        WalletTransHist walletTransHist = getById(id);
        if (walletTransHist != null) {
            getSession().delete(walletTransHist);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(WalletTransHist walletTransHist) {
        // 在這裡實現更新
        getSession().update(walletTransHist);
        return 1;
    }

    @Override
    public WalletTransHist selectById(Integer id) {
        // 在這裡實現查詢
        return getById(id);
    }

    private <T> List<T> executeQuery(String hql, Class<T> clazz) {
        return getSession().createQuery(hql, clazz).getResultList();
    }
}