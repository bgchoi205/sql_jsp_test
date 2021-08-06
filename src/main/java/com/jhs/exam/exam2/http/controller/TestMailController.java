package com.jhs.exam.exam2.http.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jhs.exam.exam2.dto.MailAuth;
import com.jhs.exam.exam2.http.Rq;

public class TestMailController extends Controller {
	public void init() {
		
	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "send":
			actionShowSend(rq);
			break;
		case "doSend":
			actionDoSend(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionDoSend(Rq rq) {
		String emailAddress = rq.getParam("emailAddress", "");
		String emailTitle = rq.getParam("emailTitle", "");
		String emailBody = rq.getParam("emailBody", "");
		
		
		System.out.println("받는사람 주소 : " + emailAddress);
		System.out.println("메일제목 : " + emailTitle);
		System.out.println("메일내용 : " + emailBody);
		
		
		Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        
        Authenticator auth = new MailAuth("choibk4938@gmail.com", "ptkmpvttlebnrdgr");
        
        Session session = Session.getDefaultInstance(prop, auth);
        
        MimeMessage msg = new MimeMessage(session);
    
        try {
            msg.setSentDate(new Date());
            
            msg.setFrom(new InternetAddress("choibk4938@gmail.com", "VISITOR"));
            InternetAddress to = new InternetAddress(emailAddress); // 받는사람       
            msg.setRecipient(Message.RecipientType.TO, to);            
            msg.setSubject(emailTitle, "UTF-8"); // 제목
            msg.setText(emailBody, "UTF-8"); // 내용
            
            Transport.send(msg);
            
        } catch(AddressException ae) {            
            System.out.println("AddressException : " + ae.getMessage());           
        } catch(MessagingException me) {            
            System.out.println("MessagingException : " + me.getMessage());
        } catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException : " + e.getMessage());			
        }
        
        rq.replace("발송완료", "../../");
		
	}

	private void actionShowSend(Rq rq) {
		rq.jsp("test/mail/send");
	}


	
}
