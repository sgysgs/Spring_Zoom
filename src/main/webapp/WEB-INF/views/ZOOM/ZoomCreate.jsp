<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

function doOpenCheck(chk){
    var obj = document.getElementsByName("type");
    for(var i=0; i<obj.length; i++){
        if(obj[i] != chk){
            obj[i].checked = false;
        }
    }
}
</script>
<body>

<h3>회의 추가완료</h3>
<table width="700" height="300" cellpadding="0" cellspacing="0" border="1">

		<form action="/create/redirect" method="post">
			<tr>
				<td> 회의 주제 </td>
				<td> <input type="text" name="topic" id="topic" size = "50"> </td>
			</tr>
			<tr>
			<c:set var="type"/>
				<td> 회의 날짜</td>
				<td> 
				<label><input type="checkbox" name="type" id="type" value="1" onclick="doOpenCheck(this);"> 즉석회의 </label>
     		 	<label><input type="checkbox" name="type" id="type" value="2" onclick="doOpenCheck(this);"> 예약회의 </label><br>
     			
     			예약회의일 경우에만 작성
				<input type="text" name="start_time"  id="start_time" size = "50" placeholder="yyyy-mm-ddTHH:mm:ss"> 
				</td>
			</tr>
			<tr>
				<td> 회의시간</td>
				<td> <input type="text" name="duration"  id="duration" size = "50" placeholder="분단위">  </td>
			</tr>
			<tr>
				<td> 회의방 비밀번호</td>
				<td> <input type="text" name="password"  id="password" size = "50" placeholder="특수문자가능">  </td>
			</tr>
			<tr>
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; <a href="/zoom">목록으로</a></td>
			</tr>
		</form>
	</table> 
	
</body>
</html>
