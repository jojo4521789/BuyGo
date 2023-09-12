package web.front_end.member.eva.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.eva.util.EvaConstants.SERVICE;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import core.dto.ActionDTO;
import web.front_end.member.eva.dto.PaSoDetailsEvaDTO;
import web.front_end.member.eva.dto.PaSoEvaDTO;
import web.front_end.member.eva.entity.PaProdPic;
import web.front_end.member.eva.entity.PaSo;
import web.front_end.member.eva.entity.PaSoDetails;

@WebServlet("/needLoginApi/front_end/eva")
public class EvaServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
	    response.setCharacterEncoding("UTF-8");
	    
	    Gson gson = new Gson();
	    Object requestObj = json2Pojo(request, Object.class);
	    String requestToJson = gson.toJson(requestObj);
	    ActionDTO actionDTO = gson.fromJson(requestToJson, ActionDTO.class);
	    
	    String action = actionDTO.getAction();
	    // -----action共有以下模式-----
	    // loadPaSoEva 讀取一般代購已完成的訂單
	    // 
	    // 
	    // ------------------------
	    
	    if("loadPaSoEva".equals(action)) {
			List<PaSo> paProdlist = SERVICE.loadPaSoBySellerMemberNoAndPaSoStatus((Integer)(session.getAttribute("memberNo")), 5); //只搜尋訂單狀態5(已完成的訂單)
			System.out.println("paProdlist.size():" + paProdlist.size());
			// 使用DTO傳送，避免傳送未被使用的參數至前端，設置參數至DTO
			List<PaSoEvaDTO> paSoEvaDTOList = new LinkedList<PaSoEvaDTO>();
			for(PaSo paSo : paProdlist) {
				PaSoEvaDTO paSoEvaDTO = new PaSoEvaDTO();
				paSoEvaDTO.setPaSoNo(paSo.getPaSoNo());
				paSoEvaDTO.setPaSoStatus(paSo.getPaSoStatus());
				paSoEvaDTO.setPaTotalAmount(paSo.getPaTotalAmount());
				paSoEvaDTO.setPaEvaSeller(paSo.getPaEvaSeller());
				paSoEvaDTO.setMemberAcct(paSo.getMember().getMemberAcct());
				for(PaSoDetails paSoDetails : paSo.getPaSoDetails()) {
					PaSoDetailsEvaDTO paSoDetatilEvaDTO = new PaSoDetailsEvaDTO();
					paSoDetatilEvaDTO.setPaProdName(paSoDetails.getPaProdName());
					paSoDetatilEvaDTO.setPaProdQty(paSoDetails.getPaProdQty());
					paSoDetatilEvaDTO.setPaProdPrice(paSoDetails.getPaProdPrice());
					for(PaProdPic paProdPic: paSoDetails.getPaProd().getPaProdPic()) {
						paSoDetatilEvaDTO.getPaProdPicToBase64().add(paProdPic.getPaProdPicToBase64());
					}
					paSoEvaDTO.getPaSoDetailsEvaDTO().add(paSoDetatilEvaDTO);
					paSoDetatilEvaDTO = null; // 清空該物件
				}
				paSoEvaDTOList.add(paSoEvaDTO);
				paSoEvaDTO = null; // 清空該物件
			}
			
			writePojo2Json(response, paSoEvaDTOList);
	    }
	}
}
