package web.back_end.opa.req.controller;

import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.req.entity.OpaRequest;
import web.front_end.member.notification.entity.Notification;
import core.entity.Core;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet("/needLoginApi/admin/opa/request/notify")
public class AdminRequestNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final Core core = new Core();
		response.setCharacterEncoding("UTF-8");
		if (request.getParameter("id") == null) {
			core.setSuccessful(false);
			core.setMessage("invalid parameter");
			writePojo2Json(response, core);
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Notification notification = web.back_end.opa.req.util.OpaRequestConstants.SERVICE.getNotification(id);
			Integer inserted_id = web.front_end.member.notification.util.NotificationConstants.SERVICE.insert(notification);
			String error = sendEmailNotification(
				notification.getNotifiTitle(),
				notification.getNotifiContent(),
				notification.getMemberNo(),
				core
			);
			core.setSuccessful(error == null);
		} catch (Exception e) {
			core.setSuccessful(false);
            core.setMessage(e.getMessage());
        }
		writePojo2Json(response, core);
	}

	
	private String sendEmailNotification(String subject, String messageText, int memberNo, Core core) {
		try {
			Properties props = new Properties();

			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "465");
			
			final String myGmail = "tibamecha102g6@gmail.com";
			final String myGmail_password = "vwnzqhlqdujkuhwz";

Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});		
			String email = web.front_end.member.acc.util.MemberConstants.SERVICE.selectById(memberNo).getMemberEmail();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

			message.setSubject(subject);
			message.setText(messageText);
			Transport.send(message);
			core.setMessage("已通知 " + email);
			return null;
		} catch (MessagingException e) {
			return e.getMessage();
		}
	}
}