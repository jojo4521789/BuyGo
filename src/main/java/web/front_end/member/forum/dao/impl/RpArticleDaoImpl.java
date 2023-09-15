package web.front_end.member.forum.dao.impl;

import java.util.List;

import web.front_end.member.forum.dao.RpArticleDao;
import web.front_end.member.forum.entity.RpArticle;

public class RpArticleDaoImpl implements RpArticleDao {

	@Override
	// 新增檢舉文章內容
	public int insert(RpArticle rpArticle) {
		getSession().persist(rpArticle);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(RpArticle entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RpArticle selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RpArticle> selectAll() {
		final String hql = "FROM RpArticle ORDER BY rpArticleNo";
		return getSession().createQuery(hql, RpArticle.class).getResultList();

	}

	@Override
	// 針對"檢舉文章編號",更新檢舉文章狀態、檢舉文章結果動作
	public int updateByRpArticleNo(Integer rpArticleNo, Integer articleStatus, Integer auditResult) {
		final String hql = "UPDATE RpArticle SET articleStatus = :articleStatus, auditResult = :auditResult WHERE rpArticleNo = :rpArticleNo";
		int rowsUpdated = getSession().createQuery(hql)
				.setParameter("articleStatus", articleStatus)
				.setParameter("auditResult", auditResult)
				.setParameter("rpArticleNo", rpArticleNo)
				.executeUpdate();
		return rowsUpdated;
	}

	@Override
	public int deleteByIdAll(Integer articleNo) {
		final String hql = "DELETE FROM RpArticle WHERE articleNo = :articleNo";
		int rowsUpdated = getSession().createQuery(hql)
				.setParameter("articleNo", articleNo).executeUpdate();
		return rowsUpdated;
	}

}
