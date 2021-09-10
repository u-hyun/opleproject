<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Ople : 마이페이지 수정</title>
<style type="text/css">

</style>
</head>
<body>

<div style="float:right;">
	<input multiple type="file" name="files" value="등록">
<table id="table3">
	<tr><td>아이디: &emsp;</td><td>${member.memberId}</td></tr>
	<tr><td>이&emsp;름: &emsp;</td><td>${member.memberName}</td></tr>
	<tr><td>가입일: &emsp;</td><td><fmt:formatDate value="${member.joinDate}" pattern="yyyy-MM-dd" /></td></tr>
	<tr><td>프로필</td></tr>
	<tr><td colspan="2"><textarea  rows="5" cols="30" name="profileComment" id="profileComment">${member.profileComment}</textarea></td><td></td></tr>

</table>
</div>
<h4>마이페이지</h4>
<form action="/updateForm" method="post" id="mypageForm">

<table id="table1">
<tr><td>닉네임: </td><td><input name="memberNickname" id="memberNickname" value="${member.memberNickname}"></td></tr>
<tr><td>생&emsp;일: </td><td><fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd" /></td></tr>
<tr><td>비밀번호: </td><td><input name="memberPassword" id="memberPassword" ></td></tr>
<tr><td>비밀번호 확인: </td><td><input name="memberPassword2" id="memberPassword2" ></td></tr>
</table>
<br><br>

<!-- likedTags 연동 필요 -->
<!-- 태그를 리스트로 만든다, 모델로 가져온다, for each로 하나씩 꺼내서 checkbox를 만들고, checked 속성 추가-->

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


<!-- <table id="table2">  
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
</table> -->
<br><br>
<%-- <div style="float:right;">
<table id="table3">
	<tr><td>아이디: &emsp;</td><td>${member.memberId}</td></tr>
	<tr><td>이&emsp;름: &emsp;</td><td>${member.memberName}</td></tr>
	<tr><td>가입일: &emsp;</td><td><fmt:formatDate value="${member.joinDate}" pattern="yyyy-MM-dd" /></td></tr>
	<tr><td>프로필</td></tr>
	<tr><td colspan="2"><textarea  rows="5" cols="30" name="profileComment" id="profileComment">${member.profileComment}</textarea></td><td></td></tr>

</table>
</div> --%>
<br><br><br><br><input type="submit" value="변경"><input type="button" value="취소" onclick="history.back()">
</form>
</body>
</html>

<!--
table 나란히 옆으로 출력하기
<table style="float:left;"></table>
<table style="float:left;"></table>
 -->


