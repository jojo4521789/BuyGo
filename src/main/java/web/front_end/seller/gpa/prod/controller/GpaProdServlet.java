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
public class GpaProdServlet extends HttpServlet {
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
		// loadGpaPreProdByGpaProdNo 輸入指定的GpaProdNo回傳指定商品編號的庫存剩餘數量
		// decreaseGpaPreProdByGpaProdNo 輸入指定的GpaProdNo，將該商品的庫存數量減1後保存置資料庫
		// ------------------------
		if ("randomLoadByGpaCatsNoAndFilterGpaProdNo".equals(action)) {
			GpaProd gpaProd = gson.fromJson(requestToJson, GpaProd.class);
			List<GpaProd> gpaProdList = SERVICE.randomLoadByGpaCatsNoAndFilterGpaProdNo(gpaProd.getGpaCatsNo(),
					gpaProd.getGpaProdNo());
			List<RecommendedGpaProdDTO> recommendedGpaProdDTOList = new LinkedList<RecommendedGpaProdDTO>();
			for (GpaProd searchGpaProd : gpaProdList) {
				RecommendedGpaProdDTO recommendedGpaProdDTO = new RecommendedGpaProdDTO();
				recommendedGpaProdDTO.setGpaProdNo(searchGpaProd.getGpaProdNo());
				recommendedGpaProdDTO.setGpaProdName(searchGpaProd.getGpaProdName());
				recommendedGpaProdDTO.setGpaFirstPrice(searchGpaProd.getGpaFirstPrice());
				recommendedGpaProdDTO.setGpaPreProd(searchGpaProd.getGpaPreProd());
				recommendedGpaProdDTO.setGpaEndDate(searchGpaProd.getGpaEndDate());
				String gpaProdPicToBase64 = "";
				try {
					if (searchGpaProd.getGpaProdPics().size() != 0) {
						gpaProdPicToBase64 = Base64EncoderByByte(searchGpaProd.getGpaProdPics().get(0).getGpaProdPic());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				recommendedGpaProdDTO.setGpaProdPicToBase64(gpaProdPicToBase64);
				for (GpaReach gpaReach : searchGpaProd.getGpaReach()) {
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

		if ("loadGpaPreProdByGpaProdNo".equals(action)) {
			GpaProd gpaProd = gson.fromJson(requestToJson, GpaProd.class);
			GpaProd currentGpaProd = SERVICE.loadByGpaProdNo(gpaProd.getGpaProdNo());
			Integer currentGpaPreProd = currentGpaProd.getGpaPreProd();
			GpaProd newGpaProd = new GpaProd();
			newGpaProd.setGpaPreProd(currentGpaPreProd);
			newGpaProd.setGpaProdPics(null); // 將未使用到的table設為null
			newGpaProd.setGpaReach(null); // 將未使用到的table設為null
			writePojo2Json(response, newGpaProd);
		}

		if ("decreaseGpaPreProdByGpaProdNo".equals(action)) {
			GpaProd gpaProd = gson.fromJson(requestToJson, GpaProd.class);
			GpaProd currentGpaProd = SERVICE.loadByGpaProdNo(gpaProd.getGpaProdNo());
			Integer currentGpaPreProd = currentGpaProd.getGpaPreProd(); // 取得當前商品目前的庫存數量
			boolean result = SERVICE.changeGpaPreProdByGpaProdNo(gpaProd.getGpaProdNo(), currentGpaPreProd - 1); // 將該商品庫存數量-1並保存至資料庫
			GpaProd newGpaProd = new GpaProd();
			newGpaProd.setSuccessful(result); // 將成功與否的布林狀態回傳前端
			newGpaProd.setGpaProdPics(null); // 將未使用到的table設為null
			newGpaProd.setGpaReach(null); // 將未使用到的table設為null
			writePojo2Json(response, newGpaProd);
		}

		if ("getAllGpaProds".equals(action)) {
			List<GpaProd> gpaProds = SERVICE.findAll();
			List<GpaProd> newGpaProds = new LinkedList<GpaProd>();
			for (GpaProd gpaProd : gpaProds) {
				GpaProd newGpaProd = new GpaProd();
				newGpaProd.setGpaProdNo(gpaProd.getGpaProdNo());
				newGpaProd.setGpaProdName(gpaProd.getGpaProdName());
				newGpaProd.setGpaFirstPrice(gpaProd.getGpaFirstPrice());
				newGpaProd.setGpaCatsNo(gpaProd.getGpaCatsNo());
				newGpaProd.setGpaProdContent(gpaProd.getGpaProdContent());
				String gpaProdPicToBase64 = "";
				try {
					if (gpaProd.getGpaProdPics().size() != 0) {
						gpaProdPicToBase64 = Base64EncoderByByte(gpaProd.getGpaProdPics().get(0).getGpaProdPic());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				newGpaProd.setMessage(gpaProdPicToBase64); //暫放圖片字串在message
				newGpaProds.add(newGpaProd);
				newGpaProd = null;
			}
			writePojo2Json(response, newGpaProds);
		}
	}
}
