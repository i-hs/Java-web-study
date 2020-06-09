<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.*, sec03.brd08.*"%>

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
	text-decoreation:none;
	}
	.cls2{
	font-size:30px;
	text-align:center;
	}
</style>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>글 쓰기 창</title>

<script type="text/javascript">
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj){
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}
</script>
</head>
<body>
	<h1 style="text-align:'center'">새글 쓰기</h1>
	<form name = "articleForm" method="post"
	action = "${contextPath }/board/addArticle.do"
	enctype="multipart/form-data">
	
	<table border="0" align="center">
		<tr>
			<td align = "right">글제목: </td>
			<td colspan = "2"><input type = "text" size = "67" maxlength="500" name="title"/></td>
		</tr>
		<tr>
			<td align = "right" valign="top"><br>글내용: </td>
			<td colspan = "2"><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea>
			</td>
		</tr>
		<tr>
			<td align="right">이미지 파일 첨부:</td>
			<td> <input type="file" name="imageFileName" onchange="readURL(this);"/></td>
			<td><img id="preview" src="#" width=200 height=200 /></td>
		</tr>
		<tr>
			<td align = "right"></td>
			<td colspan="2">
				<input type = "submit" value = "글쓰기"/>
				<input type = "button" value = "목록보기" onClick = "backToList(this.form)" />
			</td>
		</tr>
	</table>
	</form>
</body>
</html>