package com.jhs.exam.exam2.dto;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
    
    PasswordAuthentication pa;
    
    public MailAuth(String mail_id, String mail_pw) {
//        String mail_id = "choibk4938@gmail.com";
//        String mail_pw = "jwljjubnpywgmakd";
        
        pa = new PasswordAuthentication(mail_id, mail_pw);
    }
    
    public PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
}

