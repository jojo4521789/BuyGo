package web.front_end.guest.register;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.acc.util.MemberConstants.SERVICE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.acc.entity.Member;

@WebServlet("/api/front_end/guest/register/MemberRegisterCheckMailServlet")
public class MemberRegisterCheckMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("1111111111");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Member member = json2Pojo(req, Member.class);
		String authCode = genAuthCode(); // 生成8位數authCode
		Map<String,String> checkMailMap = new HashMap();
		checkMailMap.put("memberEmail", member.getMemberEmail());
		checkMailMap.put("authCode", authCode);
		session.setAttribute("checkMailMap", checkMailMap);
		
		MailService mailService = new MailService();
		mailService.sendMail(member.getMemberEmail(),"BuyGo註冊驗證信", authCode);
	}

	static String genAuthCode() {
		// 驗證碼包含 0~9(10個) + A~Z(26個) + a~z(26個)
		// ASCII 0=48 9=57 A=65 Z=90 a=97 z=122
		char randomTable[] = new char[62];// 宣告含有所有字元的陣列，長度62=10+26+26
		String authCode = "";// 驗證碼
		int count = 0;// 存值進入陣列時累加用
		for (int i = 48; i <= 57; i++) {// 使用ASCII，將0~9存入矩陣
			randomTable[count] = (char) i;
			count++;
		}
		for (int i = 65; i <= 90; i++) {// 使用ASCII，將A~Z存入矩陣
			randomTable[count] = (char) i;
			count++;
		}
		for (int i = 97; i <= 122; i++) {// 使用ASCII，將a~z存入矩陣
			randomTable[count] = (char) i;
			count++;
		}

		for (int i = 0; i < 8; i++) {
			authCode += randomTable[(int) (Math.random() * 62)];// 使用Math.random隨機挑選該矩陣的Index
		}
		return authCode;
	}

	class MailService {

		// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
		public void sendMail(String to, String subject, String authCode) {

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
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				// 設定信中的主旨
				message.setSubject(subject);
				// 設定信中的內容

				String messageText = "親愛的訪客您好，\r\n" + "\r\n"
						+ "感謝您使用我們平台。為了確保您的帳戶安全並享受完整的服務，我們需要驗證您的電子郵件地址。請在平台頁面輸入下方驗證碼，以驗證電子郵件地址正確：\r\n" + "\r\n" + "驗證碼：" + authCode
						+ "\r\n" + "\r\n" + "如果您並未申請或有疑慮，請忽略此郵件。感謝您的理解與合作。\r\n" + "\r\n"
						+ "如有任何疑問或需要協助，請聯繫我們的客戶服務團隊。\r\n" + "\r\n" + "謝謝！\r\n" + "\r\n" + "BuyGo團隊";

				message.setText(messageText);

				Transport.send(message);
//				System.out.println("傳送成功!");
			} catch (MessagingException e) {
//				System.out.println("傳送失敗!");
				e.printStackTrace();
			}
		}
	}
}
