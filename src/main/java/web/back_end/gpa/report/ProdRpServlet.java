package web.back_end.gpa.report;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import web.front_end.member.gpa.order.entity.GpaProdRp;
import web.front_end.member.gpa.order.entity.SelectProdRp;
import web.front_end.seller.gpa.prod.entity.GpaProd;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static core.util.CommonUtil.jsonStringWirter;
import static web.front_end.member.gpa.order.util.GpaProdRpConstants.SERVICE;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@WebServlet("/api/back_end/gpa/report/ProdRpServlet")
public class ProdRpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html:charset=utf-8");
		
		GpaProdRp gpaProdRp = json2Pojo(req, GpaProdRp.class);
		String action = gpaProdRp.getAction();
		if("show".equals(action)) {
			List<SelectProdRp> prodRps = SERVICE.selectByProdRps();
			String resultString = JSONObject.toJSONString(prodRps);
			jsonStringWirter(resp, resultString);
//			for(SelectProdRp pr :prodRps) {
//			gson2Json(resp,prodRps);
//				writePojo2Json(resp, pr);
//			}
		}else if("selectSell".equals(action)) {
			Integer sell = gpaProdRp.getProdSell();
			List<SelectProdRp> gpaProdRps = SERVICE.selectByProdRps();
			Map<Integer, List<SelectProdRp>>groupBySellNoMap = gpaProdRps.stream().collect(Collectors.groupingBy(SelectProdRp::getProdSell));
			List<SelectProdRp> groupList = (sell == 1) ? groupBySellNoMap.get(1) : groupBySellNoMap.get(2);
			if(sell == 1) {
				groupList = groupBySellNoMap.get(1);
				
			}else if (sell==2) {
				groupList = groupBySellNoMap.get(2);
				
			} else {
				String re  =JSONObject.toJSONString(gpaProdRps);
				jsonStringWirter(resp, re);
			}
			String resultString = JSONObject.toJSONString(groupList);
			jsonStringWirter(resp, resultString);
			
		}
	}
	
}
