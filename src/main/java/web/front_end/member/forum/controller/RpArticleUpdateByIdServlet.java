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

import core.entity.Core;
import web.front_end.member.forum.entity.RpArticle;

@WebServlet("/api/rpArticle/updateById")
public class RpArticleUpdateByIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse the JSON data into an RpArticle object
        RpArticle rpArticle = json2Pojo(request, RpArticle.class);
        response.setContentType("text/html;charset=UTF-8");

        if (rpArticle != null) {
            Integer rpArticleNo = rpArticle.getRpArticleNo();
            Integer articleNo = rpArticle.getArticleNo();
            Integer articleStatus = rpArticle.getArticleStatus();
            Integer auditResult = rpArticle.getAuditResult();

            System.out.println("rpArticleNo:" + rpArticleNo); // Check if you received the "檢舉文章編號"

            Core core = new Core();
            if (rpArticleNo == null) {
                core.setMessage("查無檢舉文章ID，無法更新");
                core.setSuccessful(false);
            } else {
                core.setSuccessful(SERVICE.updateByRpArticleNo(articleNo, rpArticleNo, articleStatus, auditResult));
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
