<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Ople : 로그인</title>
</head>
<body>
<h1>로그인하고 당신만의 리스트를 확인하세요!</h1>
<form action="login" method="post">
<table align="center" cellpadding="0" cellspacint="0">
<tr>
	<td><input name="id" type="text" size="10" placeholder="아이디"></td>
</tr>
<tr>
	<td><input name="password" type="password" size="10" placeholder="비밀번호"></td>
</tr>
<tr>
	<td colspan="2" align="center"><input type="submit" value="로그인"></td>  
</tr>
<tr>
	<td>
		<a href="/findAccountView">아이디/비밀번호찾기</a><a href="/joinView">회원 가입</a>
	</td>
</tr>
</table>
</form>
</body>
</html>