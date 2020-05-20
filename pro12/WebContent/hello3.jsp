<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String name = "이순신";
	public String getName(){return name;}
%> 
<% String age = request.getParameter("age"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현식 연습</title>
	<h1>이름은 <%=name %>입니다.</h1>
	<h1>나이는 <%=age %>살 입니다.</h1>
	<h1>키는 <%=180 %>cm 입니다.</h1>
	<h1>나이+10은 <%=Integer.parseInt(age)+10 %>살 입니다.</h1>
</head>
<body>

</body>
</html>