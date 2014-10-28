//package test;
//
//import java.util.Date;
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.URLName;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//import jp.co.fujixerox.swpf.mail.NMSMTPTransport;
//
//public class SendMail {
//	public static void main(String[] args) throws Exception {
//		SendMail.sendMail();
//	}
//	
//	public static void sendMail() throws Exception {
//		String from = "test@ustcsoft.com";
//		String to = "lianghaijin@ustcsoft.com";
//		String subject = "Test mail";
//		String body = "A text mail";
//		Properties props = System.getProperties();
//		// 
//		props.put("mail.smtp.host", "172.17.1.9");
//		props.setProperty("mail.smtp.port", "25");
//		Session session = Session.getDefaultInstance(props);
//		URLName urlName = new URLName("smtp://172.17.1.9");
//		// 
//		Message msg = new MimeMessage(session);
//
//		msg.setFrom(new InternetAddress(from));
//		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,
//				false));
//		msg.setSubject(subject);
//		msg.setText(body);
//		msg.setSentDate(new Date());
//		Multipart multi = new MimeMultipart();
//		// 
//		MimeBodyPart mbp1 = new MimeBodyPart();		
//		mbp1.attachFile("out/outdddput.html");
////		mbp1.set
//		mbp1.setText("A text mail");
//		multi.addBodyPart(mbp1);
//		
////		mbp1 = new MimeBodyPart();
////		mbp1.setText("A text mail");
////		multi.addBodyPart(mbp1);
//		
//		msg.setContent(multi);
//		msg.saveChanges();
//		Transport transport = new NMSMTPTransport(session,urlName);
//		transport.connect();
//		transport.sendMessage(msg, msg.getAllRecipients());
//
//	}
//
//
//}
