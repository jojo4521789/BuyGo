package web.front_end.member.forum.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.front_end.member.forum.dao.ForumArticleDao;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;

public class ForumArticleDaoImpl implements ForumArticleDao {

//	public static void main(String[] args) {
//		ForumArticleDaoImpl forumArticleDaoImpl = new ForumArticleDaoImpl();
//		Session session = forumArticleDaoImpl.getSession();
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		
//		forumArticleDaoImpl.deleteById(1);
//		
//		transaction.commit(); // 結束交易
//				
//	}

	@Override
	// 新增文章內容和標題
	public int insert(ForumArticle forumArticle) {
		getSession().persist(forumArticle);
		return 1;
	}

	@Override
	// 只改變文章狀態0 變 1
	public int deleteById(Integer articleNo) {
		final String hql = "DELETE ForumArticle WHERE articleNo = :articleNo";
		int rowsUpdated = getSession().createQuery(hql).setParameter("articleNo", articleNo).executeUpdate();
		return rowsUpdated;

	}

	@Override
	// 更新映射欄位資料
	public int update(ForumArticle forumArticle) {
		Session session = getSession();
		ForumArticle oldForumArticle = session.get(ForumArticle.class, forumArticle.getArticleNo());// 取得目前資料庫中的紀錄，以便以下進行更新
		final String articleTitle = forumArticle.getArticleTitle();
		if (articleTitle != null) {
			oldForumArticle.setArticleTitle(articleTitle);
		}
		final String articleContent = forumArticle.getArticleContent();
		if (articleContent != null) {
			oldForumArticle.setArticleContent(articleContent);
		}
		final Integer articleStatus = forumArticle.getArticleStatus();
		if (articleStatus != null) {
			oldForumArticle.setArticleStatus(articleStatus);
		}
		final Timestamp articlePublish = forumArticle.getArticlePublish();
		if (articlePublish != null) {
			oldForumArticle.setArticlePublish(articlePublish);
		}

		final Timestamp articleUpdate = forumArticle.getArticleUpdate();
		if (articleUpdate != null) {
			oldForumArticle.setArticleUpdate(articleUpdate);
		}
		final Integer articleLike = forumArticle.getArticleLike();
		if (articleLike != null) {
			oldForumArticle.setArticleLike(articleLike);
		}
		final Integer articleDislike = forumArticle.getArticleDislike();
		if (articleDislike != null) {
			oldForumArticle.setArticleDislike(articleDislike);
		}

		return 1;
	}

	@Override
	public ForumArticle selectById(Integer articleNo) {
		return getSession().get(ForumArticle.class, articleNo);
	}

	@Override
	public List<ForumArticle> selectAll() {
		final String hq1 = "FROM ForumArticle WHERE articleStatus = 0";
		return getSession().createQuery(hq1, ForumArticle.class).getResultList();

	}

	@Override
	// 根據 articleTitle(文章標題) 值查詢 ForumArticle 表格
	public ForumArticle selectByForumArticleTitle(String articleTitle) {
		try {
			Query<ForumArticle> query = getSession()
					.createQuery("FROM ForumArticle WHERE articleTitle = :articleTitle", ForumArticle.class)
					.setParameter("articleTitle", articleTitle);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	// 根據 articleNo(文章編號) 值查詢 ForumArticle 表格() "回傳只有該articleNo(文章編號)的list"
	public List<ForumArticle> selectByArticleNo(Integer articleNo) {
		final String hql = "FROM ForumArticle WHERE articleNo = :articleNo";
		return getSession().createQuery(hql, ForumArticle.class).setParameter("articleNo", articleNo).getResultList();
	}
	

	@Override
	public int updateByArticleNo(Integer articleNo, Integer articleStatus) {
		final String hql = "UPDATE ForumArticle SET articleStatus = :articleStatus WHERE articleNo = :articleNo";
		int rowsUpdated = getSession().createQuery(hql)
				.setParameter("articleStatus", articleStatus)
				.setParameter("articleNo", articleNo)
				.executeUpdate();
		return rowsUpdated;
	}

	@Override
	public List<ForumArticle> selectByMemberNo(Integer memberNo) {
		final String hql = "FROM ForumArticle WHERE memberNo = :memberNo AND articleStatus = 0 ORDER BY articlePublish DESC";
		return getSession().createQuery(hql, ForumArticle.class).setParameter("memberNo", memberNo).getResultList();
	
	}

	@Override
	public List<ForumArticle> selectByMemberNo_test(Integer memberNo) {
		  final String hql = "SELECT AC FROM ArticleCollList AC WHERE AC.memberNo = :memberNo";
		    return getSession().createQuery(hql, ForumArticle.class)
		            .setParameter("memberNo", memberNo)
		            .getResultList();
	}

}
