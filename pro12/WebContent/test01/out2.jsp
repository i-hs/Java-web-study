<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String age  = request.getParameter("age");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Out2</title>
</head>
<body>
	<%
		if(name != null || name.length() !=0){
	%>
		<h1><%=name %>님의 나이는  <%=age %>입니다.</h1><br>
	<%
		}else{
	%>
		<h1>이름을 입력하세요.</h1>
	<%
		}
	%>
		<h2>== out 객체 이용 ==</h2>
	<%
		if(name != null || name.length() !=0){
	%>
		<h1><%out.println(name+"님의 나이는 "+age+"입니다.") ;%></h1>
	<%
		}else{
	%>
		<h1>이름을 입력하세요.</h1>
	<%
		}
	%>

</body>
</html>