package web.front_end.member.forum.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.front_end.member.forum.dao.ArticleRecordDao;
import web.front_end.member.forum.dao.ForumArticleDao;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ArticleRecord;
import web.front_end.member.forum.entity.ForumArticle;

public class ArticleRecordDaoImpl implements ArticleRecordDao {

	@Override
	public int insert(ArticleRecord entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ArticleRecord entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArticleRecord selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleRecord> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer queryCountByArticleNoAndRcordStat(int articleNo, int stat) {
		// select count(*) from acticle_record where ARTICLE_NO = 1 and
		// ACTICLE_RECORD_STAT = 0;
		final String hql = "Select count(*) FROM ArticleRecord WHERE articleNo = :articleNo and ACTICLE_RECORD_STAT = :stat";
		Query<Long> query = getSession().createQuery(hql, Long.class) // 載入 HQL
				.setParameter("articleNo", articleNo) // 子句 條件
				.setParameter("stat", stat);
		Integer count = query.uniqueResult().intValue();
		return count;
	}

	@Override
	public ArticleRecord queryCountByArticleNoAndMemeberNo(int articleNo, int memberNo) {
		final String hql = "FROM ArticleRecord WHERE articleNo = :articleNo and memberNo = :memberNo ";

		try {
			Query<ArticleRecord> query = getSession().createQuery(hql, ArticleRecord.class)
					.setParameter("articleNo", articleNo).setParameter("memberNo", memberNo);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
