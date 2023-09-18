package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.RpArticleMsgConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.entity.Core;
import web.front_end.member.forum.entity.RpArticleMsg;

@WebServlet("/api/rpArticleMsg/updateById")
public class RpArticleMsgUpdateByIdServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Parse the JSON data into an RpArticle object
	    	RpArticleMsg rpArticleMsg = json2Pojo(request, RpArticleMsg.class);
	        response.setContentType("text/html;charset=UTF-8");

	        if (rpArticleMsg != null) {
	            Integer rpMsgNo = rpArticleMsg.getRpMsgNo();
	            Integer msgeStat = rpArticleMsg.getMsgeStat();
	            Integer auditResult = rpArticleMsg.getAuditResult();

	            System.out.println("rpMsgNo:" + rpMsgNo); // Check if you received the "檢舉文章編號"

	            Core core = new Core();
	            if (rpMsgNo == null) {
	                core.setMessage("查無檢舉文章留言ID，無法更新");
	                core.setSuccessful(false);
	            } else {
	                core.setSuccessful(SERVICE.updateByRpArticleMsgNo(rpMsgNo, msgeStat, auditResult));
	            }
	            writePojo2Json(response, core);
	        } else {
	            // Handle the case where JSON parsing failed
	            Core core = new Core();
	            core.setMessage("JSON parsing failed");
	            core.setSuccessful(false);
	            writePojo2Json(response, core);
	        }
	    }
	}
