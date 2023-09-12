package web.front_end.seller.gpa.order.controller;

import static core.util.Base64Util.Base64EncoderByByte;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.seller.gpa.order.util.GpaSoConstants.SERVICE;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.seller.gpa.order.dto.GpaOrderDTO;
import web.front_end.seller.gpa.order.dto.GpaSoSearchPageDTO;
import web.front_end.seller.gpa.order.entity.GpaSo;

@WebServlet("/needLoginApi/front_end/loadGpaOrderBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStr")
public class LoadGpaOrderBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStrServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		GpaSoSearchPageDTO gpaSoSearchPageDTO= json2Pojo(request, GpaSoSearchPageDTO.class);
		
		List<GpaSo> gpaSoList = SERVICE.loadBySellerMemberNoAndSoStatAndLimitAndOffsetAndSearchStr((Integer)(session.getAttribute("memberNo")), gpaSoSearchPageDTO.getGpaSoStat(), gpaSoSearchPageDTO.getLimit(), gpaSoSearchPageDTO.getOffset(), gpaSoSearchPageDTO.getSearchStr());
		
		List<GpaOrderDTO> gpaOrderDTOList = new LinkedList<GpaOrderDTO>();
		for (GpaSo gpaSo : gpaSoList) {
			GpaOrderDTO gpaOrderDTO = new GpaOrderDTO();
//			// DTO寫法開始
			gpaOrderDTO.setGpaSoNo(gpaSo.getGpaSoNo()); // 揪團賣場訂單編號
			gpaOrderDTO.setGpaProdCount(gpaSo.getGpaProdCount()); // 購買數量
			gpaOrderDTO.setGpaProdTotal(gpaSo.getGpaProdTotal()); // 總金額
//			gpaOrderDTO.setGpaEvaSeller(gpaSo.getGpaEvaSeller()); // 給賣家的評價
			gpaOrderDTO.setGpaEvaMember(gpaSo.getGpaEvaMember()); // 給買家的評價
			gpaOrderDTO.setGpaBuyName(gpaSo.getGpaBuyName()); // 收貨人
			gpaOrderDTO.setGpaBuyTel(gpaSo.getGpaBuyTel()); // 收貨人電話
			gpaOrderDTO.setGpaBuyAdd(gpaSo.getGpaBuyAdd()); // 收貨地址
			gpaOrderDTO.setGpaProdNo(gpaSo.getGpaProd().getGpaProdNo()); // 揪團商品編號
			gpaOrderDTO.setGpaProdName(gpaSo.getGpaProd().getGpaProdName()); // 商品名稱
			gpaOrderDTO.setGpaEndDate(gpaSo.getGpaProd().getGpaEndDate()); // 截止日期
			gpaOrderDTO.setMemberAcct(gpaSo.getMember().getMemberAcct()); // 買家名稱
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
		
		writePojo2Json(response, gpaOrderDTOList);
	}
}
