package com.gree.ant.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Properties;


/**
 * Utility class to send e-mail.
 * 
 * <p>
 * <a href="MailUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version $Revision: 1.4 $ $Date: 2014/03/15 06:17:44 $
 * 
 */
public class MailUtil {
	// ~ Static fields/initializers
	// =============================================
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	// ~ Methods
	// ================================================================

	/**
	 * Retrieves a Mail session from Tomcat's Resource Factory (JNDI)
	 */
	private static Session getSession() {
		Session session = null;

		try {
			session = (Session) new InitialContext()
					.lookup(Constants.JNDI_MAIL);
		} catch (NamingException ex) {
			Properties props = new Properties();
			// props.setProperty("mail.debug",
			// mailProps.getString("mail.debug"));
			// props.setProperty("mail.transport.protocol",
			// mailProps.getString("mail.transport.protocol"));
			// props.put("mail.smtp.host",host);
			props.put("mail.smtp.host", "10.1.1.195");
			props.put("mail.smtp.auth", "false");
			session = Session.getDefaultInstance(props, null);
		}
		return session;
	}

	/**
	 * This method is used to send a Message with an attachment.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address(es) of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @param mimeType
	 *            type of message, i.e. text/plain or text/html
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendMessageWithAttachment(String from, String[] to,
			String[] cc, String subject, String content, String mimeType,
			File attachment) throws MessagingException {
		Message message = new MimeMessage(getSession());

		// TODO: Refactor to use a default from address (maybe in config?!)
		if (true) {
			InternetAddress sentFrom = new InternetAddress(from+ "@gree.com.cn");
			message.setFrom(sentFrom);
		}

		InternetAddress[] sendTo = new InternetAddress[to.length];

		for (int i = 0; i < to.length; i++) {
			sendTo[i] = new InternetAddress(to[i]+"@gree.com.cn");

		}

		setCopyTo(cc,message);
		message.setRecipients(Message.RecipientType.TO, sendTo);
		message.setSubject(subject);

		// create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setContent(content, mimeType);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();

		DataSource source = new FileDataSource(attachment);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(attachment.getName());

	

		multipart.addBodyPart(messageBodyPart);

		// Put parts in message
		message.setContent(multipart);

		Transport.send(message);
	}

	public static void sendMessageWithAttachments(String from, String[] to,
			String[] cc, String subject, String content, String mimeType,
			File[] attachment) throws MessagingException {
		Message message = new MimeMessage(getSession());

		// TODO: Refactor to use a default from address (maybe in config?!)
		if (true) {
			InternetAddress sentFrom = new InternetAddress(from+ "@gree.com.cn");
			message.setFrom(sentFrom);

		}

		InternetAddress[] sendTo = new InternetAddress[to.length];

		for (int i = 0; i < to.length; i++) {
			sendTo[i] = new InternetAddress(to[i]+ "@gree.com.cn");

	
		}
		setCopyTo(cc,message);
		message.setRecipients(Message.RecipientType.TO, sendTo);
		message.setSubject(subject);

		// create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(content, mimeType);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		for (int i = 0; i < attachment.length; i++) {
			messageBodyPart = new MimeBodyPart();

			DataSource source = new FileDataSource(attachment[i]);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachment[i].getName());

		

			multipart.addBodyPart(messageBodyPart);

		}

		// Put parts in message
		message.setContent(multipart);

		Transport.send(message);
	}

	/**
	 * This method is used to send a Message with a pre-defined mime-type.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address(es) of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @param mimeType
	 *            type of message, i.e. text/plain or text/html
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendMessage(String from, String[] to, String[] cc,
			String subject, String content, String mimeType)
			throws MessagingException {
		Message message = new MimeMessage(getSession());

		// TODO: Refactor to use a default from address (maybe in config?!)
		if (true) {
			InternetAddress sentFrom = new InternetAddress(from + "@gree.com.cn");
			System.out.println(">>>>>>>from>>>>>>>>>"+from);
			message.setFrom(sentFrom);
		}

		InternetAddress[] sendTo = new InternetAddress[to.length];

		for (int i = 0; i < to.length; i++) {
			sendTo[i] = new InternetAddress(to[i] + "@gree.com.cn");
		}
		setCopyTo(cc,message);
		message.setRecipients(Message.RecipientType.TO, sendTo);
		message.setSubject(subject);
		message.setContent(content, mimeType);
		Transport.send(message);
	}

	/**
	 * This method is used to send a Text Message.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail addresses of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendTextMessage(String from, String[] to, String[] cc,
			String subject, String content) throws MessagingException {
		sendMessage(from, to, cc, subject, content, "text/plain");
	}

	/**
	 * This method overrides the sendTextMessage to specify only one sender,
	 * rather than an array of senders.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendTextMessage(String from, String to, String cc,
			String subject, String content) throws MessagingException {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessage(from, recipient, copy, subject, content, "text/plain");
	}

	/**
	 * This method allows you to specify the mimeType as part of the method
	 * call.
	 */
	public static void sendMessage(String from, String to, String cc,
			String subject, String content, String mimeType)
			throws MessagingException {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessage(from, recipient, copy, subject, content, mimeType);
	}

	/**
	 * Convenience method for sending messages with attachments.
	 */
	public static void sendMessage(String from, String to, String cc,
			String subject, String content, String mimeType, File attachment)
			throws MessagingException {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessageWithAttachment(from, recipient, copy, subject, content,
				mimeType, attachment);
	}

	/**
	 * This method is used to send a HTML Message
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address(es) of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendHTMLMessage(String from, String[] to, String[] cc,
			String subject, String content) throws MessagingException {
		sendMessage(from, to, cc, subject, content, "text/html");
	}

	/**
	 * This method overrides the sendHTMLMessage to specify only one sender,
	 * rather than an array of senders.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendHTMLMessage(String from, String to, String cc,
			String subject, String content) throws MessagingException {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessage(from, recipient, copy, subject, content, "text/html");
	}

	/**
	 * Sendmail.
	 *
	 * @param userid 接收用户
	 * @param titl   邮件问候语
	 * @param djno   任务编号
	 * @param notes  任务标题
	 * @description 用一句话描述这个方法的作用.
	 * @author create by jinyuk@foxmail.com.
	 * @version V1.0
	 * @createTime 2017 :10:16 10:10:16.
	 */
	public static void sendmail(String userid, String titl, String djno,String notes,String sys) {
        
		String subject="";

		subject= " <^-^> GREE-ANT GNAW BONE 蚂蚁系统提示:";

		String contentType = "text/html;charset=UTF-8";
		String[] to = { userid };
		String[] cc = {};
        
		String content = getContent(titl, djno,notes,sys);
		try {
			sendMessage("AutoMail", to, cc, subject, content, contentType);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sendmail.
	 *
	 * @param csid 接收者
	 * @param cc   抄送者
	 * @param ptno 绩效表编号
	 * @param grop 团队描述
	 * @description 发送当月绩效表给目标
	 * @author create by jinyuk@foxmail.com.
	 * @version V1.0
	 * @createTime 2017 :10:13 11:10:10.
	 */
	public static void sendmail(String[] csid,String[] cc,String ptno,String grop) {

		String subject="";

		subject= " <^-^> GREE-ANT GNAW BONE 蚂蚁系统提示:";

		String contentType = "text/html;charset=UTF-8";
		String content = getContent(ptno,grop);
		try {
			sendMessage("AutoMail", csid, cc, subject, content, contentType);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendmail(String csid,Long doid,String usid,String tilt) {

		String subject="";

		subject= " <^-^> GREE-ANT GNAW BONE 蚂蚁系统提示:";

		String contentType = "text/html;charset=UTF-8";
		String content = getContent(csid,doid,usid,tilt);
		try {
			sendMessage("AutoMail", csid, null, subject, content, contentType);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendmail(String csid,String taid,String tilt,String status) {

		String subject="";

		subject= " <^-^> GREE-ANT GNAW BONE 蚂蚁系统提示:";

		String contentType = "text/html;charset=UTF-8";
		String content = getEyeContent(csid,taid,tilt,status);
		try {
			sendMessage("AutoMail", csid, null, subject, content, contentType);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	private static String getContent(String titl, String cono,String notes,String sys) {
		return "<HTML><HEAD><TITLE>GREE-ANT GNAW BONE 蚂蚁系统</TITLE>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
				+ "<STYLE type=text/css>BODY {FONT-SIZE: 9pt; MARGIN: 0px; OVERFLOW: auto; FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #ffffff}"
				+ "BODY {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "TABLE {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "DIV#default {BORDER-RIGHT: black 0px solid; PADDING-RIGHT: 10px; BORDER-TOP: black 0px solid; PADDING-LEFT: 10px; BACKGROUND: #fff; PADDING-BOTTOM: 20px; MARGIN: 10pt auto 20pt 20pt; BORDER-LEFT: black 0px solid; WIDTH: 700px; PADDING-TOP: 10px; BORDER-BOTTOM: black 0px solid; TEXT-ALIGN: left}"
				+ "</STYLE>"
				+ "<BODY>"
				+ "<DIV id=default><B>GREE-ANT GNAW BONE 蚂蚁系统邮件提示:</B><BR>"
				+ "<TABLE width=\"100%\">"
				+ "  <TBODY>"
				+ "  <TR>"
				+ "    <TD colSpan=2>"
				+ " <LI><U>"+titl+"</U><FONT><U>"
				+ sys+">>>任务单号："+cono
				+ "("+ notes+ ") ;"
				+ "</U></FONT> 。 <FONT color=green>注：请及时<A HREF='http://10.1.18.83:887/ant'>"+"登录"+"</A>"+"系统处理。亲，记得用Chrome 谷歌浏览器登录！</FONT></LI></TD></TR></TR>"
				+ "<TR>"
				+ "<TD colSpan=2>"
				+ "<LI><FONT color=red><B>注:</B></FONT> "
				+ "此邮件为系统信息,请不要作回复操作。</LI></TD></TR></TBODY></TABLE></DIV></BODY></HTML>";

	}

	private static String getContent( String ptno,String grop) {
		return "<HTML><HEAD><TITLE>GREE-ANT GNAW BONE 蚂蚁系统</TITLE>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
				+ "<STYLE type=text/css>BODY {FONT-SIZE: 9pt; MARGIN: 0px; OVERFLOW: auto; FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #ffffff}"
				+ "BODY {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "TABLE {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "DIV#default {BORDER-RIGHT: black 0px solid; PADDING-RIGHT: 10px; BORDER-TOP: black 0px solid; PADDING-LEFT: 10px; BACKGROUND: #fff; PADDING-BOTTOM: 20px; MARGIN: 10pt auto 20pt 20pt; BORDER-LEFT: black 0px solid; WIDTH: 700px; PADDING-TOP: 10px; BORDER-BOTTOM: black 0px solid; TEXT-ALIGN: left}"
				+ "</STYLE>"
				+ "<BODY>"
				+ "<DIV id=default><B>GREE-ANT GNAW BONE 蚂蚁系统邮件提示:</B><BR>"
				+ "<TABLE width=\"100%\">"
				+ "  <TBODY>"
				+ "  <TR>"
				+ "    <TD colSpan=2>"
				+ " <LI>尊敬的领导，"+grop+"团队绩效表("+ptno+")，计划表已发送到您的邮箱！"
				+ "<FONT><U>"
				+ "请<A href='http://10.1.18.83:887/ant/grade/printAllGrade?ptno=" +ptno
				+"'>下载</A>"
				+ "</U></FONT></LI></TD></TR></TR>"
				+ "<TR>"
				+ "<TD colSpan=2>"
				+ "<LI><FONT color=red><B>注:</B></FONT> "
				+ "此邮件为系统信息,请不要作回复操作。</LI></TD></TR></TBODY></TABLE></DIV></BODY></HTML>";
	}

	private static String getContent( String csid,Long doid,String usid,String tilt) {
		return "<HTML><HEAD><TITLE>GREE-ANT GNAW BONE 蚂蚁系统</TITLE>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
				+ "<STYLE type=text/css>BODY {FONT-SIZE: 9pt; MARGIN: 0px; OVERFLOW: auto; FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #ffffff}"
				+ "BODY {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "TABLE {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "DIV#default {BORDER-RIGHT: black 0px solid; PADDING-RIGHT: 10px; BORDER-TOP: black 0px solid; PADDING-LEFT: 10px; BACKGROUND: #fff; PADDING-BOTTOM: 20px; MARGIN: 10pt auto 20pt 20pt; BORDER-LEFT: black 0px solid; WIDTH: 700px; PADDING-TOP: 10px; BORDER-BOTTOM: black 0px solid; TEXT-ALIGN: left}"
				+ "</STYLE>"
				+ "<BODY>"
				+ "<DIV id=default><B>GREE-ANT GNAW BONE 蚂蚁系统邮件提示:</B><BR>"
				+ "<TABLE width=\"100%\">"
				+ "  <TBODY>"
				+ "  <TR>"
				+ "    <TD colSpan=2>"
				+ " <LI>亲爱的"+csid+"，有一个分享文档("+tilt+")送达，请登录"
				+ "<FONT>"
				+ "<A href='http://10.1.18.83:887/ant/doc/showDoc?doid=" +doid
				+"'>查看</A>"
				+ "</FONT>,来自("+usid+")</LI></TD></TR></TR>"
				+ "<TR>"
				+ "<TD colSpan=2>"
				+ "<LI><FONT color=red><B>注:</B></FONT> "
				+ "此邮件为系统信息,请不要作回复操作。</LI></TD></TR></TBODY></TABLE></DIV></BODY></HTML>";
	}

	private static String getEyeContent( String csid,String taid,String tilt,String status) {
		return "<HTML><HEAD><TITLE>GREE-ANT GNAW BONE 蚂蚁系统</TITLE>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
				+ "<STYLE type=text/css>BODY {FONT-SIZE: 9pt; MARGIN: 0px; OVERFLOW: auto; FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #ffffff}"
				+ "BODY {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "TABLE {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "DIV#default {BORDER-RIGHT: black 0px solid; PADDING-RIGHT: 10px; BORDER-TOP: black 0px solid; PADDING-LEFT: 10px; BACKGROUND: #fff; PADDING-BOTTOM: 20px; MARGIN: 10pt auto 20pt 20pt; BORDER-LEFT: black 0px solid; WIDTH: 700px; PADDING-TOP: 10px; BORDER-BOTTOM: black 0px solid; TEXT-ALIGN: left}"
				+ "</STYLE>"
				+ "<BODY>"
				+ "<DIV id=default><B>GREE-ANT GNAW BONE 蚂蚁系统邮件提示:</B><BR>"
				+ "<TABLE width=\"100%\">"
				+ "  <TBODY>"
				+ "  <TR>"
				+ "    <TD colSpan=2>"
				+ " <LI>亲爱的"+csid+"，您关注的任务"+taid+"("+tilt+")状态更新为(-->>"+status+"<<--)，详情请"
				+ "<FONT>"
				+ "<A href='http://10.1.18.83:887/ant>"
				+"'>登录</A>查看"
				+ "</FONT></LI></TD></TR></TR>"
				+ "<TR>"
				+ "<TD colSpan=2>"
				+ "<LI><FONT color=red><B>注:</B></FONT> "
				+ "此邮件为系统信息,请不要作回复操作。</LI></TD></TR></TBODY></TABLE></DIV></BODY></HTML>";
	}

	private static void setCopyTo(String[] cc,Message message) throws MessagingException{
		if ((cc.length > 0) && (cc[0] != null)) {
			InternetAddress[] copyTo = new InternetAddress[cc.length];

			for (int i = 0; i < cc.length; i++) {
				copyTo[i] = new InternetAddress(cc[i]+"@gree.com.cn");
			}

			message.setRecipients(Message.RecipientType.CC, copyTo);
		}
	}
}
