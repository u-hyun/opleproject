<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>목록보기</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
.btn-primary{
	margin:0;
	padding:0;
}
#keywordInput{
 border-radius: 10px;
 }
 
 .card-text{
font-size: 10px;
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
<br>
<br>
<h3> My 플레이리스트</h3>
<div class="card-deck">
<c:forEach items="${playlists}" var="playlist">
	<div class="card songcard" id="${playlist.playlistId}">
	   <img src="/img/icon/cd.png" class="card-img-top playlistcover" alt="...">	   
	   <div class="card-body">
	     <h5 class="card-title">${playlist.playlistName}</h5>
	     <p class="card-text">${playlist.description}</p>
	     <p class="card-text"><small class="text-muted">${playlist.member.memberNickname}</small></p>
	     <a href="/getPlaylist?playlistId=${playlist.playlistId}" class="btn playlistDetailsButton" id="${playlist.playlistId}"><img src="/img/icon/menu.png" alt="자세히보기" height="20px"> </a>
	     <a href="#" class="btn playlistLikeButton" id="${playlist.playlistId}"> <img src="/img/icon/thumbs_outline.png" height="20px" alt="좋아요"> </a>
	   </div>
	 </div>
</c:forEach>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
$(function(){
	$('.menu_div').load("menu");

	$('.playlistLikeButton').click(function(){
		var id = $(this).attr('id');
		var imgsrc = $(this).children('img').attr("src");
		var $img = $(this).children('img');
		$.ajax({
			type: "GET",
			url: "/likePlaylist?playlistId=" + id,
			success: function(data){	
				if(imgsrc === '/img/icon/thumbs_outline.png'){
					$img.attr('src', "/img/icon/update_button.png");
				} else {
					$img.attr('src', "/img/icon/thumbs_outline.png");
				}
			}, error: function(xhr, textStatus, errorThrown){
				alert(xhr.responseText);
			}
		});
	});
});
</script>
</body>
</html>