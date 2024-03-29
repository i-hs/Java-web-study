<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.*, sec02.ex01.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 창</title>
</head>
<body>
	<form method="post" action="${contextPath}/member/addMember.do">
		<h1 style="text-align:center"> 회원 가입 창</h1>
		<table align="center">
			<tr>
				<td width="200">
					<p align="right">아이디</p>
				</td>
				<td width="400"><input type="text" name="id"></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">패스워드</p>
				</td>
				<td width="400"><input type="password" name="pwd"></td>
			</tr>
			 <tr>
				<td width="200">
					<p align="right">이름</p>
				</td>
				<td width="400"><input type="text" name="name"></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">이메일</p>
				</td>
				<td width="400"><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>
				</td>
				<td>
				<input type="submit" value="가입하기" >
				<input type="reset" value="다시 입력">
				</td>
			</tr>
					
		</table>
	</form>
</body>
</html>