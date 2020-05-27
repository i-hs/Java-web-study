<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, sec01.ex01.*"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
</head>
<body>
	<form method="post" action="result2.jsp">
		아이디   : <input type="text" size=20 name="userID"/> <br>
		비밀번호 : <input type="password" size=20 name="userPwd"/><br>
		<input type="submit" value="로그인" /> <input type="reset" value="다시입력" /> 
	</form>
	<br><br>
	<!-- <a href="http://localhost:8090/pro14/test01/memberForm.jsp">회원가입하기</a> -->
	<%--
	<a href="<%=request.getContextPath() %>/test01/memberForm.jsp">회원가입하기</a>
	 --%>
	 <%-- <a href="${pageContext.request.contextPath }/test01/memberForm.jsp">회원가입하기</a> --%>
</body>
</html>