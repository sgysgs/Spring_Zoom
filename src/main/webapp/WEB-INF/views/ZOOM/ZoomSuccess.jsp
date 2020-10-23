<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

</script>
</head>
<body>
	<h3>회의 리스트</h3>
 	 <table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>회의 주제</td>
			<td>회의 ID</td>
			<td>회의시작 시간</td>
			<td>회의진행 시간</td>
			<td>회의삭제</td>
		</tr>
		<c:forEach items="${dtos}" var="meeting">
		<tr>
			<td>
			<a href="${meeting.join_url}" target="_blank">${meeting.topic}</a></td>
			<td>${meeting.meetingid}</td>
			<td>${meeting.start_time}</td>
			<td>${meeting.duration}분</td>
			<td><a href="/DeleteMeeting?meetingid=${meeting.meetingid}&uuid=${meeting.uuid}">삭제하기</a></td>
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