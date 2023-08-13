package web.front_end.member.pa.req.service.impl;

import java.util.List;

import web.front_end.member.pa.req.dao.MPaReqDAO;
import web.front_end.member.pa.req.dao.impl.MPaReqDAOImpl;
import web.front_end.member.pa.req.entity.MPaReq;
import web.front_end.member.pa.req.service.MPaReqService;

public class MPaReqServiceImpl implements MPaReqService {

	private MPaReqDAO dao;

	public MPaReqServiceImpl() {
		dao = new MPaReqDAOImpl();
	}

	//買家新增委託單
	public MPaReq add(MPaReq mPaReq) {

		// 查無賣家
		if (mPaReq.getMemberNoSeller() == null) {
			mPaReq.setMessage("查無此賣家");
			mPaReq.setSuccessful(false);
			return mPaReq;
		}

		// 商品名稱欄為空
		if (mPaReq.getPaRqProdName() == null) {
			mPaReq.setMessage("未輸入商品名稱");
			mPaReq.setSuccessful(false);
			return mPaReq;
		}

		// 輸入商品數量為零
		if (mPaReq.getPaRqProdName() == null || mPaReq.getPaRqProdName().isEmpty()) {
			mPaReq.setMessage("數量輸入有誤");
			mPaReq.setSuccessful(false);
			return mPaReq;
		}

		// 輸入網址為空或無效
		if (mPaReq.getPaRqNo() == null) {
			mPaReq.setMessage("未輸入商品網址");
			mPaReq.setSuccessful(false);
			return mPaReq;
		}
		
		mPaReq.setMessage("訂單送出成功");
		mPaReq.setSuccessful(true);
		return mPaReq;
	}

	//查詢委託紀錄
	public List<MPaReq> checkAll() {
		return dao.selectAll();
	}
}
