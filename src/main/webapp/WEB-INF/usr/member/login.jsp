<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="로그인" />
<%@ include file="../part/head.jspf"%>

<section class="section section-member-login flex flex-grow justify-center items-center">
	<div class="w-full max-w-md px-2">
		<div class="card bordered shadow-lg">
			<div class="card-title">
				<a href="javascript:history.back();" class="cursor-pointer">
					<i class="fas fa-chevron-left"></i>
				</a>
				<span>로그인</span>
			</div>

			<div class="px-2 py-4">
				<script>
					let MemberLogin__submitDone = false;
					function MemberLogin__submit(form) {
						if (MemberLogin__submitDone) {
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

						form.submit();
						MemberLogin__submitDone = true;
					}
				</script>
				<script>
					function noSpaceForm(obj) {
					    var str_space = /\s/;  // 공백체크
					    if(str_space.exec(obj.value)) { //공백 체크
					        obj.value = obj.value.replace(' ',''); // 공백제거
					        return false;
					    }
					 // onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"
					}
				</script>
				<form action="../member/doLogin" method="POST"
					onsubmit="MemberLogin__submit(this); return false;">
					<input type="hidden" name="redirectUri" value="${param.afterLoginUri}" />
					<div class="form-control">
						<label class="label">
							<span class="label-text">로그인아이디</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="loginId" type="text" placeholder="로그인아이디를 입력해주세요." autofocus onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>

					<div class="form-control">
						<label class="label">
							<span class="label-text">로그인비밀번호</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="loginPw" type="password" placeholder="로그인비밀번호를 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>

					<div class="btns">
						<button type="submit" class="btn btn-link">로그인</button>
					</div>
				</form>
				<div>
					<a href="../member/join" class="ml-2">
						<span>
							<button class="btn btn-link btn-xs">회원가입</button>
						</span>
					</a>
					<a href="../member/findLoginId">
						<span>
							<button class="btn btn-link btn-xs">아이디 찾기</button>
						</span>
					</a>
					<a href="../member/findLoginPw">
						<span>
							<button class="btn btn-link btn-xs">비밀번호 찾기</button>
						</span>
					</a>
				</div>
			</div>
		</div>
	</div>
</section>
<%@ include file="../part/foot.jspf"%>