package web.front_end.member.wallet.dao.impl;

import java.util.List;
import java.io.Serializable;

import javax.persistence.criteria.*;

import org.hibernate.query.Query;

import web.front_end.member.wallet.dao.WalletTransHistDao;
import web.front_end.member.wallet.entity.WalletTransHist;

public class WalletTransHistDaoImpl implements WalletTransHistDao {

	public Class<WalletTransHist> getEntityClass() {
		return WalletTransHist.class;
	}

	@Override
	public List<WalletTransHist> selectAll() {
		return getSession().createQuery("from " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
	}

	@Override
	public List<WalletTransHist> selectAllByMember(int memberNo) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<WalletTransHist> criteriaQuery = builder.createQuery(this.getEntityClass());
        Root<WalletTransHist> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("memberNo"), memberNo));
        criteriaQuery.orderBy(builder.desc(root.get("walletNo")));
        
        Query<WalletTransHist> query = getSession().createQuery(criteriaQuery);
        List<WalletTransHist>results = query.getResultList();
        return results;
	}

	public Serializable save(WalletTransHist entity) {
		return getSession().save(entity);
	}

    public void persist(WalletTransHist entity) {
		getSession().persist(entity);
	}
    
    @Override
    public int insert(WalletTransHist entity) {
		return (int)save(entity);
	}

    @Override
    public WalletTransHist selectById(Integer id) {
		return getSession().get(getEntityClass(), id);
	}
    
    public void delete(WalletTransHist entity) {
		getSession().delete(entity);
	}
   
    @Override
	public int deleteById(Integer id) {
		WalletTransHist entity = selectById(id);
		delete(entity);
		return 1;
	}

    @Override
	public int update(WalletTransHist entity) {
		getSession().update(entity);
		return 1;
	}

    @Override
	public void saveOrUpdate(WalletTransHist entity) {
		getSession().saveOrUpdate(entity);
	}
}