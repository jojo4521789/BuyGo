package web.front_end.member.forum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.entity.Core;
import web.front_end.member.forum.entity.ForumArticle;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ForumArticleConstants.SERVICE;

@WebServlet("/api/forumArticle/remove")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Integer articleNo = json2Pojo(request, ForumArticle.class).getArticleNo();
		response.setContentType("text/html;charset=UTF-8");
				
		System.out.println("articleNo:" + articleNo); //Alan
		final Core core = new Core();
		if (articleNo == null) {
			core.setMessage("查無論壇文章ID，無法刪除");
			core.setSuccessful(false);
			
		}else {
			core.setSuccessful(SERVICE.remove(articleNo));
		}
		writePojo2Json(response, core);
	}

}
