package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static web.front_end.member.forum.util.ForumArticleConstants.SERVICE;
import core.entity.Core;
import web.front_end.member.forum.entity.ForumArticle;

@WebServlet("/api/forumArticle/updateById")
public class ForumArticleUpdateByIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ForumArticle forumArticle = json2Pojo(request, ForumArticle.class);
        response.setContentType("text/html;charset=UTF-8");
        
        if (forumArticle != null) {
            Integer articleNo = forumArticle.getArticleNo();
            Integer articleStatus = forumArticle.getArticleStatus();

            System.out.println("articleNo:" + articleNo);
            System.out.println("articleStatus:" + articleStatus);

            Core core = new Core();
            if (articleNo == null) { // 檢查 articleNo 是否為 null，以確保它是有效的對象
                core.setMessage("查無論壇文章ID，無法更新");
                core.setSuccessful(false);
            } else {
                core.setSuccessful(SERVICE.updateByArticleNo(articleNo, articleStatus));
            }
            writePojo2Json(response, core);
        } else {
            // 處理JSON解析失敗的情況
            Core core = new Core();
            core.setMessage("JSON解析失敗");
            core.setSuccessful(false);
            writePojo2Json(response, core);
        }
    }
}
