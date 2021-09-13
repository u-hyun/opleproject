<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Ople : 마이페이지</title>
<style type="text/css">

</style>
</head>
<body>
<!-- 여기서는 사진 업로드/변경만 가능하게 해야한다 -->
<div style="float:right;">
	<img width="100" height="100" src="${member.imagePath }" alt="profile image">
<table id="table3">
	<tr><td>아이디: &emsp;</td><td>${member.memberId}</td></tr>
	<tr><td>이&emsp;름: &emsp;</td><td>${member.memberName}</td></tr>
	<tr><td>가입일: &emsp;</td><td><fmt:formatDate value="${member.joinDate}" pattern="yyyy-MM-dd" /></td></tr>
	<tr><td>프로필</td></tr>
	<tr><td colspan="2"><textarea rows="5" cols="30" readonly="readonly" >${member.profileComment}</textarea></td><td></td></tr>

</table>
</div>
<h4>마이페이지</h4>
<form action="/updateMypage" method="post" id="mypageForm">

<table id="table1">
<tr><td>닉네임: </td><td>${member.memberNickname}</td></tr>
<tr><td>생&emsp;일: </td><td><fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd" /></td></tr>

</table>
<br><br>

<table id="table2">  
<tr><td>관심있는 장르: </td>
	<td>&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="rock">rock&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="electronic">electronic&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="pop">pop&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="funk">funk
	</td></tr>
	<tr><td></td>
	<td>&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="metal">metal&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="jazz">jazz&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="hip-hop">hip-hop&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="classical">classical
	</td></tr>
		
	<tr><td></td>
	<td>&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="blues">blues&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="acoustic">acoustic&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="instrumental">instrumental&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="soundtrack">soundtrack
	</td></tr>
</table>
<br><br><br><br>
<div id="submit">
<input type="submit" value="수정"><input type="button" value="취소" onclick="history.back()">
</div>
</form>

<script type="text/javascript">

</script>
</body>
</html>



