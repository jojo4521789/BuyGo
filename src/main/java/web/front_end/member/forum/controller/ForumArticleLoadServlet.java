package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ForumArticleConstants.SERVICE;

import web.front_end.member.forum.dto.ArticlelListDTO;
import web.front_end.member.forum.entity.ForumArticle;
import web.front_end.member.forum.entity.RpArticle;

@WebServlet("/api/forumArticle/loadarticlelList")
public class ForumArticleLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		
		ForumArticle forumArticle = json2Pojo(request, ForumArticle.class);
		Integer articleNo = forumArticle.getArticleNo();
		List<ForumArticle> forumArticleslList = SERVICE.loadForumArticleByArticleNo(articleNo);
		
		//DTO的寫法(袋中袋)
		List<ArticlelListDTO> articlelListDTOList = new LinkedList<ArticlelListDTO>();
		for(ForumArticle forumArticle_1 : forumArticleslList) {
			ArticlelListDTO articlelListDTO = new ArticlelListDTO();
			//這裡想要的欄位(論壇文章裡)
			articlelListDTO.getForumArticle().setArticleTitle(forumArticle_1.getArticleTitle());
			articlelListDTO.getForumArticle().setArticleContent(forumArticle_1.getArticleContent());
			articlelListDTO.getForumArticle().setArticleNo(forumArticle_1.getArticleNo()); 
			articlelListDTO.getForumArticle().setMemberNo(forumArticle_1.getMemberNo());
			articlelListDTO.getForumArticle().setArticlePublish(forumArticle_1.getArticlePublish());
			articlelListDTO.getForumArticle().setArticleStatus(forumArticle_1.getArticleStatus());
			articlelListDTOList.add(articlelListDTO);
			articlelListDTO = null;
		}
		//測試用
//		System.out.println("articlelListDTOList.size():" + articlelListDTOList.size());
//		for(ArticlelListDTO articlelListDTO : articlelListDTOList) {
//			System.out.println("articlelListDTO.getForumArticle().getArticleTitle():" + articlelListDTO.getForumArticle().getArticleTitle());
//		}
		
//		System.out.println("(Integer)(session.getAttribute(\"articleNo\")):" + (Integer)(session.getAttribute("articleNo")));
//		System.out.println("forumArticleslList.size():" + forumArticleslList.size());
		if(forumArticleslList.size() !=0) {
//			writePojo2Json(response, forumArticleslList);
			writePojo2Json(response, articlelListDTOList);
		}
	}
	
	
}
