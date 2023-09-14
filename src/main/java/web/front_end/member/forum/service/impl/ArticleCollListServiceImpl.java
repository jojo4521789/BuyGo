package web.front_end.member.forum.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import web.front_end.member.forum.dao.ArticleCollListDao;
import web.front_end.member.forum.dao.ForumArticleDao;
import web.front_end.member.forum.dao.impl.ArticleCollListDaoImpl;
import web.front_end.member.forum.dao.impl.ForumArticleDaoImpl;
import web.front_end.member.forum.dto.ArticleCollListDto;
import web.front_end.member.forum.dto.ForumArticleDTO;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ForumArticle;
import web.front_end.member.forum.service.ArticleCollListService;

public class ArticleCollListServiceImpl implements ArticleCollListService {

	private ArticleCollListDao dao;

	private ForumArticleDao forumArticleDao;

	public ArticleCollListServiceImpl() {
		forumArticleDao = new ForumArticleDaoImpl();
		dao = new ArticleCollListDaoImpl();
	}

//	@Override
//	public List<ArticleCollList> loadArticleCollListBymemberNo(Integer memberNo) {
//		List<ArticleCollList> resultList = new ArrayList<>();
//		List<ArticleCollList> articleCollList = dao.selectByMemberNo(memberNo);
//		List<ForumArticle> forumArticlelList = forumArticleDao.selectAll();
//
//		for (ArticleCollList collList : articleCollList) {
//
//			ForumArticle resultItem = new ForumArticle();
//			resultItem.setArticleNo(collList.getForumArticle().getArticleNo());
//			resultItem.setArticleContent(collList.getForumArticle().getArticleContent());
//			resultList.add(collList);
//		}
//		return resultList;
//	}
//	@Override
//	public List<ArticleCollList> loadArticleCollListBymemberNo(Integer memberNo) {
//	    List<ForumArticle> resultList = new ArrayList<>();
//	    List<ArticleCollList> articleCollList = dao.selectByMemberNo(memberNo);
//	    List<ForumArticle> forumArticlelList = forumArticleDao.selectAll();
//
//	    for (ArticleCollList collList : articleCollList) {
//	        for (ForumArticle forumArticle : forumArticlelList) {
//	            // 根据条件找到匹配的 ForumArticle
//	            if (collList.getForumArticle() != null && collList.getForumArticle().getArticleNo() == forumArticle.getArticleNo()) {
//	                ForumArticle resultItem = new ForumArticle();
//	                resultItem.setArticleNo(forumArticle.getArticleNo());
//	                resultItem.setArticleContent(forumArticle.getArticleContent());
//	                resultList.add(resultItem);
//	                break; // 找到匹配后跳出内部循环
//	            }
//	        }
//	    }
//	    return resultList;
//	}

//
//	@Override
//	public List<ArticleCollList> loadArticleCollListBymemberNo(Integer memberNo) {
//		List<ForumArticle> lstArticleData = forumArticleDao.selectAll(); // 原始資料
//		List<ArticleCollList> articleCollList = dao.selectByMemberNo(memberNo);
// 
//    // 創建一個新的列表，用於存儲建立了對應關系的結果
//    List<ArticleCollList> resultList = new ArrayList<>();
//
//    // 使用嵌套的循環將兩個列表之間建立對應關系
//    for (ForumArticle article : lstArticleData) {
//        for (ArticleCollList collList : articleCollList) {
//            if (collList.getMemberNo().equals(article.getMemberNo())) {
//                // 如果找到匹配的文章編號，將 ArticleCollList 對象添加到結果列表
//            	ForumArticle resultItem = new ForumArticle();
//                resultItem.setArticleNo(article.getArticleNo());
//                resultItem.setMemberNo(article.getMemberNo());
//                resultItem.setArticleContent(article.getArticleContent());
//               // resultItem.setArticleContent(article.getArticleContent());
//                //resultItem.getForumArticle().setArticleTitle(article.getArticleTitle());
//                resultList.add(resultItem);
//                break; // 跳出內部循環，因為已經找到匹配
//            }
//        }
//    }
//
//		return resultList;
//	}
//
	@Override
	public List<ArticleCollList> loadArticleCollListBymemberNo(Integer memberNo) {
		return dao.selectByMemberNo(memberNo);

	}

	@Override
	public boolean remove(Integer memberNo, Integer articleNo) {
		return dao.deleteById(memberNo, articleNo) > 0;
	}

	@Override
	public ArticleCollList add(Integer memberNo, ArticleCollList articleCollList) {
		if (articleCollList.getArticleNo() == null) {
			articleCollList.setMessage("此無文章編號");
			articleCollList.setSuccessful(false);
			return articleCollList;
		}
		articleCollList.setMemberNo(memberNo);
		articleCollList.setMessage("收藏新增成功");
		articleCollList.setSuccessful(true);

		dao.insert(articleCollList);

		return articleCollList;
	}

//	@Override
//	public List<ArticleCollListDto> loadAllArticleInfo(int selfMemeberNo) {
//		List<ForumArticle> lstArticleData = forumArticleDao.selectAll(); // 原始資料
//		List<ArticleCollList> articleCollList = dao.selectByMemberNo(selfMemeberNo);
//		return null;
//	}

	// =========================================ok的頭=================================================
	@Override
	public List<ArticleCollListDto> loadAllArticleInfo(int selfMemberNo) {
		// 获取原始数据
		List<ForumArticle> lstArticleData = forumArticleDao.selectAll(); // 原始資料
		List<ArticleCollList> articleCollList = dao.selectByMemberNo(selfMemberNo);

		// 创建用于存放结果的列表
		List<ArticleCollListDto> result = new ArrayList<>();

		// 遍历articleCollList，并将对应的ForumArticle数据找到并合并到结果列表中
		for (ArticleCollList articleColl : articleCollList) {
//			for (ForumArticle collList : lstArticleData) {
				// 在lstArticleData中查找与articleColl相关的ForumArticle
				ForumArticle matchingArticle = findMatchingArticle(lstArticleData, articleColl);
				// 如果找到了匹配的ForumArticle，则将数据合并到新的ArticleCollListDto中
//				if (articleColl.getMemberNo() == collList.getMemberNo()
//						&& articleColl.getArticleNo() == collList.getArticleNo()) {
				ArticleCollListDto dto = new ArticleCollListDto();
				// 将相关的数据复制到dto中
				dto.setMemberNo(articleColl.getMemberNo());
				dto.setArticleNo(articleColl.getArticleNo());
				dto.setArticleContent(matchingArticle.getArticleContent());
				dto.setArticleDislike(matchingArticle.getArticleDislike());
				dto.setArticleLike(matchingArticle.getArticleLike());
				dto.setArticlePublish(matchingArticle.getArticlePublish());
				dto.setArticleStatus(matchingArticle.getArticleStatus());
				dto.setArticleTitle(matchingArticle.getArticleTitle());
				dto.setArticleUpdate(matchingArticle.getArticleUpdate());

				// dto.setLstForumArticle(lstArticleData);
				dto.setLstCollLists(articleCollList);
				// dto.setForumArticle(matchingArticle);
				result.add(dto);
				dto = null;
//				}

			}
//		}

		return result;
	}

	private ForumArticle findMatchingArticle(List<ForumArticle> lstArticleData, ArticleCollList articleColl) {
		for (ForumArticle forumArticle : lstArticleData) {
			if (forumArticle.getArticleNo() == articleColl.getArticleNo()) {
				return forumArticle;
			}
		}
		return null; // 如果找不到匹配的ForumArticle，返回null
	}

	@Override
	public boolean select(Integer memberNo, Integer articleNo) {
		return dao.selectById(memberNo, articleNo) > 0;
	}

	// =========================================ok的尾=================================================

//	@Override
//	public List<ArticleCollListDto> loadAllArticleInfo(int selfMemberNo) {
//	    // 获取原始数据
//	    List<ForumArticle> lstArticleData = forumArticleDao.selectAll(); // 原始資料
//	    List<ArticleCollList> articleCollList = dao.selectByMemberNo(selfMemberNo);
//
//	    // 创建用于存放结果的列表
//	    List<ArticleCollListDto> result = new ArrayList<>();
//
//	    // 遍历articleCollList，并将对应的ForumArticle数据找到并合并到结果列表中
//	    for (ArticleCollList articleColl : articleCollList) {
//	        // 在lstArticleData中查找与articleColl相关的ForumArticle
//	        ForumArticle matchingArticle = findMatchingArticle(lstArticleData, articleColl);
//
//	        // 如果找到了匹配的ForumArticle，则将数据合并到新的ArticleCollListDto中
//	        if (matchingArticle != null) {
//	            ArticleCollListDto dto = new ArticleCollListDto();
//	            // 设置相关的数据
//	            dto.setArticleNo(matchingArticle.getArticleNo());
//	            dto.setMemberNo(matchingArticle.getMemberNo());
//	            dto.setLstForumArticle(Collections.singletonList(matchingArticle)); // 使用Collections.singletonList将匹配的ForumArticle包装成列表
//	            dto.setLstCollLists(Collections.singletonList(articleColl)); // 使用Collections.singletonList将articleColl包装成列表
//	            dto.setSuccessful(matchingArticle.isSuccessful()); // 设置其他相关字段
//	            result.add(dto);
//	        }
//	    }
//
//	    return result;
//	}
//
//	// 辅助方法，根据memberNo和articleNo查找匹配的ForumArticle
//	private ForumArticle findMatchingArticle(List<ForumArticle> lstArticleData, ArticleCollList articleColl) {
//	    for (ForumArticle forumArticle : lstArticleData) {
//	        if (forumArticle.getMemberNo() == articleColl.getMemberNo() &&
//	            forumArticle.getArticleNo() == articleColl.getArticleNo()) {
//	            return forumArticle;
//	        }
//	    }
//	    return null; // 如果找不到匹配的ForumArticle，返回null
//	}

//	@Override
//	public List<ArticleCollListDto> loadAllArticleInfo(int selfMemberNo) {
//	    // 获取原始数据
//	    List<ForumArticle> lstArticleData = forumArticleDao.selectAll(); // 原始资料
//	    List<ArticleCollList> articleCollList = dao.selectByMemberNo(selfMemberNo);
//
//	    // 创建用于存放结果的列表
//	    List<ArticleCollListDto> result = new ArrayList<>();
//
//	    // 遍历articleCollList，并将对应的ForumArticle数据找到并合并到结果列表中
//	    for (ArticleCollList articleColl : articleCollList) {
//	        // 在lstArticleData中查找与articleColl相关的ForumArticle
//	        ForumArticle matchingArticle = findMatchingArticle(lstArticleData, articleColl);
//
//	        // 如果找到了匹配的ForumArticle，则将数据合并到新的ArticleCollListDto中
//	        if (matchingArticle != null) {
//	            ArticleCollListDto dto = new ArticleCollListDto();
//	            // 设置相关的数据
//	            dto.setArticleNo(matchingArticle.getArticleNo());
//	            dto.setMemberNo(matchingArticle.getMemberNo());
//
//	            // 获取lstCollLists中所有的articleNo
//	            List<Integer> articleNos = new ArrayList<>();
//	            for (ArticleCollList collList : articleCollList) {
//	                if (collList.getMemberNo() == articleColl.getMemberNo()) {
//	                    articleNos.add(collList.getArticleNo());
//	                }
//	            }
//	            dto.setLstCollLists(articleCollList);
//
//	            // 获取lstForumArticle中对应articleNo的articleTitle和articleContent
//	            List<Map<String, Object>> forumArticleData = new ArrayList<>();
//	            for (Integer articleNo : articleNos) {
//	                for (ForumArticle forumArticle : lstArticleData) {
//	                    if (forumArticle.getArticleNo() == articleNo) {
//	                        Map<String, Object> articleData = new HashMap<>();
//	                        articleData.put("articleNo", forumArticle.getArticleNo());
//	                        articleData.put("memberNo", forumArticle.getMemberNo());
//	                        articleData.put("articleTitle", forumArticle.getArticleTitle());
//	                        articleData.put("articleContent", forumArticle.getArticleContent());
//	                        forumArticleData.add(articleData);
//	                    }
//	                }
//	            }
//	            dto.setLstForumArticle(lstArticleData);
//
//	            // 设置其他相关字段
//	            dto.setSuccessful(matchingArticle.isSuccessful());
//	            result.add(dto);
//	        }
//	    }
//
//	    return result;
//	}
//
//	// 辅助方法，根据memberNo和articleNo查找匹配的ForumArticle
//	private ForumArticle findMatchingArticle(List<ForumArticle> lstArticleData, ArticleCollList articleColl) {
//	    for (ForumArticle forumArticle : lstArticleData) {
//	        if (forumArticle.getMemberNo() == articleColl.getMemberNo() &&
//	            forumArticle.getArticleNo() == articleColl.getArticleNo()) {
//	            return forumArticle;
//	        }
//	    }
//	    return null; // 如果找不到匹配的ForumArticle，返回null
//	}

//	@Override
//	public List<ArticleCollListDto> loadAllArticleInfo(int selfMemeberNo) {
//		List<ArticleCollListDto> lstArticleInfo = new LinkedList<ArticleCollListDto>();
//
//		List<ArticleCollList> lstArticleData = dao.selectByMemberNo(selfMemeberNo);
//
//		for (ArticleCollList articleData : lstArticleData) {
//			ForumArticleDTO articleInfo = convertArticleInfo(articleData);
//		}
//		return null;
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
//	@Override
//	public List<ArticleCollListDto> loadAllArticleInfo(int selfMemberNo) {
//		List<ArticleCollListDto> lstArticleInfo = new LinkedList<ArticleCollListDto>();
//
//		List<ArticleCollList> lstArticleData = dao.selectByMemberNo(selfMemberNo);
//
//		for (ArticleCollList articleData : lstArticleData) {
//			ArticleCollListDto articleInfo = convertArticleInfo(articleData);
//			int memberNo = articleData.getMemberNo();
//			List<ArticleCollList> lstCollLists = dao.selectByMemberNo(memberNo);
//			articleInfo.setLstCollLists(lstCollLists);
//			articleInfo.setArticleTitle(articleData.getForumArticle().getArticleTitle());
//
//			lstArticleInfo.add(articleInfo); // 添加到列表中
//		}
//
//		return lstArticleInfo;
//	}
//
//	private ArticleCollListDto convertArticleInfo(ArticleCollList articleData) {
//		ArticleCollListDto articleInfo = new ArticleCollListDto();
//
//		// 检查 articleData.getForumArticle() 是否为 null
//		if (articleData.getForumArticle() != null) {
//			articleInfo.setArticleNo(articleData.getForumArticle().getArticleNo());
//			// articleInfo.setMemberNo(articleData.getMember().getMemberNo()); // 注意这里获取
//			// MemberNo
//			articleInfo.setArticleTitle(articleData.getForumArticle().getArticleTitle());
//			articleInfo.setArticleContent(articleData.getForumArticle().getArticleContent());
//			articleInfo.setArticleStatus(articleData.getForumArticle().getArticleStatus());
//			articleInfo.setArticlePublish(articleData.getForumArticle().getArticlePublish());
//			articleInfo.setArticleUpdate(articleData.getForumArticle().getArticleUpdate());
//			articleInfo.setArticleLike(articleData.getForumArticle().getArticleLike());
//			articleInfo.setArticleDislike(articleData.getForumArticle().getArticleDislike());
//		}
//
//		return articleInfo;
//	}

}
