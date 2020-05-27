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
<title>출력할 구구단의 수를 지정</title>
</head>
<body>
	<h1>출력할 구구단의 수를 지정하세요.</h1>
	<form method=get action='guguResult2.jsp'>
		출력할 구구단: <input type=text name="dan" /><br>
		<input type="submit" value="구구단 출력">
	</form>
</body>
</html>