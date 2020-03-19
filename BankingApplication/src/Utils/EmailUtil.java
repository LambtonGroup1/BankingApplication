package Utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import config.Configurations;
import models.LoginDetails;

public class EmailUtil {

	public static void sendFromGMail(String to, LoginDetails loginDetails) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", Configurations.USER_NAME);
		props.put("mail.smtp.password", Configurations.PASSWORD);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(Configurations.USER_NAME));
			InternetAddress toAddress = new InternetAddress(to);

			message.addRecipient(Message.RecipientType.TO, toAddress);

			String subject = " Welcome to the Bank";

			String body = "Hi " + loginDetails.getAccountNumber()
					+ "\nWelcome to the bank. Please use below credentials to login into your" + "account.\n"
					+ "USERNAME : " + loginDetails.getAccountNumber() + "\n" + "PASSWORD : "
					+ loginDetails.getPassword() + "\n"
					+ "Note that this is a temporary password. Kindly change it on first login.";

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, Configurations.USER_NAME, Configurations.PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}