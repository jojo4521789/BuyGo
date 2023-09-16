package web.front_end.member.forum.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import web.front_end.member.acc.dao.MemberDao;
import web.front_end.member.acc.dao.impl.MemberDaoImpl;
import web.front_end.member.acc.entity.Member;
import web.front_end.member.forum.dao.ArticleCollListDao;
import web.front_end.member.forum.dao.ArticleMsgDao;
import web.front_end.member.forum.dao.ArticleRecordDao;
import web.front_end.member.forum.dao.ForumArticleDao;
import web.front_end.member.forum.dao.RpArticleDao;
import web.front_end.member.forum.dao.impl.ArticleCollListDaoImpl;
import web.front_end.member.forum.dao.impl.ArticleMsgDaoImpl;
import web.front_end.member.forum.dao.impl.ArticleRecordDaoImpl;
import web.front_end.member.forum.dao.impl.ForumArticleDaoImpl;
import web.front_end.member.forum.dao.impl.RpArticleDaoImpl;
import web.front_end.member.forum.dto.ArticleCollListDto;
import web.front_end.member.forum.dto.ForumArticleDTO;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ArticleRecord;
import web.front_end.member.forum.entity.ForumArticle;
import web.front_end.member.forum.entity.RpArticle;
import web.front_end.member.forum.service.ForumArticleService;

public class ForumArticleServiceImpl implements ForumArticleService {

	private ForumArticleDao dao;

	private ArticleRecordDao articleRecordDao;

	private ArticleMsgDao articleMsgDao;

	private ArticleCollListDao articleCollListDao;

	private RpArticleDao rpArticleDao;

	private MemberDao memberDao;

	public ForumArticleServiceImpl() {
		dao = new ForumArticleDaoImpl();
		articleRecordDao = new ArticleRecordDaoImpl();
		articleMsgDao = new ArticleMsgDaoImpl();
		articleCollListDao = new ArticleCollListDaoImpl();
		rpArticleDao = new RpArticleDaoImpl();
		memberDao = new MemberDaoImpl();
	}

	@Override
	public ForumArticle add(Integer memberNo, ForumArticle forumArticle) {
		if (forumArticle.getArticleTitle() == null) {
			forumArticle.setMessage("文章標題未輸入");
			forumArticle.setSuccessful(false);
			return forumArticle;
		}
		if (forumArticle.getArticleContent() == null) {
			forumArticle.setMessage("文章內容未輸入");
			forumArticle.setSuccessful(false);
			return forumArticle;
		}

		forumArticle.setMemberNo(memberNo);
		forumArticle.setMessage("文章新增成功");
		forumArticle.setSuccessful(true);

		dao.insert(forumArticle);

		return forumArticle;
	}

	@Override
	public ForumArticle update(ForumArticle forumArticle) {
		final int resultCount = dao.update(forumArticle);
		forumArticle.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		forumArticle.setSuccessful(resultCount > 0);
		return forumArticle;
	}

	@Override
	public List<ForumArticle> finAll() {
		List<ForumArticle> lstArticles = dao.selectAll();
		for (ForumArticle article : lstArticles) {
			System.out.print(article.getArticleTitle() + " : " + article.getArticleContent());
		}
		return lstArticles;
	}

//	@Override
//	public List<ForumArticleDTO> loadAllArticleInfo(int selfMemberNo) {
//	    List<ForumArticleDTO> result = new ArrayList<>();
//
//	    List<ForumArticle> lstArticleData = dao.selectAll(); // 原始ForumArticle数据
//	    List<Member> members = memberDao.selectAll();// 会员数据
//
//	    for (ForumArticle articleData : lstArticleData) {
//	        // 根据成员号码查找匹配的会员数据
//	        Member matchingMember = findMatchingMember(members, articleData.getMemberNo());
//
//	        if (matchingMember != null) {
//	            ForumArticleDTO dto = new ForumArticleDTO();
//	            // 复制ForumArticle的数据到DTO对象中
//	            dto.setMemberNo(articleData.getMemberNo());
//	            dto.setArticleNo(articleData.getArticleNo());
//	            dto.setArticleContent(articleData.getArticleContent());
//	            dto.setArticleDislike(articleData.getArticleDislike());
//	            dto.setArticleLike(articleData.getArticleLike());
//	            dto.setArticlePublish(articleData.getArticlePublish());
//	            dto.setArticleStatus(articleData.getArticleStatus());
//	            dto.setArticleTitle(articleData.getArticleTitle());
//	            dto.setArticleUpdate(articleData.getArticleUpdate());
//	            
//	            // 复制匹配的会员数据到DTO对象中
//	            dto.setMemberAcct(matchingMember.getMemberAcct()); // 例如，假设会员有名称字段
//
//	            result.add(dto);
//	        }
//	    }
//
//	    return result;
//	}
//
//	private Member findMatchingMember(List<Member> members, int memberNo) {
//	    for (Member member : members) {
//	        if (member.getMemberNo() == memberNo) {
//	            return member;
//	        }
//	    }
//	    return null; // 如果找不到匹配的会员，返回null
//	}
//
//
//	// ==============================================以下別刪到======================================================================
//
//		for (ForumArticle articleData : lstArticleData) {
//			ForumArticleDTO articleInfo = convertArticleInfo(articleData);
//
//			int articleNo = articleData.getArticleNo();
//			// int memberNo = articleData.getMemberNo();
//			
//			//List<Member> members = memberDao.selectAll();
//			articleInfo.setMembers(members);
//
//			// 留言文章(By文章編號)載入
//			List<ArticleMsg> lstArticleMsgs = articleMsgDao.selectByArticleNo(articleNo);
//			articleInfo.setLstMsg(lstArticleMsgs);
//			
//			// 收藏會員(By文章編號)載入
//			List<ArticleCollList> lstCollLists = articleCollListDao.selectByMemberNo(articleNo);
//			articleInfo.setLstCollLists(lstCollLists);
//
//			// -- Like 數量
//			// select count(*) from acticle_record where ARTICLE_NO = 1 and
//			// ACTICLE_RECORD_STAT = 0;
////			int likeCount = articleRecordDao.queryCountByArticleNoAndRcordStat(articleNo, 0);
////			articleInfo.setLikeCount(likeCount);
//
//			// -- Unlike 數量
//			// select count(*) from acticle_record where ARTICLE_NO = 1 and
//			// ACTICLE_RECORD_STAT = 1;
////			int unlikeCount = articleRecordDao.queryCountByArticleNoAndRcordStat(articleNo, 1);
////			articleInfo.setUnLikeCount(unlikeCount);
//
//			// -- 文章評價
//			// select * from acticle_record where ARTICLE_NO = 1 and MEMBER_NO = 1;
////			ArticleRecord record = articleRecordDao.queryCountByArticleNoAndMemeberNo(articleNo, selfMemeberNo);
////			if (record != null) {
////				if (record.getArticleRecordStat() != null) {
////					articleInfo.setSelfFavStatus(record.getArticleRecordStat());
////				}
////			}
//
//			lstArticleInfo.add(articleInfo);
//			// System.out.print(article.getArticleTitle() + " : " +
//			// article.getArticleContent());
//		}
//
//		return lstArticleInfo;
//	}
//
//	private ForumArticleDTO convertArticleInfo(ForumArticle articleData) {
//		ForumArticleDTO articleInfo = new ForumArticleDTO();
//		articleInfo.setArticleNo(articleData.getArticleNo());
//		articleInfo.setMemberNo(articleData.getMemberNo());
//		articleInfo.setArticleTitle(articleData.getArticleTitle());
//		articleInfo.setArticleContent(articleData.getArticleContent());
//		articleInfo.setArticleStatus(articleData.getArticleStatus());
//		articleInfo.setArticlePublish(articleData.getArticlePublish());
//		articleInfo.setArticleUpdate(articleData.getArticleUpdate());
//		articleInfo.setArticleLike(articleData.getArticleLike());
//		articleInfo.setArticleDislike(articleData.getArticleDislike());
//		return articleInfo;
//	}
//========================================================以上別刪到==========================================================================================
	
	@Override
	public List<ForumArticleDTO> loadAllArticleInfo(int selfMemberNo) {
	    List<ForumArticleDTO> lstArticleInfo = new ArrayList<>();
	    List<ForumArticle> lstArticleData = dao.selectAll(); // 原始ForumArticle数据
	    List<Member> members = memberDao.selectAll();// 会员数据

	    for (ForumArticle articleData : lstArticleData) {
	        ForumArticleDTO articleInfo = convertArticleInfo(articleData, members);

	        int articleNo = articleData.getArticleNo();
	        articleInfo.setMembers(members);

	        // 留言文章(By文章編號)載入
	        List<ArticleMsg> lstArticleMsgs = articleMsgDao.selectByArticleNo(articleNo);
	        articleInfo.setLstMsg(lstArticleMsgs);
	        
	        // 收藏會員(By文章編號)載入
//	        List<ArticleCollList> lstCollLists = articleCollListDao.selectByMemberNo(articleNo);
//	        articleInfo.setLstCollLists(lstCollLists);

	        // -- Like 數量
	        // ...

	        // -- Unlike 數量
	        // ...

	        // -- 文章評價
	        // ...

	        lstArticleInfo.add(articleInfo);
	    }

	    return lstArticleInfo;
	}

	private ForumArticleDTO convertArticleInfo(ForumArticle articleData, List<Member> members) {
	    ForumArticleDTO articleInfo = new ForumArticleDTO();
	    articleInfo.setArticleNo(articleData.getArticleNo());
	    articleInfo.setMemberNo(articleData.getMemberNo());
	    articleInfo.setArticleTitle(articleData.getArticleTitle());
	    articleInfo.setArticleContent(articleData.getArticleContent());
	    articleInfo.setArticleStatus(articleData.getArticleStatus());
	    articleInfo.setArticlePublish(articleData.getArticlePublish());
	    articleInfo.setArticleUpdate(articleData.getArticleUpdate());
	    articleInfo.setArticleLike(articleData.getArticleLike());
	    articleInfo.setArticleDislike(articleData.getArticleDislike());

	    // 在这里，您可以设置会员帐号信息
	    Member matchingMember = findMatchingMember(members, articleData.getMemberNo());
	    if (matchingMember != null) {
	        articleInfo.setMemberAcct(matchingMember.getMemberAcct()); // 例如，假设会员有名称字段
	    }

	    return articleInfo;
	}

	private Member findMatchingMember(List<Member> members, int memberNo) {
	    for (Member member : members) {
	        if (member.getMemberNo() == memberNo) {
	            return member;
	        }
	    }
	    return null; // 如果找不到匹配的会员，返回null
	}


	
	//===================================================================================================
	@Override
	public boolean remove(Integer articleNo) {
		int articleCollList = articleCollListDao.deleteByIdAll(articleNo);
		int articleMsg = articleMsgDao.deleteByIdAll(articleNo);
		int RpArticleDao = rpArticleDao.deleteByIdAll(articleNo);
		int totel = articleCollList + articleMsg + RpArticleDao;
		if (totel >= 0) {
			int forumArticle = dao.deleteById(articleNo);
			return forumArticle > 0;
		} else {
			return false;
		}
	}

	@Override
	public List<ForumArticle> loadForumArticleByArticleNo(Integer articleNo) {
		return dao.selectByArticleNo(articleNo);
	}

	@Override
	public boolean updateByArticleNo(Integer articleNo, Integer articleStatus) {
		return dao.updateByArticleNo(articleNo, articleStatus) > 0;
	}

	@Override
	public List<ForumArticle> loadForumArticleBymemberNo(Integer memberNo) {
		return dao.selectByMemberNo(memberNo);

	}

	@Override
	public List<ForumArticle> loadForumArticleBymemberNo_test(Integer memberNo) {
		return dao.selectByMemberNo_test(memberNo);
	}

}
