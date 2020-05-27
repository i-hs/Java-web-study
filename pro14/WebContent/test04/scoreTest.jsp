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
<title>시험 점수 입력 페이지</title>
</head>
<body>
	<h1>시험 점수를 입력하세요.</h1>
	<form method=get action="scoreResult2.jsp">
		시험 점수: <input type=text name="score" /> <br>
		<input type="submit" value="학점 변환">
	</form>
</body>
</html>