package web.back_end.lpa.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.back_end.lpa.order.DTO.EditProdStatusDTO;
import web.back_end.lpa.order.entity.LpaSoDetails;
import web.back_end.lpa.order.service.LpaSoService;
import web.back_end.lpa.order.service.impl.LpaSoServiceImpl;

@WebServlet("/needLoginApi/updateDetailStatus")
public class UpdateBoughtOrNotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LpaSoService service = new LpaSoServiceImpl();
	CommonUtil commonUtil = new CommonUtil();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		EditProdStatusDTO editProdStatusDTO = commonUtil.json2Pojo(req, EditProdStatusDTO.class); // 不能直接轉成List.class ?!
		 
		System.out.println(editProdStatusDTO);
				
		List<LpaSoDetails> newlpaDetailsList = new ArrayList<LpaSoDetails>();
		for (LpaSoDetails lpaSoDetails : editProdStatusDTO.getLpaSoDetailsList()) {
			LpaSoDetails newlpaSoDetails = service.updateOrderDetailStatus(lpaSoDetails);
			newlpaDetailsList.add(newlpaSoDetails);
		}
		System.out.println(newlpaDetailsList);
		
		commonUtil.writePojo2Json(resp, newlpaDetailsList);

	}
}
