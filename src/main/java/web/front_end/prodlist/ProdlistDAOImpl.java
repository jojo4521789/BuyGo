package web.front_end.prodlist;

import java.util.List;

public class ProdlistDAOImpl implements ProdlistDAO{

	public List<Prodlist> selectAll() {

		final String hql = "FROM Prodlist ORDER BY Prodlist";		
		return getSession().createQuery(hql, Prodlist.class).getResultList();
		
	}

	@Override
	public int insert(Prodlist pojo) {
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		return 0;
	}

	@Override
	public int update(Prodlist pojo) {
		return 0;
	}

	@Override
	public Prodlist selectById(Integer id) {
		return null;
	}

}
