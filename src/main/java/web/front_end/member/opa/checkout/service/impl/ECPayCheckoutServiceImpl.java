package web.front_end.member.opa.checkout.service.impl;

import java.text.SimpleDateFormat;
import java.util.UUID;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import web.front_end.member.opa.checkout.service.ECPayCheckoutService;

public class ECPayCheckoutServiceImpl implements ECPayCheckoutService {

	@Override
	public String getECpayForm(AioCheckOutALL aioCheckOutALL) {
		// java.util.Date → String
		java.util.Date tradeDate = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String tradeDateStr = sdf.format(tradeDate);

		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		AllInOne all = new AllInOne("");

		aioCheckOutALL.setMerchantTradeNo(uuId);
		aioCheckOutALL.setMerchantTradeDate(tradeDateStr);
		aioCheckOutALL.setReturnURL("http://211.23.128.214:5000");
		aioCheckOutALL.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(aioCheckOutALL, null);
		return form;
	}

}
