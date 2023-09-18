package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.RpArticleConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.forum.dto.RpArticleDTO;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;
import web.front_end.member.forum.entity.RpArticle;

@WebServlet("/needLoginApi/apArticle/add")
public class RpArticleAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RpArticleDTO rpArticleData = json2Pojo(request, RpArticleDTO.class);
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		Integer memberNo = (Integer) (session.getAttribute("memberNo"));
		// System.out.print("memberNo : " + memberNo);


		RpArticle rpArticle = null;
		if (rpArticleData != null) {
			rpArticle = new RpArticle();
			rpArticle.setArticleNo(rpArticleData.getArticleNo());
			rpArticle.setReportContent(rpArticleData.getReportContent());
			rpArticle.setRpmemberNo(memberNo);
			rpArticle = SERVICE.add(memberNo, rpArticle);
			writePojo2Json(response, rpArticle);
		} else {
			rpArticle = new RpArticle();
			rpArticle.setMessage("無論壇文章留言");
			rpArticle.setSuccessful(false);
			writePojo2Json(response, rpArticle);
		}

		// 測試用的
		System.out.println("articleMsg：" + rpArticle);
		System.out.println("memberNo：" + memberNo);

	}
}
