<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>

<h3>나의 회의 초대장</h3>
	<table border="1">
		<tr>
			<td>제목</td>
			<td>내용</td>
		</tr>
		<c:forEach items="${result}" var="meeting" varStatus="i">
		<tr>
			<td>진행중인 '${meeting.TOPIC}'회의에 참석하세요.</td> 
			<td>
			- 회의에 참가하는 방법<br>
			&nbsp;&nbsp;1. 아래 URL클릭 후 패스워드 입력하여 참가하기<br>
			&nbsp;&nbsp;&nbsp;&nbsp;회의 참가 URL: <a href="${meeting.JOINURL}"> ${meeting.JOINURL} </a><br>
			&nbsp;&nbsp;&nbsp;&nbsp;회의 비밀번호: ${meeting.JOINPW}<br>
			<br>
			&nbsp;&nbsp;2. ZOOM 홈페이지 에서 아래 아이대와 패스워드 입력하여 참가하기 -> <a href="https://zoom.us/">ZOOM 홈페이지 클릭</a><br>
			&nbsp;&nbsp;&nbsp;&nbsp;회의 아이디: ${meeting.JOINID} <br>
			&nbsp;&nbsp;&nbsp;&nbsp;회의 비밀번호: ${meeting.JOINPW}<br>
			<br>
			- 회의정보<br>
			&nbsp;&nbsp;&nbsp;&nbsp;회의 시작시간: ${meeting.STARTTIME}<br>
			&nbsp;&nbsp;&nbsp;&nbsp;회의 진행시간: ${meeting.DURATION}분<br>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> 
			<a href="/zoom">목록으로</a> 
			</td>
		</tr> 
	</table> 
</body>
</html>