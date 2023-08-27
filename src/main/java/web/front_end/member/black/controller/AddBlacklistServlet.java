package web.front_end.member.black.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.black.util.BlacklistConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.black.entity.Blacklist;

@WebServlet("/needLoginApi/front_end/addBlacklist")
public class AddBlacklistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	    response.setCharacterEncoding("UTF-8");
	    Blacklist blacklist = json2Pojo(request, Blacklist.class);

		SERVICE.addBlack(blacklist);
		//successful
		if(blacklist.isSuccessful()) { // 修改成功
			writePojo2Json(response, blacklist);
		}else { // 修改失敗
			writePojo2Json(response, blacklist);
		}
		
	}
}
