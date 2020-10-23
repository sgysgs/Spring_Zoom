<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>관리자</h2>
	<a href="${url}">zoom유저 회의 리스트 보기</a> <br>
	<a href="/list/meetings">유저 회의 리스트 보기</a> <br>
	<a href="/create/meetings">회의 추가</a> <br>
	
	<h2>사용자</h2>
	<a href="/user/Invitation">회의 초대장 보기</a> <br>
</body>
</html>