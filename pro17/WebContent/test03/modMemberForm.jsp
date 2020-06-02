<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.*, sec01.ex01.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<style>
.cls1{
	font-size:40px;
	text-align:center;
}
</style>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>회원 정보 수정창</title>
</head>
<body>
		<h1 style="text-align:center">회원 정보 수정창</h1>
	<form method="post" action="${contextPath}/member/modMember.do?id=${memInfo.id}">
		<table align="center">
			<tr>
				<td width="200">
					<p align="right">아이디</p>
				</td>
				<td width="400"><input type="text" name="id" value="${memInfo.id}" disabled></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">패스워드</p>
				</td>
				<td width="400"><input type="password" name="pwd" value="${memInfo.pwd }"></td>
			</tr>
			 <tr>
				<td width="200">
					<p align="right">이름</p>
				</td>
				<td width="400"><input type="text" name="name" value="${memInfo.name }"></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">이메일</p>
				</td>
				<td width="400"><input type="text" name="email" value="${memInfo.email }"></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">가입일</p>
				</td>
				<td width="400"><input type="text" name="joinDate" value="${memInfo.joinDate } disabled"></td>
			</tr>

			<tr align="center">
				<td>
				<input type="submit" value="수정하기" >
				
				<input type="reset" value="다시 입력">
				</td>
			</tr>
					
		</table>
	</form>
</body>
</html>