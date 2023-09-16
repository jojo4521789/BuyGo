package web.front_end.member.forum.dao.impl;

import java.util.List;

import web.front_end.member.forum.dao.ArticleCollListDao;
import web.front_end.member.forum.dto.ArticleCollListDto;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;

public class ArticleCollListDaoImpl implements ArticleCollListDao {

	@Override
	public List<ArticleCollList> selectByMemberNo(Integer memberNo) {
		final String hql = "FROM ArticleCollList WHERE memberNo = :memberNo";
		return getSession().createQuery(hql, ArticleCollList.class).setParameter("memberNo", memberNo).getResultList();
	}

	@Override
	public int deleteById(Integer memberNo, Integer articleNo) {
		final String hql = "DELETE FROM ArticleCollList WHERE memberNo = :memberNo AND articleNo = :articleNo";
		int rowsUpdated = getSession().createQuery(hql)
				.setParameter("memberNo", memberNo)
				.setParameter("articleNo", articleNo).executeUpdate();
		return rowsUpdated;
	}

//	@Override
//	public int selectById(Integer memberNo, Integer articleNo) {
//		final String hql = "FROM ArticleCollList WHERE memberNo = :memberNo AND articleNo = :articleNo";
//		int rowsUpdated =getSession().createQuery(hql).setParameter("memberNo", memberNo)
//				.setParameter("articleNo", articleNo).executeUpdate();
//		return rowsUpdated;
//	}

	@Override
	public int selectById(Integer memberNo, Integer articleNo) {
	    final String hql = "SELECT COUNT(*) FROM ArticleCollList WHERE memberNo = :memberNo AND articleNo = :articleNo";
	    Long rowCount = (Long) getSession().createQuery(hql)
	        .setParameter("memberNo", memberNo)
	        .setParameter("articleNo", articleNo)
	        .uniqueResult();

	    // 转换为 int 类型并返回
	    return rowCount != null ? rowCount.intValue() : 0;
	}

	
	
	
	@Override
	public int insert(ArticleCollList articleCollList) {
		getSession().persist(articleCollList);
		return 1;
	}

	@Override
	public int update(ArticleCollList entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArticleCollList selectById(Integer memberNo) {
		final String sql = "SELECT * FROM ArticleCollList WHERE memberNo = :memberNo";
		return null;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ArticleCollList> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByIdAll(Integer articleNo) {
		final String hql = "DELETE FROM ArticleCollList WHERE articleNo = :articleNo";
		int rowsUpdated = getSession().createQuery(hql)
				.setParameter("articleNo", articleNo).executeUpdate();
		return rowsUpdated;
	}
	

}
