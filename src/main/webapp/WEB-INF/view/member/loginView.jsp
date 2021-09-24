<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Ople : 로그인</title>
<style type="text/css">
	.img{text-align: center;}
	img{width: 100px;}
	h4{text-align: center;}
	a{text-decoration: none; color: gray;}
	div{font-size: 5px; }
	#table1{text-align: center; margin: auto;}
	
}
	
</style>
</head>
<body>
<br>
<div class="img">
<img src="/img/logo/logo.png">
</div>
<br><br>
<h4>로그인하고 당신만의 리스트를 확인하세요!</h4>

<form action="login" method="post">
<table id="table1">
<tr><td><c:if test="${loginFailed}">
	<div>아이디 또는 비밀번호가 일치하지 않습니다</div>
</c:if></td></tr>
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
		<div><a href="/pwFindView">비밀번호찾기</a>&emsp;&emsp;&emsp;&emsp;<a href="/joinView">회원 가입</a></div>
	</td>
</tr>
</table>
</form>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>