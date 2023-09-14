package web.front_end.member.forum.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.forum.dto.ArticleCollListDto;
import web.front_end.member.forum.entity.ArticleCollList_In;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ArticleCollListConstants.SERVICE;

@WebServlet("/api/articleCollLis/loadByMemberNo")
public class ArticleCollListByMemberNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		Integer memberNo = (Integer) (session.getAttribute("memberNo"));

		// 測試用"登入功能加進來"(記得刪)
		if (memberNo == null) {
			memberNo = 3;
		}
		
		System.out.println("memberNo:" + memberNo);
		List<ArticleCollListDto> articleCollList_Ins = SERVICE.loadAllArticleInfo(memberNo);

		if (articleCollList_Ins.size() != 0) {
			System.out.println("取得成功");
			System.out.println("memberNo" + memberNo);
			writePojo2Json(response, articleCollList_Ins);
		} else {
			System.out.println("取得失敗");
			writePojo2Json(response, articleCollList_Ins);
		}
	}

}
