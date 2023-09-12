package web.back_end.opa.prod.controller;

import static core.util.CommonUtil.json2Pojo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prod;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.ProdConstants.SERVICE;

@WebServlet("/api/opa/prod")
public class ProdServlet extends HttpServlet{
	private static final long serialVersionUID = 8766189914253504301L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		Prod prod = json2Pojo(req, Prod.class);
		String action = prod.getAction();
		if("getProdTotalQty".equals(action)) {
			Integer prodTotalQty = SERVICE.getProdTotalQty();
			writePojo2Json(resp, prodTotalQty);
		}
		if ("findAllProdWithLimit".equals(action)) {
			Integer limit = prod.getLimit();
			Integer offset = prod.getOffset();
			List<Prod> prods = SERVICE.findAllProdWithLimit(limit, offset);
			writePojo2Json(resp, prods);
		}
		if("getProdTotalQtySelectByOpaProdName".equals(action)) {
			String opaProdName = prod.getOpaProdName();
			Integer prodTotalQty = SERVICE.getProdTotalQtySelectByOpaProdName(opaProdName);
			writePojo2Json(resp, prodTotalQty);
		}
		if ("findByOpaProdNameWithLimit".equals(action)) {
			String opaProdName = prod.getOpaProdName();
			Integer limit = prod.getLimit();
			Integer offset = prod.getOffset();
			List<Prod> prods = SERVICE.findByOpaProdNameWithLimit(opaProdName, limit, offset);
			writePojo2Json(resp, prods);
		}
		if("getRandomProdsByPrcatsWithLimit".equals(action)) {
			Integer opaProdNo = prod.getOpaProdNo();
			Integer opaPrcatsNo = prod.getOpaPrcatsNo();
			Integer limit = prod.getLimit();
			System.out.println(prod);
			List<Prod> prods = SERVICE.getRandomProdsByPrcatsWithLimit(opaProdNo, opaPrcatsNo, limit);
			System.out.println(prods);
			writePojo2Json(resp, prods);
		}
	}
}
