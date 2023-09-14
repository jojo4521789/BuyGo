package web.front_end.member.pa.order.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import redis.clients.jedis.Jedis;
import web.back_end.member.wallet.dao.WalletMemberDao;
import web.back_end.member.wallet.dao.impl.WalletMemberDaoImpl;
import web.front_end.member.acc.dao.MemberDao;
import web.front_end.member.acc.dao.impl.MemberDaoImpl;
import web.front_end.member.acc.entity.Member;
import web.front_end.member.pa.order.DTO.UpdateOrderDTO;
import web.front_end.member.pa.order.dao.PaReturnDAO;
import web.front_end.member.pa.order.dao.PaReturnDetailsDAO;
import web.front_end.member.pa.order.dao.PaSoDAO;
import web.front_end.member.pa.order.dao.PaSoDetailsDAO;
import web.front_end.member.pa.order.dao.impl.PaReturnDAOImpl;
import web.front_end.member.pa.order.dao.impl.PaReturnDetailsDAOImpl;
import web.front_end.member.pa.order.dao.impl.PaSoDAOImpl;
import web.front_end.member.pa.order.dao.impl.PaSoDetailsDAOImpl;
import web.front_end.member.pa.order.entity.PaReturn;
import web.front_end.member.pa.order.entity.PaReturnDetails;
import web.front_end.member.pa.order.entity.PaSo;
import web.front_end.member.pa.order.entity.PaSoDetails;
import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.prod.dao.PaProdDAO;
import web.front_end.member.pa.prod.dao.impl.PaProdDAOImpl;
import web.front_end.member.pa.prod.entity.PaProd;
import web.front_end.member.pa.prodpic.dao.ProdPicDAO;
import web.front_end.member.pa.prodpic.dao.impl.ProdPicDAOImpl;
import web.front_end.member.wallet.dao.WalletTransHistDao;
import web.front_end.member.wallet.dao.impl.WalletTransHistDaoImpl;
import web.front_end.member.wallet.entity.WalletTransHist;

public class PaSoServiceImpl implements PaSoService {
	private PaSoDAO paSoDAO;
	private PaSoDetailsDAO paSoDetailsDAO;
	private PaProdDAO paProdDAO;
	private ProdPicDAO paProdPicsDAO;
	private MemberDao memberDao;
	private PaReturnDAO paReturnDAO;
	private PaReturnDetailsDAO paReturnDetailsDAO;
	private WalletTransHistDao walletTransHistDao;
	private WalletMemberDao walletMemberDao;
	private static final int[] DIGIT_RANGE = { 9, 99, 999, 9999, 99999, 999999, 9999999 };

	public PaSoServiceImpl() {
		paSoDAO = new PaSoDAOImpl();
		paSoDetailsDAO = new PaSoDetailsDAOImpl();
		paProdDAO = new PaProdDAOImpl();
		paProdPicsDAO = new ProdPicDAOImpl();
		memberDao = new MemberDaoImpl();
		paReturnDAO = new PaReturnDAOImpl();
		paReturnDetailsDAO = new PaReturnDetailsDAOImpl();
		walletTransHistDao = new WalletTransHistDaoImpl();
		walletMemberDao = new WalletMemberDaoImpl();
	}

	// 查詢所有PaSo
	public List<PaSo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	// 查詢買家訂單(依訂單狀態)
	public List<PaSo> findBuyerOrders(Integer buyerNo, Byte status) {
		return paSoDAO.selectByBuyerNo(buyerNo, status);
	}

	// 查詢賣家訂單(依訂單狀態)
	@Override
	public List<PaSo> findSellerOrders(Integer sellerNo, Byte status) {
		List<PaSo> oldList = paSoDAO.selectAllByStatus(status); // 先查詢各狀態下的全部訂單
		System.out.println("查詢全部訂單，狀態" + status + " = " + oldList);
		List<PaSo> newList = new LinkedList<PaSo>();
		for (PaSo paSo : oldList) {
			for (PaSoDetails paSoDetails : paSo.getPaSoDetails()) {
				if (paSoDetails.getPaProd().getMemberNo().equals(sellerNo)) { // 確認商品明細是哪個賣家(equals是比較值是否相等，而非是否為同樣物件)
					newList.add(paSo);
					break; // 因一張訂單都是同一個賣家, 只要加入一次List即可
				}
			}
		}
		System.out.println("查詢該賣家訂單，狀態" + status + " = " + newList);
		return newList;
	}

	// 依訂單Id查詢詳情
	public PaSo findPaSoByPaSoNo(Integer paSoNo) {
		return paSoDAO.selectById(paSoNo);
	}

	// 產生新訂單
	public Integer generateNewOrder(Integer memberNo, PaSo paSo, List<PaSoDetails> paSoDetailsList) {
		paSo.setMemberNo(memberNo); // 設定買家
		Integer generatedId = paSoDAO.insert(paSo); // 新增PaSo並取得自增主鍵值
		paSoDAO.updateSoSeq(generatedId, seqPadding(generatedId)); // 更新PaSo.lpaSeq
		for (PaSoDetails paSoDetails : paSoDetailsList) { // 新增PaSoDetails
			paSoDetails.setPaSoNo(generatedId);
			paSoDetailsDAO.insert(paSoDetails);
		}

		// 加入刪除購物車欄位

		String paSoSeq = paSoDAO.selectById(generatedId).getPaSoSeq(); // why null??
		return generatedId;
	}

	// 將新訂單產生編號
	public static String seqPadding(Integer nextId) {

		int digitNum = 0, count = 7;
		// 檢查自增主鍵值有幾個數字
		for (;; digitNum++) {
			if (nextId <= DIGIT_RANGE[digitNum]) {
				digitNum++;
				break;
			}
		}

		// 根據數字數量再補上對應0的數量到7個位數
		// 例如：自增主鍵值是3，所以要再補上6個0，串接成為SO0000003
		// 又或是自增主鍵值為13，所以要再補上5個0，串接成為SO0000013
		StringBuilder sb = new StringBuilder("SO");
		for (int i = 1; i <= (count - digitNum); i++) {
			sb.append(0);
		}

		return sb.append(nextId).toString();
	}

	// 更新訂單詳情每個商品的狀態(是否買到商品)
	public PaSoDetails updateOrderDetailStatus(PaSoDetails paSoDetails) {
		Integer paSoNo = paSoDetails.getPaSoNo();
		Integer paProdNo = paSoDetails.getPaProdNo();
		Integer status = paSoDetails.getStatus();
		paSoDetailsDAO.updateStatus(paSoNo, paProdNo, status);

		return paSoDetailsDAO.selectBySoNoAndProdNo(paSoNo, paProdNo);
	}

	// 更改訂單狀態(改已付款, 運送中, 取消...等等)
	public int updateOrder(UpdateOrderDTO updateOrderDTO) {
		Integer paSoNo = updateOrderDTO.getPaSoNo();
		Byte newStatus = updateOrderDTO.getNewStatus();
		Integer buyerNo = updateOrderDTO.getBuyerNo();
		String deliverMsg = updateOrderDTO.getDeliverMsg();
		WalletTransHist walletTransHist = new WalletTransHist();
		Double newWalletAmount;
		Integer refundAmount = Integer.valueOf(updateOrderDTO.getRefundToBuyer()); // 退款金額
		// 若新狀態是1(改為已付款)
		if (newStatus == 1) {
			paSoDAO.updateSoStatus(paSoNo, newStatus);
		}

		// 若新狀態是2(運送中), 需更新PaSo出貨相關欄位並檢查是否退款給賣家
		if (newStatus == 2) {
			paSoDAO.updateSoStatus(paSoNo, newStatus);
			paSoDAO.updateSoDeliverDetails(paSoNo, deliverMsg);

			PaSo paSo = paSoDAO.selectById(paSoNo);
			Member buyer = memberDao.selectById(buyerNo);
			// 發送出貨通知
			sendMail(paSo, buyer);

			// 確認是否需退款給買家
			if (refundAmount != 0) {
				// 增加錢包交易紀錄
				walletTransHist.setMemberNo(buyerNo);
				walletTransHist.setWalletAmount(refundAmount);
				walletTransHist.setWalletDetail("部分商品追加失敗，退款" + refundAmount + "元");
				walletTransHist.setWalletStatus((byte) 3);
				walletTransHistDao.insert(walletTransHist);
				// 更新會員錢包餘額
				newWalletAmount = walletMemberDao.selectMemberByMemberNo(buyerNo).getMemberWalletAmount() + refundAmount;
				walletMemberDao.updateWalletAmountByMemberNoAndWalletAmount(buyerNo, newWalletAmount);
			}
		}

		// 若新狀態是3(確認收貨), 撥款給賣家
		if (newStatus == 3) {
			paSoDAO.updateSoStatus(paSoNo, newStatus);
			
			Integer orderAmount = paSoDAO.selectById(paSoNo).getPaTotalAmount();
			Integer sellerNo = paSoDAO.selectById(paSoNo).getPaProds().get(0).getMemberNo();
			
			// 增加錢包交易紀錄
			walletTransHist.setMemberNo(sellerNo);
			walletTransHist.setWalletAmount(orderAmount);
			walletTransHist.setWalletDetail("存入貨款" + orderAmount + "元");
			walletTransHist.setWalletStatus((byte) 3);
			walletTransHistDao.insert(walletTransHist);
			// 更新會員錢包餘額
			newWalletAmount = walletMemberDao.selectMemberByMemberNo(sellerNo).getMemberWalletAmount() +orderAmount;
			walletMemberDao.updateWalletAmountByMemberNoAndWalletAmount(sellerNo, newWalletAmount);
		}
		// 若新狀態是4(取消訂單), 全額退款給賣家
		if (newStatus == 4) {
			paSoDAO.updateSoStatus(paSoNo, newStatus);
			// 退款給買家
			if (refundAmount != 0) {
				// 增加錢包交易紀錄
				walletTransHist.setMemberNo(buyerNo);
				walletTransHist.setWalletAmount(refundAmount);
				walletTransHist.setWalletDetail("訂單取消，退款" + refundAmount + "元");
				walletTransHist.setWalletStatus((byte) 3);
				walletTransHistDao.insert(walletTransHist);
				// 更新會員錢包餘額
				newWalletAmount = walletMemberDao.selectMemberByMemberNo(buyerNo).getMemberWalletAmount() + refundAmount;
				walletMemberDao.updateWalletAmountByMemberNoAndWalletAmount(buyerNo, newWalletAmount);
			}
		}

		return 0;
	}

	// 依商品號碼查詢第一個圖片
	public String showFirstImage(Integer prodNo) {
		System.out.println("抓圖片service有運作");
		return paProdPicsDAO.selectProdFirstPic(prodNo);
	}

	// 產生新退貨單 & 退貨單明細
	public String generateNewReturn(PaReturn paReturn, List<Integer> paReturnProdNos, List<String> images) {

		try (Jedis jedis = new Jedis("localhost", 6379)) {
			// 先確認該訂單號碼是否已申請過退貨單
			if (paReturnDAO.selectByPaSoNo(paReturn.getPaSoNo()).size() == 0) {
				// 新增退貨單並取得自增主鍵值
				Integer generatedId = paReturnDAO.insert(paReturn);
				String paRtnSeq = RtnSeqPadding(generatedId);
				paReturnDAO.updateRtnSeq(generatedId, paRtnSeq);

				// 設定退貨單明細(商品編號)
				for (Integer returnProdNo : paReturnProdNos) {
					PaReturnDetails paReturnDetails = new PaReturnDetails();
					paReturnDetails.setPaRtnNo(generatedId);
					paReturnDetails.setPaRtnProdNo(returnProdNo);
					paReturnDetails.setPaRtnProdName(paProdDAO.selectById(returnProdNo).getPaProdName());
					paReturnDetailsDAO.insert(paReturnDetails); // 為何參數是detached狀態?
				}

				// 將訂單狀態改成5(退貨)
				paSoDAO.updateSoStatus(paReturn.getPaSoNo(), (byte) 5);

				// 將退貨圖片的Base64存到Redis
				for (String base64 : images) {
					jedis.rpush("paRtn" + generatedId, base64);
				}

				return "新退貨單已成立，編號為: " + RtnSeqPadding(generatedId);
			} else {
				return "已申請過退貨單";
			}
		} catch (Exception e) {
			e.printStackTrace(); // javax.persistence.NoResultException: No entity found for query
			return "發生錯誤";
		}

	}

	// 將新退貨單產生編號
	public static String RtnSeqPadding(Integer nextId) {

		int digitNum = 0, count = 6;
		// 檢查自增主鍵值有幾個數字
		for (;; digitNum++) {
			if (nextId <= DIGIT_RANGE[digitNum]) {
				digitNum++;
				break;
			}
		}

		// 根據數字數量再補上對應0的數量到7個位數
		// 例如：自增主鍵值是3，所以要再補上6個0，串接成為SO0000003
		// 又或是自增主鍵值為13，所以要再補上5個0，串接成為SO0000013
		StringBuilder sb = new StringBuilder("RTN");
		for (int i = 1; i <= (count - digitNum); i++) {
			sb.append(0);
		}

		return sb.append(nextId).toString();
	}

	// 更新退貨單狀態
	public int updateOrCancelReturn(PaReturn paReturn) {
		Integer paRtnNo = paReturn.getPaRtnNo();
		Integer newPaRtnStat = paReturn.getPaRtnStat();
		Integer paSoNo = paReturn.getPaSoNo();
		Integer paRtnAmount = paReturn.getPaRtnAmount();
		Integer buyerNo = paSoDAO.selectById(paSoNo).getMemberNo();
		Double newWalletAmount;

		if (newPaRtnStat == 4) { // 買家取消申請退貨
			return paReturnDAO.updateReturnStatus(paRtnNo, newPaRtnStat);
		} else if (newPaRtnStat == 2) { // 賣家同意退貨
			// 退款到錢包
			WalletTransHist walletTransHist = new WalletTransHist();
			walletTransHist.setMemberNo(buyerNo);
			walletTransHist.setWalletAmount(paRtnAmount);
			walletTransHist.setWalletDetail("退貨成立，退款" + paRtnAmount + "元");
			walletTransHist.setWalletStatus((byte) 3);
			walletTransHistDao.insert(walletTransHist);
			// 更新會員錢包餘額
			newWalletAmount = walletMemberDao.selectMemberByMemberNo(buyerNo).getMemberWalletAmount() + paRtnAmount;
			walletMemberDao.updateWalletAmountByMemberNoAndWalletAmount(buyerNo, newWalletAmount);

			return paReturnDAO.updateReturnStatus(paRtnNo, newPaRtnStat);
		} else {
			return paReturnDAO.updateReturnStatus(paRtnNo, newPaRtnStat);
		}
	}

	// 傳送出貨email
	public void sendMail(PaSo paSo, Member buyer) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●1) 登入你的Gmail的:
			// ●2) 點選【管理你的 Google 帳戶】
			// ●3) 點選左側的【安全性】

			// ●4) 完成【兩步驟驗證】的所有要求如下:
			// ●4-1) (請自行依照步驟要求操作之.....)

			// ●5) 完成【應用程式密碼】的所有要求如下:
			// ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
			// ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
			// ●5-3) 最後按【產生】密碼
			// BuyGo帳密
			final String myGmail = "tibamecha102g6@gmail.com";
			final String myGmail_password = "vwnzqhlqdujkuhwz";

			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(buyer.getMemberEmail()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pig57132000@gmail.com"));

			// 設定信中的主旨
			message.setSubject("[出貨通知]BuyGo訂單編號：" + paSo.getPaSoSeq());
			// 設定信中的內容

			String prodList = "";
			for (PaSoDetails paSoDetails : paSo.getPaSoDetails()) {
				String prodName = paSoDetails.getPaProd().getPaProdName(); // 商品名稱
				Integer prodStatus = paSoDetails.getStatus(); // 追加狀態
				System.out.println("追加狀態" + prodStatus);
				if (prodStatus == 1) {
					prodList += ("●" + prodName + ": 追加成功\r\n");
				} else {
					prodList += ("●" + prodName + ": 追加失敗\r\n");
				}
			}

			String messageText = "親愛的" + buyer.getMemberName() + "，\r\n" + "\r\n" + "您的訂單編號：" + paSo.getPaSoSeq()
					+ "\r\n" + "已於" + paSo.getPaDeliverTime() + "出貨。\r\n" + "\r\n" + "商品明細為：\r\n" + prodList + "\r\n"
					+ "\r\n" + "出貨方式為：" + paSo.getPaDeliverMethod() + "\r\n" + "\r\n" + "賣家備註：" + paSo.getPaDeliverMsg()
					+ "\r\n" + "\r\n" + "待收到貨以後，請記得至查詢訂單頁面點選[確認收貨]按鈕" + "\r\n" + "如有任何疑問或需要協助，請聯繫賣家。\r\n" + "\r\n"
					+ "謝謝！\r\n" + "\r\n" + "BuyGo團隊";

			message.setText(messageText);

			Transport.send(message);
//			System.out.println("傳送成功!");
		} catch (MessagingException e) {
//			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}
}
