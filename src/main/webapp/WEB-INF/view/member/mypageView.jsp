<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Ople : 마이페이지</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">

#keywordInput{
 border-radius: 10px;
 }
 
.box {
    width: 150px;
    height: 150px; 
    border-radius: 30%;
    overflow: hidden;
    display: inline-block;
}
.profileImage {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.left{
width: 40%;
margin: 0;
float: left;
position: relative;
}

.right{
width: 40%;
float: right;
position: relative;

 }

.foot{
text-align: center;
clear: both;

}


 .family{
width: 70%;
overflow: hidden;
}

/* 회원탈퇴 버튼 위치 조절  */
#delete{
}



</style>
</head>
<body>
<div class="menu_div" style="float:left; width: 200px;">
</div>
<div class="collapse" id="searchbar_div" style="height:15%;">
	<form id="searchForm" action="searchResult" method="GET">
	<input id="keywordInput" name="keyword" style="width:50%">  <input type="image" src="/img/icon/search_outline.png" height="35px">
	</form>
</div>
<br><br><h4>마이페이지</h4>	
<div class="family">
<form action="/updateForm" method="post" id="mypageForm" enctype="multipart/form-data">
<br><br>

<div class="left">
<table id="table1">
<tr><td>닉네임: &emsp;</td><td><input name="memberNickname" id="memberNickname" value="${member.memberNickname}"></td></tr>
<tr><td>생&emsp;일: &emsp;</td><td><fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd" /></td></tr>
</table>
<br><br>
<table id="table2">  
<tr><td>관심있는 장르</td></tr>
		<tr>
		<td>
		<input type="checkbox" class="tagBox" name="likedTags" value="rock">rock&emsp;</td>
		<td><input type="checkbox" class="tagBox" name="likedTags" value="electronic">electronic&emsp;</td>
		<td><input type="checkbox" class="tagBox" name="likedTags" value="pop">pop&emsp;</td>
		<td><input type="checkbox" class="tagBox" name="likedTags" value="funk">funk</td>
		</tr>
		
		<tr>
		<td>
		<input type="checkbox" class="tagBox" name="likedTags" value="metal">metal&emsp;</td>
		<td><input type="checkbox" class="tagBox" name="likedTags" value="jazz">jazz&emsp;</td>
		<td><input type="checkbox" class="tagBox" name="likedTags" value="hip-hop">hip-hop&emsp;</td>
		<td><input type="checkbox" class="tagBox" name="likedTags" value="classical">classical</td>
		</tr>
		
		<tr>
		<td>
		<input type="checkbox" class="tagBox" name="likedTags" value="blues">blues&emsp;</td>
		<td><input type="checkbox" class="tagBox" name="likedTags" value="acoustic">acoustic&emsp;</td>
		<td><input type="checkbox" class="tagBox" name="likedTags" value="instrumental">instrumental&emsp;</td>
		<td><input type="checkbox" class="tagBox" name="likedTags" value="soundtrack">soundtrack</td>
		</tr>
</table>
</div>

<div class="right">
<div class="box">
<img class="profileImage" width="100" height="100" src="${member.imagePath }" alt="profile image">
</div>
<br><input type="file" name="pImage">

<table id="table3">
	<tr><td>아이디: &emsp;</td><td>${member.memberId}</td></tr>
	<tr><td>이&emsp;름: &emsp;</td><td>${member.memberName}</td></tr>
	<tr><td>가입일: &emsp;</td><td><fmt:formatDate value="${member.joinDate}" pattern="yyyy-MM-dd" /></td></tr>
	<tr><td>프로필</td></tr>
	<tr><td colspan="2"><textarea  rows="5" cols="30" name="profileComment" id="profileComment">${member.profileComment}</textarea></td><td></td></tr>
	<tr><td><input id="delete" type="button" value="회원탈퇴" onclick="location.href='deleteMemberView'" ></td></tr>
</table>
</div>
</form>
<div class="foot"><br><br><br><br><hr><input type="submit" value="변경">&emsp;<input type="button" value="취소" onclick="history.back()"></div>
</div>




<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script type="text/javascript">

$(function(){
	<c:forEach items='${tagsList}' var='tagname'>
	$(".tagBox[value='${tagname}']").attr('checked', 'checked');
	</c:forEach>
});

$(function(){
	$('.menu_div').load("menu");
});



</script>
</body>
</html>


