package core.controller.ecpay;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static core.util.Constants.SERVER_URL;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.service.ECPayCheckoutService;
import ecpay.payment.integration.domain.AioCheckOutALL;

@WebServlet("/ecpay/checkout")
public class ECPayCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = -3723135349824096416L;

	public static final ECPayCheckoutService SERVICE = new ECPayCheckoutService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		// 接收前端傳來的AioCheckOutALL物件
		AioCheckOutALL obj = json2Pojo(req, AioCheckOutALL.class);
		// 取得action
		String action = obj.getCustomField1();
		String returnURL = "";
		if ("opaOrderFirstCheckout".equals(action)) {
			// 設定綠界付款成功的回傳網址(放付款成功要執行的Controller路徑)
			// CustomField1為要傳給Controller更新用的OrderId
			returnURL = SERVER_URL + req.getContextPath() + "/api/opa/ECPayUpdateOrder?BuyGoOrderId="
					+ obj.getCustomField2();
		}
		
		if ("paCheckout".equals(action)) {
			// 設定綠界付款成功的回傳網址(放付款成功要執行的Controller路徑)
			// CustomField2為要傳給Controller更新用的OrderId
			returnURL = SERVER_URL + req.getContextPath() + "/api/UpdateOrder?paSoNo="
					+ obj.getCustomField2();
			System.out.println(returnURL);
		}

		// =================================TODO=================================
		// 要用綠界的可以直接照這裡的格式串接，TODO以外的地方應該不用改
//		if( "myAction".equals(action) ) {
//			returnURL = SERVER_URL + "Controller路徑";
//		}
		// =================================TODO=================================

		// 若returnURL不為空值就建立綠界付款表單傳回給前端
		if (returnURL != "") {
			// 取得綠界建立的form表單字串，並存在session寫入JSP檔
			String form = SERVICE.getECpayForm(obj, returnURL);
			req.getSession().setAttribute("ECPayForm", form);
			String url = "/front_end/pages/member/ECPayForm.jsp";

			// 將綠界付款JSP網址回傳給前端
			writePojo2Json(resp, req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
					+ req.getContextPath() + url);
		}

	}

}
