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

	@Override
	// 買家新增委託單
	public MPaReq add(MPaReq mPaReq) {

		// 商品名稱欄為空
		if (mPaReq.getPaRqProdName() == null) {
			mPaReq.setMessage("未輸入商品名稱");
			mPaReq.setSuccessful(false);
			return mPaReq;
		}

		// 輸入商品金額為零
		if (mPaReq.getPaRqPrice() == null) {
			mPaReq.setMessage("商品金額輸入有誤");
			mPaReq.setSuccessful(false);
			return mPaReq;
		}

		// 輸入商品數量為零
		if (mPaReq.getPaRqQty() == null) {
			mPaReq.setMessage("商品數量輸入有誤");
			mPaReq.setSuccessful(false);
			return mPaReq;
		}

		// 輸入網址為空或無效
		if (mPaReq.getPaRqUrl() == null) {
			mPaReq.setMessage("未輸入商品網址");
			mPaReq.setSuccessful(false);
			return mPaReq;
		}
		
		//調用dao中的insert方法
		final int resultCount = dao.insert(mPaReq);
		if(resultCount < 1) {
			mPaReq.setMessage("訂單送出失敗，請聯絡客服人員!");
			mPaReq.setSuccessful(false);
			return mPaReq;
		}

		mPaReq.setMessage("訂單送出成功");
		mPaReq.setSuccessful(true);
		return mPaReq;
	}

	@Override
	// 查詢委託紀錄
	public List<MPaReq> checkAll() {
		
		//調用dao中的selectAll方法
		return dao.selectAll();
	}
	
	@Override
	//用會員ID查詢屬於該會員的委託資料
	public List<MPaReq> loadReqListByMemberNo(Integer memberNo) {
		return dao.selectByMemberNo(memberNo);
	}
	
	@Override
	//用會員ID查詢屬於該會員的委託資料
	public List<MPaReq> loadReqListByMemberNoSeller(Integer memberNoSeller) {
		return dao.selectByMemberNoSeller(memberNoSeller);
	}
	
	@Override
	//修改委託單狀態(賣家)
	public MPaReq updatePaReqStatus(MPaReq mPaReq) {
		final int resultCount = dao.updateReqStatus(mPaReq);
		mPaReq.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		mPaReq.setSuccessful(resultCount > 0);
		return mPaReq;
	}



}
