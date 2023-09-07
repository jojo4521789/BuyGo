package web.front_end.member.opa.checkout.service;

import ecpay.payment.integration.domain.AioCheckOutALL;

public interface ECPayCheckoutService{
	String getECpayForm(AioCheckOutALL aioCheckOutALL);
}
