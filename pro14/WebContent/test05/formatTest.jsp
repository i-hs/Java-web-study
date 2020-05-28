<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포메팅 라이브러리</title>
</head>
<body>
	<h2> fmt의 number태그를 활용한 숫자 포멧팅 예제</h2>
	<c:set var="price" value="100000000"/>
	<fmt:formatNumber value="${price }" type="number" var="priceNumber" />
	통화로 표현 시: 
	<fmt:formatNumber value="${price }" currencySymbol="₩" type="currency" groupingUsed="true"/><br>
	퍼센트로 표현 시: 
	<fmt:formatNumber value="${price }" currencySymbol="₩" type="percent" groupingUsed="false"/><br>
	일반 숫자로 표현 시: ${priceNumber }<br>
	
	<h2>formatDate 예제 </h2>
	<c:set var="now" value="<%=new Date() %>"/>
	<fmt:formatDate value="${now }" type="date" dateStyle="full" /> <br>
	<fmt:formatDate value="${now }" type="date" dateStyle="short" /> <br>
	<fmt:formatDate value="${now }" type="time" /> <br>
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full" /><br>
	<fmt:formatDate value="${now }" pattern="YYYY-MM-dd :hh:mm:ss" /><br>
	
	<br><br>
	한국 현재 시간: 
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br><br>
	
	<fmt:timeZone value="America/New York">
	뉴욕 현재 시간:
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full" /><br>
	</fmt:timeZone>
</body>
</html>