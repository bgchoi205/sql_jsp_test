package com.jhs.exam.exam2.http.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jhs.exam.exam2.app.App;
import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.dto.MailAuth;
import com.jhs.exam.exam2.dto.Member;
import com.jhs.exam.exam2.dto.ResultData;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.service.MemberService;
import com.jhs.exam.exam2.util.Ut;

public class UsrMemberController extends Controller {
	private MemberService memberService;

	public void init() {
		memberService = Container.memberService;
	}
	
	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "login":
			actionShowLogin(rq);
			break;
		case "doLogin":
			actionDoLogin(rq);
			break;
		case "doLogout":
			actionDoLogout(rq);
			break;
		case "join":
			actionShowJoin(rq);
			break;
		case "doJoin":
			actionDoJoin(rq);
			break;
		case "findLoginId":
			actionShowFindLoginId(rq);
			break;
		case "doFindLoginId":
			actionDoFindLoginId(rq);
			break;
		case "findLoginPw":
			actionShowFindLoginPw(rq);
			break;
		case "doFindLoginPw":
			actionDoFindLoginPw(rq);
			break;
		case "myPage":
			actionMyPage(rq);
			break;
		case "modifyMemberInfo":
			actionModifyMemberInfo(rq);
			break;
		case "doModifyMemberInfo":
			actionDoModifyMemberInfo(rq);
			break;
		case "modifyMemberPw":
			actionModifyMemberPw(rq);
			break;
		case "doModifyMemberPw":
			actionDoModifyMemberPw(rq);
			break;
		case "doLoginIdCheck":
			actionDoLoginIdCheck(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	

	private void actionDoModifyMemberPw(Rq rq) {
		if(rq.isNotLogined()) {
			rq.historyBack("로그인 후 이용해주세요.");
			return;
		}
		
		String loginPw = rq.getParam("loginPw", "");
		String newLoginPw = rq.getParam("newLoginPw", "");
		String checkLoginPw = rq.getParam("checkLoginPw", "");
		
		if (loginPw.length() == 0) {
			rq.historyBack("loginPw를 입력해주세요.");
			return;
		}
		
		if (newLoginPw.length() == 0) {
			rq.historyBack("newLoginPw를 입력해주세요.");
			return;
		}
		
		if (checkLoginPw.length() == 0) {
			rq.historyBack("checkLoginPw를 입력해주세요.");
			return;
		}
		
		Member loginedMember = rq.getLoginedMember();
		
		if(loginPw.equals(loginedMember.getLoginPw()) == false) {
			rq.historyBack("비밀번호가 틀립니다.");
			return;
		}
		
		if(newLoginPw.equals(checkLoginPw) == false) {
			rq.historyBack("새 비밀번호를 다시 확인해주세요.");
			return;
		}
		
		int loginedMemberId = loginedMember.getId();
		
		memberService.modifyMemberPw(loginedMemberId, newLoginPw);
		
		Member modifiedMember = memberService.getMemberById(loginedMemberId);
		
		rq.setSessionAttr("loginedMemberJson", Ut.toJson(modifiedMember, ""));
		
		rq.replace("비밀번호 변경 완료", "../member/myPage");
		
	}

	private void actionModifyMemberPw(Rq rq) {
		if(rq.isNotLogined()) {
			rq.historyBack("로그인 후 이용해주세요.");
			return;
		}
		
		rq.jsp("usr/member/modifyMemberPw");
		
	}

	private void actionDoModifyMemberInfo(Rq rq) {
		if(rq.isNotLogined()) {
			rq.historyBack("로그인 후 이용해주세요.");
			return;
		}
		
		Member loginedMember = rq.getLoginedMember();
		int loginedMemberId = loginedMember.getId();
		
		String name = rq.getParam("name", "");
		String nickname = rq.getParam("nickname", "");
		String email = rq.getParam("email", "");
		String cellphoneNo = rq.getParam("cellphoneNo", "");
		
		
		if (name.length() == 0) {
			rq.historyBack("name을 입력해주세요.");
			return;
		}
		
		if (nickname.length() == 0) {
			rq.historyBack("nickname을 입력해주세요.");
			return;
		}
		
		if (email.length() == 0) {
			rq.historyBack("email을 입력해주세요.");
			return;
		}
		
		if (cellphoneNo.length() == 0) {
			rq.historyBack("cellphoneNo를 입력해주세요.");
			return;
		}

		memberService.ModifyMemberInfo(name, nickname, email, cellphoneNo, loginedMemberId);
		
		Member modifiedMember = memberService.getMemberById(loginedMemberId);
		
		rq.setSessionAttr("loginedMemberJson", Ut.toJson(modifiedMember, ""));
		
		rq.replace("정보수정 완료", "../member/myPage");
	}

	private void actionModifyMemberInfo(Rq rq) {
		if(rq.isNotLogined()) {
			rq.historyBack("로그인 후 이용해주세요.");
			return;
		}
		
		Member loginedMember = rq.getLoginedMember();
		
		rq.setAttr("loginedMember", loginedMember);
		rq.jsp("usr/member/modifyMemberInfo");
	}
	
	
	private void actionMyPage(Rq rq) {
		if(rq.isNotLogined()) {
			rq.historyBack("로그인 후 이용해주세요.");
			return;
		}
		
		Member loginedMember = rq.getLoginedMember();
		
		rq.setAttr("loginedMember", loginedMember);
		rq.jsp("usr/member/myPage");
	}
	

	private void actionDoLoginIdCheck(Rq rq) {
		
		
	}

	private void actionDoFindLoginPw(Rq rq) {
		String loginId = rq.getParam("loginId", "");
		String email = rq.getParam("email", "");
		
		ResultData doFindLoginPwRd = memberService.doFindLoginPw(loginId, email);
		
		if (doFindLoginPwRd.isFail()) {
			rq.setAttr("message", "일치하는 회원이 없습니다.");
			rq.jsp("usr/member/findLoginPw");
			return;
		}
		
		Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        
        Authenticator auth = new MailAuth(Container.app.getSmtpGmailId(), Container.app.getSmtpGmailPw());
        
        Session session = Session.getDefaultInstance(prop, auth);
        
        MimeMessage msg = new MimeMessage(session);
        
        Random rnd = new Random();
		String temporaryPw = "";
		
		for(int i = 0; i < 6; i++) {
			temporaryPw += String.valueOf((char) ((int) (rnd.nextInt(26)) + 97));
		}
		
		memberService.setTemporaryPw(temporaryPw, loginId);
		
    
        try {
            msg.setSentDate(new Date());
            
            msg.setFrom(new InternetAddress("choibk4938@gmail.com", "VISITOR"));
            InternetAddress to = new InternetAddress(email); // 받는사람       
            msg.setRecipient(Message.RecipientType.TO, to);            
            msg.setSubject("임시비밀번호 발송", "UTF-8"); // 제목
            msg.setText(loginId + "님 임시비밀번호는 " + temporaryPw + " 입니다.", "UTF-8"); // 내용
            
            Transport.send(msg);
            
        } catch(AddressException ae) {            
            System.out.println("AddressException : " + ae.getMessage());           
        } catch(MessagingException me) {            
            System.out.println("MessagingException : " + me.getMessage());
        } catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException : " + e.getMessage());			
        }
        
        rq.replace("임시비밀번호 발송완료. 이메일 확인 후 다시 로그인해주세요.", "../member/login");
		
	}

	private void actionShowFindLoginPw(Rq rq) {
		rq.jsp("usr/member/findLoginPw");
		
	}

	private void actionDoFindLoginId(Rq rq) {
		String name = rq.getParam("name", "");
		String email = rq.getParam("email", "");
		
		if (name.length() == 0) {
			rq.historyBack("name을 입력해주세요.");
			return;
		}
		
		if (email.length() == 0) {
			rq.historyBack("email을 입력해주세요.");
			return;
		}
		
		ResultData doFindLoginIdRd = memberService.doFindLoginId(name, email);

		if (doFindLoginIdRd.isFail()) {
			rq.setAttr("message", "일치하는 회원이 없습니다.");
			rq.jsp("usr/member/findLoginId");
			return;
		}
		
		Member member = (Member) doFindLoginIdRd.getBody().get("member");
		
		rq.setAttr("message", "회원님의 아이디는 " + member.getLoginId() + " 입니다.");
		rq.jsp("usr/member/findLoginId");
		
	}

	private void actionShowFindLoginId(Rq rq) {
		rq.jsp("usr/member/findLoginId");
		
	}

	private void actionDoJoin(Rq rq) {
		String loginId = rq.getParam("loginId", "");
		String loginPw = rq.getParam("loginPw", "");
		String name = rq.getParam("name", "");
		String nickname = rq.getParam("nickname", "");
		String email = rq.getParam("email", "");
		String cellphoneNo = rq.getParam("cellphoneNo", "");

		if (loginId.length() == 0) {
			rq.historyBack("loginId를 입력해주세요.");
			return;
		}

		if (loginPw.length() == 0) {
			rq.historyBack("loginPw를 입력해주세요.");
			return;
		}
		
		if (name.length() == 0) {
			rq.historyBack("name을 입력해주세요.");
			return;
		}
		
		if (nickname.length() == 0) {
			rq.historyBack("nickname을 입력해주세요.");
			return;
		}
		
		if (email.length() == 0) {
			rq.historyBack("email을 입력해주세요.");
			return;
		}
		
		if (cellphoneNo.length() == 0) {
			rq.historyBack("cellphoneNo를 입력해주세요.");
			return;
		}

		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, email, cellphoneNo);

		if (joinRd.isFail()) {
			rq.historyBack(joinRd.getMsg());
			return;
		}
		
		rq.replace(joinRd.getMsg(), "../../");
	}

	private void actionShowJoin(Rq rq) {
		rq.jsp("usr/member/join");
	}

	private void actionDoLogout(Rq rq) {
		rq.removeSessionAttr("loginedMemberJson");
		rq.replace(null, "../../");
	}

	private void actionDoLogin(Rq rq) {
		String loginId = rq.getParam("loginId", "");
		String loginPw = rq.getParam("loginPw", "");
		String redirectUri = rq.getParam("redirectUri", "../../");

		if (loginId.length() == 0) {
			rq.historyBack("loginId를 입력해주세요.");
			return;
		}

		if (loginPw.length() == 0) {
			rq.historyBack("loginPw를 입력해주세요.");
			return;
		}

		ResultData loginRd = memberService.login(loginId, loginPw);

		if (loginRd.isFail()) {
			rq.historyBack(loginRd.getMsg());
			return;
		}
		
		Member member = (Member) loginRd.getBody().get("member");

		rq.setSessionAttr("loginedMemberJson", Ut.toJson(member, ""));
		
		rq.replace(loginRd.getMsg(), redirectUri);
		
	}

	private void actionShowLogin(Rq rq) {
		
//		String referer = rq.getHeader();
//		rq.setAttr("referer", referer);
		
		rq.jsp("usr/member/login");
	}
}
