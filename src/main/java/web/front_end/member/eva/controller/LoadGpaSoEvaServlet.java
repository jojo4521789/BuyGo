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
//		for(GpaSo gpaSo : gpaSoList) {
//			System.out.println("-----------------");
//			System.out.println("getGpaSoNo:" + gpaSo.getGpaSoNo());
//			System.out.println("getGpaProdNo:" + gpaSo.getGpaProdNo());
//			System.out.println("getGpaSoStat:" + gpaSo.getGpaSoStat());
//			System.out.println("-----------------");	
//		}
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
//		//DTO
//		List<GpaProdEvaDTO> gpaProdEvaDTOList = new LinkedList<GpaProdEvaDTO>();
//		
//		for (GpaProd gpaProd : gpsProdlist) {
//			GpaProdEvaDTO gpaProdEvaDTO = new GpaProdEvaDTO();
//			
////			// DTO開始
//			
//			gpaProdEvaDTO.setGpaProdName(gpaProd.getGpaProdName()); // 揪團商品名稱
//			gpaProdEvaDTO.setGpaEndDate(gpaProd.getGpaEndDate()); // 揪團商品截止時間
//			
//			for(int i =0 ; i < gpaProd.getGpaSo().size() ; i++) {
//				gpaProdEvaDTO.getGpaSoNo().add(gpaProd.getGpaSo().get(i).getGpaSoNo()); // 揪團賣場訂單編號
//				gpaProdEvaDTO.getGpaProdCount().add(gpaProd.getGpaSo().get(i).getGpaProdCount()); // 購買數量
//				gpaProdEvaDTO.getGpaProdTotal().add(gpaProd.getGpaSo().get(i).getGpaProdTotal()); // 總金額
//				gpaProdEvaDTO.getGpaEvaSeller().add(gpaProd.getGpaSo().get(i).getGpaEvaSeller()); // 給賣家的評價
//				gpaProdEvaDTO.getMemberAcct().add(gpaProd.getGpaSo().get(i).getMember().getMemberAcct()); // 買家名稱
//			}
//			
//			for(int i = 0 ; i < gpaProd.getGpaProdPics().size() ; i++) { // 商品圖片(轉Base64)
//				try {
//					String gpaProdPicToBase64 = Base64EncoderByByte(gpaProd.getGpaProdPics().get(i).getGpaProdPic());
//					gpaProdEvaDTO.getGpaProdPics().add(gpaProdPicToBase64);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} // 商品圖片
//			}
//			gpaProdEvaDTOList.add(gpaProdEvaDTO);
////			// DTO寫法結束
//		}
//		// DTO驗證開始
////		for(GpaProdEvaDTO gpaProdEvaDTO : gpaProdEvaDTOList) {
////			System.out.println("---------------");
////			System.out.println(gpaProdEvaDTO.getGpaProdName());
////			System.out.println(gpaProdEvaDTO.getGpaEndDate());
////			for(String pic : gpaProdEvaDTO.getGpaProdPics()) {
////				System.out.println(pic);
////			}
////			
////			for(int i = 0 ; i < gpaProdEvaDTO.getGpaSoNo().size() ; i++) {
////				System.out.println("--訂單開始--");
////				System.out.println(gpaProdEvaDTO.getGpaSoNo().get(i));
////				System.out.println(gpaProdEvaDTO.getGpaProdCount().get(i));
////				System.out.println(gpaProdEvaDTO.getGpaProdTotal().get(i));
////				System.out.println(gpaProdEvaDTO.getGpaProdTotal().get(i));
////				System.out.println(gpaProdEvaDTO.getGpaEvaSeller().get(i));
////				System.out.println(gpaProdEvaDTO.getMemberAcct().get(i));
////				System.out.println("--訂單結束--");
////			}
////			System.out.println("---------------");
////		}
//		System.out.println("gpaProdEvaDTOList:" + gpaProdEvaDTOList);
//		// DTO驗證結束
		
		
		writePojo2Json(response, gpaSoEvaDTOList);
	}
}
