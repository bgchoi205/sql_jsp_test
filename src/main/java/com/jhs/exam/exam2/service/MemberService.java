package com.jhs.exam.exam2.service;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.exam.exam2.dto.Member;
import com.jhs.exam.exam2.dto.ResultData;
import com.jhs.exam.exam2.repository.MemberRepository;

public class MemberService implements ContainerComponent {
	private MemberRepository memberRepository;
	
	public void init() {
		memberRepository = Container.memberRepository;
	}

	public ResultData login(String loginId, String loginPw) {
		Member member = memberRepository.getMemberByLoginId(loginId);

		if (member == null) {
			return ResultData.from("F-1", "존재하지 않는 회원의 로그인아이디 입니다.");
		}
		
		String temporaryPw = memberRepository.getTemporaryPw(loginId);
		
		System.out.println("임시 비밀번호 : " + temporaryPw);

		if (member.getLoginPw().equals(loginPw) == false) {
			
			if(temporaryPw != null && temporaryPw.equals(loginPw)) {
				
				memberRepository.changeLoginPwToTemporaryPw(loginId, temporaryPw);
				
				return ResultData.from("S-1", "환영합니다.", "member", member);
			}
			return ResultData.from("F-2", "비밀번호가 일치하지 않습니다.");
		}

		return ResultData.from("S-1", "환영합니다.", "member", member);
	}
	
	public ResultData join(String loginId, String loginPw, String name, String nickname, String email, String cellphoneNo) {
		Member memberByLoginId = memberRepository.getMemberByLoginId(loginId);

		if (memberByLoginId != null) {
			return ResultData.from("F-1", "이미 사용중인 아이디 입니다.");
		}

		Member memberByNameAndEmail = memberRepository.getMemberByNameAndEmail(name, email);
		
		if (memberByNameAndEmail != null) {
			
			return ResultData.from("F-2", "이미 존재하는 사용자입니다.");
		}
		
		memberRepository.join(loginId, loginPw, name, nickname, email, cellphoneNo);

		return ResultData.from("S-1", "가입완료. 로그인해주세요.");
	}

	public ResultData doFindLoginId(String name, String email) {
		Member member = memberRepository.getMemberByNameAndEmail(name, email);
		
		if (member == null) {
			
			return ResultData.from("F-1", "존재하지 않는 회원입니다.");
		}

		return ResultData.from("S-1", "가입완료. 로그인해주세요.", "member", member);
	}
	

	public ResultData doFindLoginPw(String loginId, String email) {
		Member member = memberRepository.getMemberByLoginIdAndEmail(loginId, email);
		
		if (member == null) {
			
			return ResultData.from("F-1", "존재하지 않는 회원입니다.");
		}

		return ResultData.from("S-1", "임시 비밀번호를 발송해드리겠습니다.", "member", member);
	}

	
	public Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public void setTemporaryPw(String temporaryPw, String loginId) {
		memberRepository.setTemporaryPw(temporaryPw, loginId);
	}

	public boolean isAdmin(Member loginedMember) {
		return loginedMember.getAuthLevel()  >= 7;
	}

	public void ModifyMemberInfo(String name, String nickname, String email, String cellphoneNo, int loginedMemberId) {
		memberRepository.modifyMemberInfo(name, nickname, email, cellphoneNo, loginedMemberId);
	}

	public Member getMemberById(int loginedMemberId) {
		return memberRepository.getMemberById(loginedMemberId);
	}

	public void modifyMemberPw(int loginedMemberId, String newLoginPw) {
		memberRepository.modifyMemberPw(loginedMemberId, newLoginPw);
	}

}
