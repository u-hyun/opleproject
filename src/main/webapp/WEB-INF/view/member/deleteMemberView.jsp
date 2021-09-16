<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ople : 회원탈퇴</title>
</head>
<body>
<form action="deleteMember" method="post" name="deleteForm">
<h4>회원탈퇴</h4>
<input name="memberId" id="memberId" value="${member.memberId}" readonly="readonly"><br>
<input type="password" name="memberPw" id="memberPw" placeholder="비밀번호">
<br>
<input type="submit" value="탈퇴"><input type="button" value="취소" onclick="history.back()">
</form>

</body>
</html>