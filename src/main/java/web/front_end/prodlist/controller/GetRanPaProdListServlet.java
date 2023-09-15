package web.front_end.prodlist.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.prodlist.util.ProdlistConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.prod.entity.PaProd;


@WebServlet("/api/front_end/ranPaProdInfo")
public class GetRanPaProdListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("fetch成功");
		
		resp.setContentType("text/html;charset=utf-8");
		PaProd paProdlist = json2Pojo(req, PaProd.class);
		
		List<PaProd> paProdlists = SERVICE.RangetProdNo(4, paProdlist.getPaProdObjNo());
		
		//使用subList取得前四筆資料
		if (paProdlists.size() >= 4) {
		    paProdlists = paProdlists.subList(0, 4);
		}
		
		if(paProdlists.size() != 0) {
			writePojo2Json((resp), paProdlists);
		}
		
		System.out.println("輸入成功");
	}

}
