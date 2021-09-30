<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>플레이리스트 수정</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
	.btn-primary{margin:0; padding:0;}
	#center{width:800px; margin-left: auto; margin-right: auto;}
	table{width: 800px; border-collapse : collapse;}
	#keywordInput{
 border-radius: 10px;
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
<div id="center">
<form action="/update" method="post">
	<input type="hidden" name="playlistId" value="${plist.playlistId}">
	<input type="hidden" name="memberId" value="${plist.memberId}">
	<input type="hidden" name="viewCount" value="${plist.viewCount}">
	<input type="hidden" name="likeCount" value="${plist.likeCount}">
	<table>
		<tr><td>Playlist 제목</td><td><textarea name="playlistName" cols="50" rows="1">${plist.playlistName}</textarea></td></tr>
		<tr><td>Playlist 설명</td><td><textarea name="description" cols="50" rows="1">${plist.description }</textarea></td></tr>
		<tr>
			<td><a href="/getPlaylist?playlistId=${plist.playlistId}">글 목록</a></td>
			<td><a href="/deletePlaylist/${plist.playlistId}" >삭제</a>
		    	<input type="submit" value="수정"></td>
		</tr>
	</table>
</form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
	$(function(){
		$('.menu_div').load("/menu");
	});
</script>
</body>
</html>