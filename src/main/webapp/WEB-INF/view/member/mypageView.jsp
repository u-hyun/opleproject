<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Ople : 마이페이지</title>
<style type="text/css">
.box {
    width: 150px;
    height: 150px; 
    border-radius: 30%;
    overflow: hidden;
}
.profileImage {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

</style>
</head>
<body>
	
	
<form action="/updateForm" method="post" id="mypageForm" enctype="multipart/form-data">
<div class="box">
<img class="profileImage" width="100" height="100" src="${member.imagePath }" alt="profile image">
</div>
<input type="file" name="pImage">

<table id="table3">
	<tr><td>아이디: &emsp;</td><td>${member.memberId}</td></tr>
	<tr><td>이&emsp;름: &emsp;</td><td>${member.memberName}</td></tr>
	<tr><td>가입일: &emsp;</td><td><fmt:formatDate value="${member.joinDate}" pattern="yyyy-MM-dd" /></td></tr>
	<tr><td>프로필</td></tr>
	<tr><td colspan="2"><textarea  rows="5" cols="30" name="profileComment" id="profileComment">${member.profileComment}</textarea></td><td></td></tr>
	<tr><td><input id="delete" type="button" value="회원탈퇴" onclick="location.href='deleteMemberView'" ></td></tr>
</table>
<h4>마이페이지</h4>


<table id="table1">
<tr><td>닉네임: </td><td><input name="memberNickname" id="memberNickname" value="${member.memberNickname}"></td></tr>
<tr><td>생&emsp;일: </td><td><fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd" /></td></tr>

</table>
<br><br>

<table id="table2">  
<tr><td>관심있는 장르: </td>
	<td>&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="rock" value="rock" >rock&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="electronic" value="electronic">electronic&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="pop" value="pop">pop&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="funk" value="funk">funk
	</td></tr>
	<tr><td></td>
	<td>&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="metal" value="metal">metal&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="jazz" value="jazz">jazz&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="hip-hop" value="hip-hop">hip-hop&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="classical" value="classical">classical
	</td></tr>
		
	<tr><td></td>
	<td>&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="blues" value="blues">blues&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="acoustic" value="acoustic">acoustic&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="instrumental" value="instrumental">instrumental&emsp;
		<input type="checkbox" class="tagBox" name="tagBox" id="soundtrack" value="soundtrack">soundtrack
	</td></tr>
</table>

<br><br>
<br><br><br><br><input type="submit" value="변경"><input type="button" value="취소" onclick="history.back()">
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$(function(){
	<c:forEach items='${tagsList}' var='tagname'>
	$(".tagBox[value='${tagname}']").attr('checked', 'checked');
	</c:forEach>
});



</script>
</body>
</html>


