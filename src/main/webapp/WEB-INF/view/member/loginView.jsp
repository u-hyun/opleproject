<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Ople : 로그인</title>
<style type="text/css">
	h4{text-align: center;}
	a{text-decoration: none; color: gray;}
	div{font-size: 5px; }
	#table1{text-align: center; margin: auto;}
	
}
	
</style>
</head>
<body>
<br><br><br><br>
<h4>로그인하고 당신만의 리스트를 확인하세요!</h4>
<form action="login" method="post">
<table id="table1">
<tr>
	<td><input name="memberId" type="text" size="20" placeholder="아이디"></td>
</tr>
<tr>
	<td><input name="memberPw" type="password" size="20" placeholder="비밀번호"></td>
</tr>
<tr>
	<td colspan="2" align="center"><input type="submit" value="로그인"></td>  
</tr>
<tr>
	<td>
		<div><a href="/findAccountView">아이디/비밀번호찾기</a>&emsp;&emsp;&emsp;&emsp;<a href="/joinView">회원 가입</a></div>
	</td>
</tr>
</table>
</form>
</body>
</html>