<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="메일발송" />
<%@ include file="../part/head.jspf"%>

<section class="section section-member-login flex flex-grow justify-center items-center">
	<div class="w-full max-w-md px-2">
		<div class="card bordered shadow-lg">
			<div class="card-title">
				<a href="javascript:history.back();" class="cursor-pointer">
					<i class="fas fa-chevron-left"></i>
				</a>
				<span>메일발송</span>
			</div>

			<div class="px-2 py-4">
				<script>
					let MailSend__submitDone = false;
					function MailSend__submit(form) {
						if (MailSend__submitDone) {
							return;
						}

						if (form.emailAddress.value.length == 0) {
							alert('받으실분 이메일 주소를 입력해주세요.');
							form.emailAddress.focus();

							return;
						}

						if (form.emailTitle.value.length == 0) {
							alert('이메일 제목을 입력해주세요.');
							form.emailTitle.focus();

							return;
						}
						
						if (form.emailBody.value.length == 0) {
							alert('이메일 내용을 입력해주세요.');
							form.emailBody.focus();

							return;
						}

						form.submit();
						MailSend__submitDone = true;
					}
				</script>
				<form action="../mail/doSend" method="POST"
					onsubmit="MailSend__submit(this); return false;">
					<div class="form-control">
						<label class="label">
							<span class="label-text">받을 분 이메일주소 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" 
								name="emailAddress" type="email" placeholder="이메일 주소를 입력해주세요."/>
						</div>
					</div>

					<div class="form-control">
						<label class="label">
							<span class="label-text">이메일 제목 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="emailTitle" type="text" placeholder="이메일 제목을 입력해주세요." />
						</div>
					</div>
					
					<div class="form-control">
						<label class="label">
							<span class="label-text">이메일 내용 입력</span>
						</label>
						<div>
							<textarea name="emailBody" class="textarea h-24 textarea-bordered min-w-full" placeholder="이메일 내용입력"></textarea>
						</div>
					</div>

					<div class="btns">
						<button type="submit" class="btn btn-link">메일 발송</button>
						<a href="javascript:history.back();" class="btn btn-link">
							취소
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<%@ include file="../part/foot.jspf"%>