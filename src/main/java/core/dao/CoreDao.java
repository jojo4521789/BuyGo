package core.dao;

import java.util.List;

import org.hibernate.Session;

import static core.util.HibernateUtil.getSessionFactory;

public interface CoreDao<E, I> {
	int insert(E entity);

	int deleteById(I id);

//	int update(E entity);

	E selectById(I id);

	List<E> selectAll();

	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
