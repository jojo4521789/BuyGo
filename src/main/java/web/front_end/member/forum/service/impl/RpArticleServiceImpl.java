package web.front_end.member.forum.service.impl;

import java.util.List;

import web.front_end.member.forum.dao.RpArticleDao;
import web.front_end.member.forum.dao.impl.RpArticleDaoImpl;
import web.front_end.member.forum.entity.ForumArticle;
import web.front_end.member.forum.entity.RpArticle;
import web.front_end.member.forum.service.RpArticleService;

public class RpArticleServiceImpl implements RpArticleService {
	private RpArticleDao dao;

	public RpArticleServiceImpl() {
		dao = new RpArticleDaoImpl();
	}

	@Override
	public RpArticle update(RpArticle rpArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RpArticle> finAll() {
		List<RpArticle> listrRpArticles = dao.selectAll();
		return listrRpArticles;
	}

	@Override
	public boolean remove(Integer reportArticleNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	// 針對"檢舉文章編號",更新檢舉文章狀態、檢舉文章結果動作
	public boolean updateByRpArticleNo(Integer rpArticleNo, Integer articleStatus, Integer auditResult) {
		return dao.updateByRpArticleNo(rpArticleNo, articleStatus, auditResult) > 0;
	}

	@Override
	public RpArticle add(Integer rpmemberNo, RpArticle rpArticle) {
		if (rpArticle.getReportContent() == null) {
			rpArticle.setMessage("檢舉文章內容未輸入");
			rpArticle.setSuccessful(false);
			return rpArticle;
		}
		rpArticle.setRpmemberNo(rpmemberNo);
		rpArticle.setMessage("檢舉新增成功");
		rpArticle.setSuccessful(true);

		dao.insert(rpArticle);
		return rpArticle;
	}
}
