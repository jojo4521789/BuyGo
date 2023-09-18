package web.front_end.member.eva.controller;

import static core.util.Base64Util.Base64EncoderByByte;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.seller.gpa.order.util.GpaSoConstants.SERVICE;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.eva.dto.GpaProdEvaDTO;
import web.front_end.member.eva.dto.GpaSoEvaDTO;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.entity.GpaProd;
import web.front_end.seller.gpa.prod.entity.GpaProdPics;

@WebServlet("/needLoginApi/front_end/loadGpaSoEva")
public class LoadGpaSoEvaServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		List<GpaSo> gpaSoList = SERVICE.loadSoBySellerMemberNoAndSoStat((Integer)(session.getAttribute("memberNo")), 4); //只搜尋訂單狀態4(已完成)
		List<GpaSoEvaDTO> gpaSoEvaDTOList = new LinkedList<GpaSoEvaDTO>();
		for(GpaSo gpaSo : gpaSoList) {
			GpaSoEvaDTO gpaSoEvaDTO = new GpaSoEvaDTO();
			gpaSoEvaDTO.getGpaSo().setGpaSoNo(gpaSo.getGpaSoNo()); // 揪團賣場訂單編號
			gpaSoEvaDTO.getGpaSo().setGpaProdCount(gpaSo.getGpaProdCount()); // 購買數量
			gpaSoEvaDTO.getGpaSo().setGpaProdTotal(gpaSo.getGpaProdTotal()); //總金額
			gpaSoEvaDTO.getGpaSo().setGpaEvaSeller(gpaSo.getGpaEvaSeller()); // 給賣家的評價
			
			gpaSoEvaDTO.getGpaSo().getMember().setMemberAcct(gpaSo.getMember().getMemberAcct()); // 買家帳號
			
			gpaSoEvaDTO.getGpaSo().getGpaProd().setGpaProdNo(gpaSo.getGpaProd().getGpaProdNo()); // 揪團商品編號
			gpaSoEvaDTO.getGpaSo().getGpaProd().setGpaProdName(gpaSo.getGpaProd().getGpaProdName()); // 揪團商品名稱
			gpaSoEvaDTO.getGpaSo().getGpaProd().setGpaEndDate(gpaSo.getGpaProd().getGpaEndDate()); // 揪團商品截止時間
			
			try {
				GpaProdPics gpaProdPics = new GpaProdPics();
				String gpaProdPicToBase64 = Base64EncoderByByte(gpaSo.getGpaProd().getGpaProdPics().get(0).getGpaProdPic()); // 僅需取資料庫內第一張照片即可，將第一張照片轉換Base64
				gpaProdPics.setGpaProdPicToBase64(gpaProdPicToBase64);
				gpaSoEvaDTO.getGpaSo().getGpaProd().getGpaProdPics().add(gpaProdPics);
				gpaProdPics = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			gpaSoEvaDTOList.add(gpaSoEvaDTO);
			gpaSoEvaDTO = null;
		}
		
		writePojo2Json(response, gpaSoEvaDTOList);
	}
}
