package web.front_end.member.forum.dao.impl;

import java.util.List;

import web.front_end.member.forum.dao.ArticleMsgDao;
import web.front_end.member.forum.entity.ArticleMsg;

public class ArticleMsgDaoImpl implements ArticleMsgDao {

	@Override
	// 新增文章留言內容
	public int insert(ArticleMsg articleMsg) {
		getSession().persist(articleMsg);
		return 1;
	}

	@Override
	// 只改變文章狀態0 變 1
	public int deleteById(Integer messageNumber) {
		final String hql = "UPDATE ArticleMsg SET messageStatus = 1 WHERE messageNumber = :messageNumber";
		int rowsUpdated = getSession().createQuery(hql).setParameter("messageNumber", messageNumber).executeUpdate();
		return rowsUpdated;

	}

	@Override
	public int update(ArticleMsg entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArticleMsg selectById(Integer articleNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleMsg> selectAll() {
		final String hql = "FROM ArticleMsg ORDER BY messageNumber";
		return getSession().createQuery(hql, ArticleMsg.class).getResultList();
	}

	@Override
	//針對文章編號做查詢的動作進行降冪(只"狀態是0"的留言才會回傳list出去)
	public List<ArticleMsg> selectByArticleNo(Integer articleNo) {
	    final String hql = "FROM ArticleMsg WHERE articleNo = :articleNo AND messageStatus = 0 ORDER BY messageNumber";
	    return getSession().createQuery(hql, ArticleMsg.class).setParameter("articleNo", articleNo).getResultList();
	}


}
