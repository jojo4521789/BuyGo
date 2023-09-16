package web.front_end.member.pa.prod.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.prod.util.ProdConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.prod.entity.PaProd;

@WebServlet("/api/front_end/member/pa/prod/paProdLoad")
public class PaProdLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		PaProd prod = json2Pojo(request, PaProd.class);

		List<PaProd> loadProds = SERVICE.findAll();
		if(loadProds.size() != 0) {
			writePojo2Json(response, loadProds);
		}
	}
}
