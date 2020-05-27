<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여러 가지 비교 연산자</title>
</head>
<body>
<h1>
	\${10==10} : ${10==10}  <br>
	\${10 eq 10} : ${10 eq 10}<br><br>
	\${"hello"=="hello"} : ${"hello"=="hello"}<br>
	\${"hello" eq "hello"} : ${"hello" eq "hello"}<br><br>

	\${20!=10} : ${20!=10}  <br>
	\${20 ne 10} : ${20 ne 10}<br><br>
	
	\${"hello" != "apple"} : ${"hello" != "apple"}<br> 
	\${"hello" ne "apple"} : ${"hello" ne "apple"} <br><br>
	
</h1>
</body>
</html>