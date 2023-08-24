package web.front_end.seller.gpa.order.controller;

import static core.util.Base64Util.Base64EncoderByByte;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.seller.gpa.order.util.GpaSoConstants.SERVICE;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.seller.gpa.order.dto.GpaOrderDTO;
import web.front_end.seller.gpa.order.dto.GpaOrderDTO2;
import web.front_end.seller.gpa.order.entity.GpaSo;

@WebServlet("/needLoginApi/front_end/loadGpaOrder")
public class LoadGpaOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		Integer selectPage = Integer.parseInt(request.getParameter("page"));
		List<GpaSo> gpaSolist = SERVICE.loadSoByMemberNoAndSoStat((Integer)(session.getAttribute("memberNo")), selectPage);
		
		//DTO
		List<GpaOrderDTO> gpaOrderDTOList = new LinkedList<GpaOrderDTO>();
//		測試
		for (GpaSo gpaSo : gpaSolist) {
			GpaOrderDTO gpaOrderDTO = new GpaOrderDTO();
			//DTO測試開始
//			gpaOrderDTO.setGpaProd(gpaSo.getGpaProd().getGpaSo().get(0).getGpaProd().getGpaSo().get(0).getGpaProd());
//			gpaOrderDTO.setGpaSo(gpaSo);
//			gpaOrderDTO.setMember(gpaSo.getMember());
//			gpaOrderDTO2.setGpaSoNo(gpaSo.getGpaSoNo());
//			gpaOrderDTO2.setGpaProdCount(gpaSo.getGpaProdCount());
			//DTO測試結束
			
//			// DTO寫法開始
			gpaOrderDTO.setGpaSoNo(gpaSo.getGpaSoNo()); // 揪團賣場訂單編號
			gpaOrderDTO.setGpaProdCount(gpaSo.getGpaProdCount()); // 購買數量
			gpaOrderDTO.setGpaProdTotal(gpaSo.getGpaProdTotal()); // 總金額
			gpaOrderDTO.setGpaEvaSeller(gpaSo.getGpaEvaSeller()); // 給賣家的評價
			gpaOrderDTO.setGpaProdName(gpaSo.getGpaProd().getGpaProdName()); // 商品名稱
			gpaOrderDTO.setGpaEndDate(gpaSo.getGpaProd().getGpaEndDate()); // 截止日期
			gpaOrderDTO.setMemberAcct(gpaSo.getGpaProd().getMember().getMemberAcct()); // 賣家名稱
			List<String> gpaProdPicToBase64List = new LinkedList<String>(); // 創建List供for迴圈將每個商品圖片設置進去，最後使用此gpaProdPicToBase64List set List進DTO中
			for(int i = 0 ; i < gpaSo.getGpaProd().getGpaProdPics().size() ; i++) { // 商品圖片(轉Base64)
				try {
					gpaProdPicToBase64List.add(Base64EncoderByByte(gpaSo.getGpaProd().getGpaProdPics().get(i).getGpaProdPic()));
				} catch (Exception e) {
					e.printStackTrace();
				} // 商品圖片
			}
			gpaOrderDTO.setGpaProdPics(gpaProdPicToBase64List); // 商品圖片(轉Base64)
			gpaOrderDTOList.add(gpaOrderDTO);
//			// DTO寫法結束
		}
		// DTO驗證
//		for(GpaOrderDTO gpaOrderDTO : gpaOrderDTOList) {
//			System.out.println("---------------");
//			System.out.println(gpaOrderDTO.getGpaProdName());
//			System.out.println(gpaOrderDTO.getMemberName());
//			System.out.println(gpaOrderDTO.getGpaEndDate());
//			System.out.println(gpaOrderDTO.getGpaEvaSeller());
//			System.out.println(gpaOrderDTO.getGpaProdCount());
//			System.out.println(gpaOrderDTO.getGpaProdTotal());
//			System.out.println(gpaOrderDTO.getGpaSoNo());
//			for(String pic : gpaOrderDTO.getGpaProdPics()) {
//				System.out.println(pic);
//			}
//			System.out.println("---------------");
//		}
		writePojo2Json(response, gpaOrderDTOList);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
	}
}
