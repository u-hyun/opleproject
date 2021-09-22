<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>목록보기</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
	.btn-primary{margin:0; padding:0;}
	#center{width:800px; margin-left: auto; margin-right: auto;}
	table{width: 800px; border-collapse : collapse;}
	th{ background-color: orange; width: 150px;}
	a{margin: 10px auto;}
	#page{text-align: center;}
</style>
</head>
<body>
<div class="menu_div" style="float:left; width: 200px;">
</div>
<div class="collapse" id="searchbar_div" style="height:15%;">
	<form id="searchForm" action="searchResult" method="GET">
		<input id="keywordInput" name="keyword" size="50">  <input type="submit" value="검색">
	</form>
</div>
<br>
<br>
<br>
<br>
<div id="playlistinfo"></div>
<h5>playlist info</h5>
<div id="center">
<table>
	<tr><td>${plist.playlistName}</td>
		<td>${plist.description}</td>
		<td>${plist.viewCount}</td>
		<td>${plist.likeCount}</td>
	</tr>	
</table>
</div>
<h5>playlistTrack</h5>
<div id="center">
<table>
	<c:forEach items= "${ptrack}" var="track">
	<tr><td>${track.trackName}</td>
		<td>${track.artistName}</td>
		<td>${track.albumName}</td>
		<td>${track.trackId}</td>
	</tr>
	</c:forEach>	
</table>
</div>
<h5>Comment</h5>
<div id="center">
<form method="post" action="insertBoard">
	<table>
		<td><textarea name="content" cols="90" rows="3"></textarea></td>
		<td><input type="submit" value="등록"></td>
	</table>
</form>
	<table>
		<c:forEach items= "${blist}" var="board">
		<tr><td>${board.memberId}</td>
			<td>${board.content}</td>
			<td><fmt:formatDate value="${board.commentDate}" pattern="yyyy.MM.dd HH:mm"/></td>
			<td>${board.likeCount}</td>
			<c:if test="${board.memberId eq member.memberId}">
			<td><a href="/content/${board.commentId}" >수정</a></td>
			<td><a href="/delete/${board.commentId}" >삭제</a></td>
			</c:if>
		</tr>
		</c:forEach>	
	</table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
$(function(){
	$('.menu_div').load("menu");
	
	/* $("#searchForm").on('submit', function(e){
		e.preventDefault();
		$('.main').load("searchResult?keyword=" + $('#keywordInput').val());
	}); */
	
	
	$('.addplaylistbutton').click(function(){
		$('#MoaModal .modal-content').load("addPlaylistModal?id=" + $(this).attr('id'));
		$('#MoaModal').modal('show');
	});
});
</script>

<!-- 모달 -->
<div class="modal fade" id="MoaModal" tabindex="-1" role="dialog" aria-labelledby="historyModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
    </div>
  </div>
</div>
</body>
</html>