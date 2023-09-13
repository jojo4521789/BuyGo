package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ArticleMsgConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.entity.Core;
import web.front_end.member.forum.entity.ArticleMsg;

@WebServlet("/api/articleMsg/remove")
public class ArticleMsgRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final Integer messageNumber = json2Pojo(request, ArticleMsg.class).getMessageNumber();
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("messageNumber:" + messageNumber); //檢查測試是否收到前端"文章編號"
		
		final Core core = new Core();
		if (messageNumber == null) {
			core.setMessage("查無文章留言ID，無法刪除");
			core.setSuccessful(false);

		} else {
			core.setSuccessful(SERVICE.remove(messageNumber));
		}
		writePojo2Json(response, core);
	}

}
