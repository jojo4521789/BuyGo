package web.front_end.member.forum.service.impl;

import java.util.List;

import web.front_end.member.forum.dao.ArticleMsgDao;
import web.front_end.member.forum.dao.impl.ArticleMsgDaoImpl;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.service.ArticleMsgService;

public class ArticleMsgServiceImpl implements ArticleMsgService {
	private ArticleMsgDao dao;

	public ArticleMsgServiceImpl() {
		dao = new ArticleMsgDaoImpl();
	}

	@Override
	public List<ArticleMsg> loadArticleMsgByArticleNo(Integer articleNo) {
		return dao.selectByArticleNo(articleNo);
	}


	@Override
	public ArticleMsg update(ArticleMsg articleMsg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleMsg> finAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Integer messageNumber) {
		return dao.deleteById(messageNumber) > 0;
		}


	@Override
	public ArticleMsg add(Integer member, ArticleMsg articleMsg) {
		if(articleMsg.getContent()== null) {
			articleMsg.setMessage("文章留言未輸入");
			articleMsg.setSuccessful(false);
			return articleMsg;
		}
		articleMsg.setMember(member);
		articleMsg.setMessage("留言新增成功");
		articleMsg.setSuccessful(true);
		
		dao.insert(articleMsg);
		
		return articleMsg;
	}

}
