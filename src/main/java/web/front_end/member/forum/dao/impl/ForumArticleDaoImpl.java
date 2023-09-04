package web.front_end.member.forum.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.forum.dao.ForumArticleDao;
import web.front_end.member.forum.entity.ForumArticle;

public class ForumArticleDaoImpl implements ForumArticleDao {

	@Override
	public int insert(ForumArticle forumArticle) {
		getSession().persist(forumArticle);
		return 1;
	}

	@Override
	public int deleteById(Integer articleNo) {
		Session session = getSession();
		ForumArticle forumArticle = session.get(ForumArticle.class, articleNo);
		session.remove(forumArticle);
		return 0;
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
		final String hq1 = "FROM ForumArticle ORDER BY articleNo";
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

}
