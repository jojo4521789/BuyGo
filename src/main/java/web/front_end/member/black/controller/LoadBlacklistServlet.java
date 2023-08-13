package web.front_end.member.black.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.black.util.BlacklistConstants.SERVICE;

import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.black.entity.Blacklist;

@WebServlet("/front_end/member/black/loadBlacklist")
public class LoadBlacklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Blacklist blacklist = json2Pojo(request, Blacklist.class);

		List<Blacklist> blacklists = SERVICE.loadBlacklistByMemberNo(blacklist.getMemberNo());
		if(blacklists.size() != 0) {
			writePojo2Json(response, blacklists);
		}
	}
}
