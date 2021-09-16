<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ople : 비밀번호 변경</title>
</head>
<body>
<h4>비밀번호 변경</h4>
<form action="/pwChange" method="post" id="changePwForm">
<table>

<tr><td><div style="display: none"><input name="memberPwOri" id="memberPwOri" type="password" value="${member.memberPw}"></div></td></tr>

<tr><td><input name="checkPw" id="checkPw" type="password" placeholder="현재 비밀번호"></td></tr>
<tr><td><input name="newPw" id="newPw" type="password" placeholder="새 비밀번호"></td></tr>
<tr><td><input name="newPw2" id="newPw2" type="password" placeholder="새 비밀번호 확인"></td></tr>

<tr>
<td align="center"><input type="submit" value="변경">&emsp;
<input type="button" value="취소" onclick="history.back()"></td>
</tr>
</table>
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$("#changePwForm").submit(function(){
		
		if($("#checkPw").val() != $("#memberPwOri").val()){
			alert("기존 비밀번호가 일치하지 않습니다.")
			return false;
		}if($("#newPw").val() != $("#newPw2").val()){
			alert("비밀번호 확인이 일치하지 않습니다");
			return false;
	}
})
})
</script>
</body>
</html>