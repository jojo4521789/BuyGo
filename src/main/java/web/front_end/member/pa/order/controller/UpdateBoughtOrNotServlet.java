package web.front_end.member.pa.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.front_end.member.pa.order.DTO.EditProdStatusDTO;
import web.front_end.member.pa.order.entity.PaSoDetails;
import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.order.service.impl.PaSoServiceImpl;

@WebServlet("/needLoginApi/updateDetailStatus")
public class UpdateBoughtOrNotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaSoService service = new PaSoServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		EditProdStatusDTO editProdStatusDTO = CommonUtil.json2Pojo(req, EditProdStatusDTO.class); // 不能直接轉成List.class ?!
		 
		System.out.println(editProdStatusDTO);
				
		List<PaSoDetails> newlpaDetailsList = new ArrayList<PaSoDetails>();
		for (PaSoDetails lpaSoDetails : editProdStatusDTO.getPaSoDetailsList()) {
			PaSoDetails newlpaSoDetails = service.updateOrderDetailStatus(lpaSoDetails);
			newlpaDetailsList.add(newlpaSoDetails);
		}
		System.out.println(newlpaDetailsList);
		
		CommonUtil.writePojo2Json(resp, newlpaDetailsList);

	}
}
