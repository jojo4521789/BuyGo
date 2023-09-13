package core.service;

import java.text.SimpleDateFormat;
import java.util.UUID;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

public class ECPayCheckoutService {
	public String getECpayForm(AioCheckOutALL aioCheckOutALL, String returnURL) {
		// java.util.Date → String
		java.util.Date tradeDate = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String tradeDateStr = sdf.format(tradeDate);
		
		//使用 UUID 隨機產生 20 碼的亂數
		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		AllInOne all = new AllInOne("");

		aioCheckOutALL.setMerchantTradeNo(uuId);
		aioCheckOutALL.setMerchantTradeDate(tradeDateStr);
		aioCheckOutALL.setReturnURL(returnURL);
		aioCheckOutALL.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(aioCheckOutALL, null);
		return form;
	}
}
