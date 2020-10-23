<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>${topic} 회의 초대</h3> <br>

	내가 관리중인 유저
	<table width="1000" border="1">
		<tr>
			<td>유저 ID</td>
			<td>유저 이름</td>
			<td>초대 하기</td>
		</tr>
		<c:forEach items="${dtos}" var="user" varStatus="i">
		<tr>
			<td>${user.ID}</td>
			<td>${user.NAME}</td>
			<td><a href="/Invitation/Meeting?userid=${user.ID}">회의 초대</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> 
			<a href="/list/meetings">회의 리스트</a> 
			<a href="/Invitation/All">전체 초대하기</a> 
			</td>
		</tr> 
	</table> 
</body>
</html>