package web.front_end.member.forum.dao.impl;

import java.util.List;

import web.front_end.member.forum.dao.RpArticleMsgDao;
import web.front_end.member.forum.entity.RpArticle;
import web.front_end.member.forum.entity.RpArticleMsg;

public class RpArticleMsgDaoImpl implements RpArticleMsgDao {

	@Override
	public int insert(RpArticleMsg rpArticleMsg) {
		getSession().persist(rpArticleMsg);
		return 1;
	}


	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(RpArticleMsg entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RpArticleMsg selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RpArticleMsg> selectAll() {
		final String hql = "FROM RpArticleMsg ORDER BY rpMsgNo";
		return getSession().createQuery(hql, RpArticleMsg.class).getResultList();
	}


	@Override
	// 針對"檢舉文章留言編號",更新檢舉文章留言狀態、檢舉文章留言結果動作
	public int updateByRpArticleMsgNo(Integer rpMsgNo, Integer msgeStat, Integer auditResult) {
		final String hql = "UPDATE RpArticleMsg SET msgeStat = :msgeStat, auditResult = :auditResult WHERE rpMsgNo = :rpMsgNo";
		int rowsUpdated = getSession().createQuery(hql)
				.setParameter("msgeStat", msgeStat)
				.setParameter("auditResult", auditResult)
				.setParameter("rpMsgNo", rpMsgNo)
				.executeUpdate();
		return rowsUpdated;
	}

}
