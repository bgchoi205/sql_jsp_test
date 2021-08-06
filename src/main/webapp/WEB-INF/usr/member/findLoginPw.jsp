<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="비밀번호찾기" />
<%@ include file="../part/head.jspf"%>

<section class="section section-member-login flex flex-grow justify-center items-center">
	<div class="w-full max-w-md px-2">
		<div class="card bordered shadow-lg">
			<div class="card-title">
				<a href="javascript:history.back();" class="cursor-pointer">
					<i class="fas fa-chevron-left"></i>
				</a>
				<span>비밀번호 찾기</span>
			</div>

			<div class="px-2 py-4">
				<script>
					let MemberFindPw__submitDone = false;
					function MemberFindPw__submit(form) {
						if (MemberFindPw__submitDone) {
							return;
						}

						form.name.value = form.name.value.trim();
						
						if (form.name.value.length == 0) {
							alert('이름을 입력해주세요.');
							form.name.focus();

							return;
						}

						form.email.value.trim();
						
						if (form.email.value.length == 0) {
							alert('이메일 입력해주세요.');
							form.email.focus();

							return;
						}

						form.submit();
						MemberFindPw__submitDone = true;
					}
				</script>
				<form action="../member/doFindLoginPw" method="POST"
					onsubmit="MemberFindPw__submit(this); return false;">
					<div class="form-control">
						<label class="label">
							<span class="label-text">아이디 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="loginId" type="text" placeholder="아이디를 입력해주세요." autofocus/>
						</div>
					</div>

					<div class="form-control">
						<label class="label">
							<span class="label-text">이메일 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="email" type="text" placeholder="이메일을 입력해주세요." />
						</div>
					</div>

					<div class="btns">
						<button type="submit" class="btn btn-link">비밀번호 찾기</button>
						<a href="../member/login" class="btn btn-link">
							취소
						</a>
					</div>
				</form>
				<c:if test="${message != null}">
					<div class="text-red-500 flex justify-center">
						<span>${message}</span>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</section>
<%@ include file="../part/foot.jspf"%>