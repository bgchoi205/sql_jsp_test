<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="비밀번호 변경" />
<%@ include file="../part/head.jspf"%>

<section
	class="section section-member-join flex-grow">
	<div class="px-2">
		<div class="card bordered shadow-lg  max-w-lg mx-auto">
			<div class="card-title">
				<a href="javascript:history.back();" class="cursor-pointer"> <i
					class="fas fa-chevron-left"></i>
				</a> <span>비밀번호 변경</span>
			</div>

			<div class="px-2 py-4">
				<script>
					let ModifyMemberPw__submitDone = false;
					function ModifyMemberPw__submit(form) {
						if (ModifyMemberPw__submitDone) {
							return;
						}

						if (form.loginPw.value.length == 0) {
							alert('비밀번호를 입력해주세요.');
							form.loginPw.focus();

							return;
						}
						
						if (form.newLoginPw.value.length == 0) {
							alert('새 비밀번호를 입력해주세요.');
							form.newLoginPw.focus();

							return;
						}
						
						if (form.checkLoginPw.value.length == 0) {
							alert('비밀번호를 확인해주세요.');
							form.checkLoginPw.focus();

							return;
						}

						form.submit();
						ModifyMemberPw__submitDone = true;
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
				<form action="../member/doModifyMemberPw" method="POST"
					onsubmit="ModifyMemberPw__submit(this); return false;">
					
					<div class="form-control">
						<label class="label">
							<span class="label-text">비밀번호 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="loginPw" type="password" placeholder="비밀번호를 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>
					
					<div class="form-control">
						<label class="label">
							<span class="label-text">새 비밀번호 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="newLoginPw" type="password" placeholder="새 비밀번호를 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>
					
					<div class="form-control">
						<label class="label">
							<span class="label-text">비밀번호 확인</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="checkLoginPw" type="password" placeholder="새 비밀번호를 다시 입력해주세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
						</div>
					</div>

					<div class="btns">
						<button type="submit" class="btn btn-link">변경하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<%@ include file="../part/foot.jspf"%>