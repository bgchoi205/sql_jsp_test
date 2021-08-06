<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../part/head.jspf"%>

<section
	class="section section-member-join flex-grow">
	<div class="px-2">
		<div class="card bordered shadow-lg  max-w-lg mx-auto">
			<div class="card-title">
				<a href="javascript:history.back();" class="cursor-pointer"> <i
					class="fas fa-chevron-left"></i>
				</a> <span>마이페이지</span>
			</div>

			<div class="px-2 py-4">
				<table class="memberInfoTable table w-full table-zebra">
				  <tbody>
				    <tr>
				      <th>아이디</th> 
				      <td>${loginedMember.loginId}</td>
				    </tr>
				    <tr>
				      <th>이름</th> 
				      <td>${loginedMember.name}</td> 
				    </tr>
				    <tr>
				      <th>닉네임</th> 
				      <td>${loginedMember.nickname}</td> 
				    </tr>
				    <tr>
				      <th>이메일</th> 
				      <td>${loginedMember.email}</td> 
				    </tr>
				    <tr>
				      <th>전화번호</th> 
				      <td>${loginedMember.cellphoneNo}</td> 
				    </tr>
				  </tbody>
				</table>
			</div>
			<div class="px-6 mb-4">
				<a href="../member/modifyMemberInfo" class="link link-hover link-primary">정보수정</a>
				<a href="../member/modifyMemberPw" class="link link-hover link-primary ml-6">비밀번호 변경</a>
			</div>
		</div>
	</div>
</section>
<%@ include file="../part/foot.jspf"%>