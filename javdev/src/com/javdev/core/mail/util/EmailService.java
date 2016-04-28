package com.javdev.core.mail.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class EmailService {

	private final Properties properties = new Properties();

	// Java basic Object
	@Getter @Setter private List<String> emailTORecipientList;
	@Getter @Setter private List<String> emailBBCRecipientList;
	@Getter @Setter private List<String> emailCCRecipientList;
	@Getter @Setter private List<File> attachedList;

	// Java Mail Session
	private Session session;


	/** @author MTorres 28 de abr. de 2016 9:07:17 */
	private void init(boolean debug) throws Exception {
		try {
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.debug", debug ? "true" : "false");
			this.session = loadSession();
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 28 de abr. de 2016 9:41:42 */
	private Session loadSession() throws Exception {
		try {
			return Session.getInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("migueltrock@gmail.com", "m3t1sM10");
				}
			});
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 28 de abr. de 2016 9:14:44 */
	public void sendMail(String from, String subject, boolean debug, String msg) throws Exception {
		try {
			if ((this.emailBBCRecipientList != null && !this.emailBBCRecipientList.isEmpty())
				|| (this.emailTORecipientList != null && !this.emailTORecipientList.isEmpty())
				|| (this.emailCCRecipientList != null && !this.emailCCRecipientList.isEmpty())) {

				init(debug);
				MimeMessage message = new MimeMessage(this.session);
				message.setFrom(new InternetAddress(from != null && !from.trim().isEmpty() ? from : "migueltrock@gmail.com"));

				if (this.emailBBCRecipientList != null && !this.emailBBCRecipientList.isEmpty())
					message.setRecipients(Message.RecipientType.BCC, loadAddressList(this.emailBBCRecipientList));
				if (this.emailCCRecipientList != null && !this.emailCCRecipientList.isEmpty())
					message.setRecipients(Message.RecipientType.CC, loadAddressList(this.emailCCRecipientList));
				if (this.emailTORecipientList != null && !this.emailTORecipientList.isEmpty())
					message.setRecipients(Message.RecipientType.TO, loadAddressList(this.emailTORecipientList));

				message.setSubject(subject);

				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(msg, "text/html");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				if (this.attachedList != null && this.attachedList != null) {
					for (File f : this.attachedList) {
						if (f != null && f.exists() && f.canRead())
							addAttachment(multipart, f);
					}
				}
				message.setContent(multipart);
				Transport.send(message);
				log.info("INFO: Mail sent successfully ");
			} else
				log.warning("WARN: You must select at least one receiver for the message. ");
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 28 de abr. de 2016 11:14:42 */
	private static void addAttachment(Multipart mp, File f) throws Exception {
		try {
			DataSource ds = new FileDataSource(f);
			BodyPart bp = new MimeBodyPart();
			bp.setDataHandler(new DataHandler(ds));
			bp.setFileName(f.getName());
			mp.addBodyPart(bp);
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 28 de abr. de 2016 10:28:20 */
	private Address[] loadAddressList(List<String> emailList) throws Exception {
		try {
			if (emailList != null && !emailList.isEmpty()) {
				Address[] addresList = new Address[emailList.size()];
				for (int i = 0; i < emailList.size(); i++) {
					addresList[i] = new InternetAddress(emailList.get(i));
				}
				return addresList;
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	public static void main(String[] args) {
		try {
			EmailService s = new EmailService();
			s.setAttachedList(new ArrayList<File>() {
				{
					add(new File("C:/test/attachment.txt"));
					add(new File("D:/Documents/Miguel/ingenieria económica/tasasinteres.pdf"));
				}
			});
			s.setEmailBBCRecipientList(new ArrayList<String>() {
				{
					add("migueltrock@gmail.com");
					add("miguel_90topo@hotmail.com");
				}
			});
			s.sendMail(null, "Mi primer correo con adjunto", true, "enviando mi mensaje aqui");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
