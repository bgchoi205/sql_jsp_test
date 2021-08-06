<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원가입" />
<%@ include file="../part/head.jspf"%>

<section
	class="section section-member-join flex-grow">
	<div class="px-2">
		<div class="card bordered shadow-lg  max-w-lg mx-auto">
			<div class="card-title">
				<a href="javascript:history.back();" class="cursor-pointer"> <i
					class="fas fa-chevron-left"></i>
				</a> <span>회원가입</span>
			</div>

			<div class="px-2 py-4">
				<script>
					let MemberJoin__submitDone = false;
					function MemberJoin__submit(form) {
						if (MemberJoin__submitDone) {
							return;
						}

						if (form.loginId.value.length == 0) {
							alert('아이디를 입력해주세요.');
							form.loginId.focus();

							return;
						}

						if (form.loginPw.value.length == 0) {
							alert('비밀번호를 입력해주세요.');
							form.loginPw.focus();

							return;
						}
						
						if (form.name.value.length == 0) {
							alert('이름을 입력해주세요.');
							form.name.focus();

							return;
						}
						
						if (form.nickname.value.length == 0) {
							alert('닉네임을 입력해주세요.');
							form.nickname.focus();

							return;
						}
						
						if (form.email.value.length == 0) {
							alert('이메일을 입력해주세요.');
							form.email.focus();

							return;
						}
						
						if (form.cellphoneNo.value.length == 0) {
							alert('전화번호를 입력해주세요.');
							form.cellphoneNo.focus();

							return;
						}

						form.submit();
						MemberJoin__submitDone = true;
					}
				</script>
				<script>
					function noSpaceForm(obj) { // 공백사용못하게
					    var str_space = /\s/;  // 공백체크
					    if(str_space.exec(obj.value)) { //공백 체크
					        obj.value = obj.value.replace(' ',''); // 공백제거
					        return false;
					    }
					 // input에 아래내용 추가
					 // onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"
					}
				</script>
				<form action="../member/doJoin" method="POST"
					onsubmit="MemberJoin__submit(this); return false;">
					
					<div class="form-control my-1">
					    <label class="cursor-pointer flex items-center">
					    	<input type="checkbox" name="agreementTermsOfService" class="checkbox checkbox-primary">
					    	&nbsp;
					    	<span class="label-text">이용약관 동의</span> 
					    </label>
					</div>

					<div class="form-control my-1">
					    <label class="cursor-pointer flex items-center">
					    	<input type="checkbox" name="agreementPrivacyPolicy" class="checkbox checkbox-primary">
					    	&nbsp;
					    	<span class="label-text">개인정보 취급방침 동의</span> 
					    </label>
					</div>
					
					<div class="form-control">
						<label class="label">
							<span class="label-text">사용할 아이디 입력</span>
						</label>
						<div class="flex items-center">
							<input class="input input-bordered max-w-md" maxlength="100"
								name="loginId" type="text" placeholder="아이디를 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
							<div class="idCheckBtn btn btn-link text-sm">중복확인</div>
							<div class="idCheckText text-red-500 font-bold pl-2">gg</div>
						</div>
						
					</div>

					<div class="form-control">
						<label class="label">
							<span class="label-text">사용할 비밀번호 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="loginPw" type="password" placeholder="비밀번호를 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>
					
					<div class="form-control">
						<label class="label">
							<span class="label-text">이름 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="name" type="text" placeholder="이름을 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>
					
					<div class="form-control">
						<label class="label">
							<span class="label-text">사용할 닉네임 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="nickname" type="text" placeholder="닉네임을 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>
					
					<div class="form-control">
						<label class="label">
							<span class="label-text">이메일 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="email" type="email" placeholder="이메일을 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>
					
					<div class="form-control">
						<label class="label">
							<span class="label-text">전화번호 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="cellphoneNo" type="tel" placeholder="전화번호를 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>

					<div class="btns">
						<button type="submit" class="btn btn-link">가입하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<%@ include file="../part/foot.jspf"%>