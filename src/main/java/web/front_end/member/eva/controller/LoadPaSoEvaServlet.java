package web.front_end.member.eva.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.eva.util.EvaPaSoConstants.SERVICE;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.eva.dto.PaSoDetailsEvaDTO;
import web.front_end.member.eva.dto.PaSoEvaDTO;
import web.front_end.member.eva.entity.PaProdPic;
import web.front_end.member.eva.entity.PaSo;
import web.front_end.member.eva.entity.PaSoDetails;

@WebServlet("/needLoginApi/front_end/loadPaSoEva")
public class LoadPaSoEvaServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		List<PaSo> paProdlist = SERVICE.loadBySellerMemberNoAndPaSoStatus((Integer)(session.getAttribute("memberNo")), 5); //只搜尋訂單狀態5(已完成的訂單)
		
		// 使用DTO傳送，避免傳送未被使用的參數至前端，設置參數至DTO
		List<PaSoEvaDTO> paSoEvaDTOList = new LinkedList<PaSoEvaDTO>();
		for(PaSo paSo : paProdlist) {
			PaSoEvaDTO paSoEvaDTO = new PaSoEvaDTO();
			paSoEvaDTO.setPaSoNo(paSo.getPaSoNo());
			paSoEvaDTO.setPaSoStatus(paSo.getPaSoStatus());
			paSoEvaDTO.setPaSoTotal(paSo.getPaSoTotal());
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
		
		
		
		
		//測試
//		for(PaSo paSo : paProdlist) {
//			System.out.println("----------------");
//			System.out.println("paSoNo:" + paSo.getPaSoNo());
//			System.out.println("(buyer)memberNo:" + paSo.getMemberNo());
//			for(PaSoDetails paSoDetails : paSo.getPaSoDetails()) {
//				System.out.println("PaProdName:" + paSoDetails.getPaProdName());
//				System.out.println("PaProdQty:" + paSoDetails.getPaProdQty());
//				System.out.println("(seller)sellerMemberNo:" + paSoDetails.getPaProd().getMemberNo());
//				
//				for(PaProdPic paProdPic : paSoDetails.getPaProd().getPaProdPic()) {
//					System.out.println("paProdPic:" + paProdPic.getPaProdPicToBase64());
//				}
//			}
//			System.out.println("----------------");
//		}
		
		writePojo2Json(response, paSoEvaDTOList);
	}
}
