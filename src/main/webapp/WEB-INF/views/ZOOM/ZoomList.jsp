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
<h3>회의 리스트</h3>
 	 <table width="1000" border="1">
		<tr>
			<td>회의 주제</td>
			<td>회의 ID</td>
			<td>회의시작시간</td>
			<td>회의진행시간</td>
			<td>참여자 목록</td>
			<td>회의 초대하기</td>
			<td>회의삭제</td>
		</tr>
		<c:forEach items="${dtos}" var="meeting" varStatus="i">
		<tr>
			<td>
			<a href="${meeting.JOIN_URL}" target="_blank">${meeting.TOPIC}</a></td>
			<td>${meeting.MEETINGID}</td>
			<td>${meeting.START_TIME}</td>
			<td>${meeting.DURATION}분</td>
			<td><a href="/participants?uuid=${meeting.UUID}">참여자 목록보기</a></td>
			<td><a href="/Invitation?uuid=${meeting.UUID}&topic=${meeting.TOPIC}">초대하기</a></td>
			<td><a href="/DeleteMeeting?meetingid=${meeting.MEETINGID}&uuid=${meeting.UUID}">삭제하기</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> 
			<a href="/create/meetings">회의 추가</a> 
			<a href="/zoom">목록으로</a> 
			</td>
		</tr> 
	</table> 
</body>
</html>