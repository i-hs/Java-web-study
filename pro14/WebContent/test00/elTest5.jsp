<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"
    isELIgnored="false"
%>
	<jsp:useBean id="m1" class="sec01.ex01.MemberBean" scope="page" />
	<jsp:setProperty  name="m1"  property="name" value="이순신"/>
	<jsp:useBean id="m2" class="java.util.ArrayList" scope="page"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어?의 empty 연산자  </title>
</head>
<body>
	<h1>
	\${10+10} : ${10+10}<br>
	\${20-10} : ${20-10}<br>
	\${10*10} : ${10*10}<br>
	\${100/9} : ${100/9}<br>
	\${100 div 9} : ${100 div 9}<br>
	\${100 mod 9} : ${100 mod 9}<br>
	\${100%9} : ${100%9}<br>
	
	</h1>

</body>
</html>