package web.front_end.prodlist.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static core.util.Base64Util.Base64EncoderByByte;
import static core.util.CommonUtil.writePojo2Json;

import web.front_end.prodlist.dto.GpaProdlistDTO;
import web.front_end.seller.gpa.prod.entity.GpaProd;
import web.front_end.seller.gpa.prod.entity.GpaProdPics;
import web.front_end.seller.gpa.prod.entity.GpaReach;

import static web.front_end.seller.gpa.prod.util.GpaProdConstants.SERVICE;

import java.util.Iterator;

@WebServlet("/api/front_end/gpaProdlist")
public class GpaProdlistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
	    response.setCharacterEncoding("UTF-8");
	    
	    String action = request.getParameter("action");
	    // -----action共有以下模式-----
	    // loadGpaProdlist 讀取揪團商品詳情
	    // 
	    // 
	    // ------------------------
	    if ("loadGpaProdlist".equals(action)) {
	    	Integer gpaProdNo = Integer.parseInt(request.getParameter("gpaProdNo"));
	    	
	    	GpaProd gpaProd = SERVICE.loadByGpaProdNo(gpaProdNo);
	    	GpaProdlistDTO gpaProdlistDTO = new GpaProdlistDTO();
	    	gpaProdlistDTO.setGpaProdNo(gpaProd.getGpaProdNo());
	    	gpaProdlistDTO.setMemberNo(gpaProd.getMemberNo());
	    	gpaProdlistDTO.setMemberAcct(gpaProd.getMember().getMemberAcct());
	    	gpaProdlistDTO.setGpaCatsNo(gpaProd.getGpaCatsNo());
	    	gpaProdlistDTO.setGpaProdName(gpaProd.getGpaProdName());
	    	gpaProdlistDTO.setGpaFirstPrice(gpaProd.getGpaFirstPrice());
	    	gpaProdlistDTO.setGpaPreProd(gpaProd.getGpaPreProd());
	    	gpaProdlistDTO.setGpaProdContent(gpaProd.getGpaProdContent());
	    	gpaProdlistDTO.setGpaMiniCount(gpaProd.getGpaMiniCount());
	    	gpaProdlistDTO.setGpaMaxCount(gpaProd.getGpaMaxCount());
	    	gpaProdlistDTO.setGpaEndDate(gpaProd.getGpaEndDate());
	    	
	    	for(int i = 0 ; i < gpaProd.getGpaProdPics().size() ; i++) {
	    		try {
	    			String gpaProdPicToBase64 = Base64EncoderByByte(gpaProd.getGpaProdPics().get(i).getGpaProdPic()); // 將取得的byte[]照片轉換為Base64字串
	    			GpaProdPics newGpaProdPics = new GpaProdPics(); // 新建立newGpaProdPics物件
	    			newGpaProdPics.setGpaProdPicToBase64(gpaProdPicToBase64); // newGpaProdPics保存Base64的照片字串
					gpaProdlistDTO.getGpaProdPics().add(newGpaProdPics);
					newGpaProdPics = null; // 刪除物件
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    	for(int i = 0 ; i < gpaProd.getGpaReach().size() ; i++) {
	    		GpaReach newGpaReach = new GpaReach();
	    		newGpaReach.setGpaLevelCount(gpaProd.getGpaReach().get(i).getGpaLevelCount());
	    		newGpaReach.setGpaLevelPrice(gpaProd.getGpaReach().get(i).getGpaLevelPrice());
	    		gpaProdlistDTO.getGpaReach().add(newGpaReach);
	    		newGpaReach = null; // 刪除物件
	    	}
	    	
	    	writePojo2Json(response, gpaProdlistDTO);
	    }
	}
}
