package web.front_end.seller.gpa.prod.controller;

import static core.util.CommonUtil.json2Pojo;
import static web.front_end.seller.gpa.prod.util.GpaProdConstants.SERVICE;
import static core.util.CommonUtil.writePojo2Json;

import java.util.LinkedList;
import java.util.List;

import static core.util.Base64Util.Base64EncoderByByte;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import core.dto.ActionDTO;
import web.front_end.seller.gpa.prod.dto.RecommendedGpaProdDTO;
import web.front_end.seller.gpa.prod.entity.GpaProd;
import web.front_end.seller.gpa.prod.entity.GpaProdPics;
import web.front_end.seller.gpa.prod.entity.GpaReach;

@WebServlet("/api/front_end/gpaProd")
public class GpaProdServlet extends HttpServlet{
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
	    // randomLoadByGpaCatsNoAndFilterGpaProdNo 回傳指定gpaCatsNo的揪團商品並排除指定的單筆GpaProdNo
	    // 
	    // ------------------------
	    if ("randomLoadByGpaCatsNoAndFilterGpaProdNo".equals(action)) {
	    	GpaProd gpaProd = gson.fromJson(requestToJson, GpaProd.class);
	    	List<GpaProd> gpaProdList = SERVICE.randomLoadByGpaCatsNoAndFilterGpaProdNo(gpaProd.getGpaCatsNo(), gpaProd.getGpaProdNo());
	    	List<RecommendedGpaProdDTO> recommendedGpaProdDTOList = new LinkedList<RecommendedGpaProdDTO>();
	    	for(GpaProd searchGpaProd : gpaProdList) {
	    		RecommendedGpaProdDTO recommendedGpaProdDTO = new RecommendedGpaProdDTO();
	    		recommendedGpaProdDTO.setGpaProdNo(searchGpaProd.getGpaProdNo());
	    		recommendedGpaProdDTO.setGpaProdName(searchGpaProd.getGpaProdName());
	    		recommendedGpaProdDTO.setGpaFirstPrice(searchGpaProd.getGpaFirstPrice());
	    		recommendedGpaProdDTO.setGpaPreProd(searchGpaProd.getGpaPreProd());
	    		recommendedGpaProdDTO.setGpaEndDate(searchGpaProd.getGpaEndDate());
	    		String gpaProdPicToBase64 = "";
				try {
					if(searchGpaProd.getGpaProdPics().size() != 0) {
						gpaProdPicToBase64 = Base64EncoderByByte(searchGpaProd.getGpaProdPics().get(0).getGpaProdPic());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		recommendedGpaProdDTO.setGpaProdPicToBase64(gpaProdPicToBase64);
	    		for(GpaReach gpaReach : searchGpaProd.getGpaReach()) {
	    			GpaReach newGpaReach = new GpaReach();
	    			newGpaReach.setGpaLevelCount(gpaReach.getGpaLevelCount());
	    			newGpaReach.setGpaLevelPrice(gpaReach.getGpaLevelPrice());
	    			recommendedGpaProdDTO.getGpaReach().add(newGpaReach);
	    			newGpaReach = null;
	    		}
	    		recommendedGpaProdDTOList.add(recommendedGpaProdDTO);
	    		recommendedGpaProdDTO = null;
	    	}
			writePojo2Json(response, recommendedGpaProdDTOList);
	    }
	}
}
