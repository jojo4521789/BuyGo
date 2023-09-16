package web.front_end.seller.gpa.prod.controller;

import static core.util.Base64Util.Base64EncoderByByte;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.seller.gpa.order.util.GpaSoConstants.SERVICE;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import core.dto.ActionDTO;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.dto.RecommendedGpaProdDTO;
import web.front_end.seller.gpa.prod.entity.GpaProd;
import web.front_end.seller.gpa.prod.entity.GpaReach;
import web.front_end.seller.gpa.prod.util.GpaProdConstants;

@WebServlet("/api/front_end/ECPayUpdateGpaOrder")
public class ECPayUpdateGpaOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		fixHeaders(response);
		HttpSession session = request.getSession(); // 取得當前請求的Session
	    response.setCharacterEncoding("UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
	    String[] shippingDetailArray = request.getParameter("ShippingDetail").split(",");
		// shippingDetailArray[0]為下單購買商品的買家會員編號
		// shippingDetailArray[1]為欲購買的商品編號
		// shippingDetailArray[2]為欲購買的商品數量
		// shippingDetailArray[3]為欲購買的商品金額(單價)
		// shippingDetailArray[4]為運費
		// shippingDetailArray[5]為買家設定的收貨人
		// shippingDetailArray[6]為買家設定的收貨電話
		// shippingDetailArray[7]為買家設定的收貨地址
	    
//	    for(int i = 0 ; i < shippingDetailArray.length ; i++) {
//	    	System.out.println("shippingDetailArray["+ i +"]:" + shippingDetailArray[i]);
//	    }
	    GpaSo gpaSo = new GpaSo();
	    gpaSo.setGpaProdNo(Integer.parseInt(shippingDetailArray[1]));
	    gpaSo.setMemberNo(Integer.parseInt(shippingDetailArray[0]));
	    gpaSo.setGpaProdCount(Integer.parseInt(shippingDetailArray[2]));
	    gpaSo.setGpaProdPrice(Integer.parseInt(shippingDetailArray[3]));
	    gpaSo.setGpaProdTotal((Integer.parseInt(shippingDetailArray[3]) * Integer.parseInt(shippingDetailArray[2])) + Integer.parseInt(shippingDetailArray[4])); // 總金額為(單價*數量)+運費
	    gpaSo.setGpaSoStat(0); // 新成立的訂單應為狀態0(揪團中)
	    gpaSo.setGpaBuyName(shippingDetailArray[5]);
	    gpaSo.setGpaBuyTel(shippingDetailArray[6]);
	    gpaSo.setGpaBuyAdd(shippingDetailArray[7]);
	    gpaSo.setGpaProd(null); // 沒使用到的關聯Table設為null，避免出錯
	    gpaSo.setMember(null); // 沒使用到的關聯Table設為null，避免出錯
	    
	    boolean resultBoolean = SERVICE.addGpaSo(gpaSo); // 新增訂單於資料庫
	    
	    // 將商品的預定數量+1開始
	    GpaProdConstants gpaProdConstants = new GpaProdConstants();
	    GpaProd currentGpaProd = gpaProdConstants.SERVICE.loadByGpaProdNo(Integer.parseInt(shippingDetailArray[1]));
	    Integer currentGpaPreProd = currentGpaProd.getGpaPreProd(); // 取得當前商品目前的庫存數量
	    boolean result = gpaProdConstants.SERVICE.changeGpaPreProdByGpaProdNo(Integer.parseInt(shippingDetailArray[1]), currentGpaPreProd + 1); // 將商品的預定數量+1並保存至資料庫
	    // 將商品的預定數量+1結束
	    
	    gpaSo.setSuccessful(resultBoolean);
	    writePojo2Json(response, gpaSo);
	}
	private void fixHeaders(HttpServletResponse resp) {
		// 允許跨域請求的域名，可以是多個域名，用逗號分隔
		resp.setHeader("Access-Control-Allow-Origin", "https://payment-stage.ecpay.com.tw");
		// 允許的HTTP方法
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST");
		// 允許的自定義請求頭
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		// 預檢請求的緩存時間，單位為秒
		resp.setHeader("Access-Control-Max-Age", "3600");
	}
}
