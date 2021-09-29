<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OPLE</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
.btn-primary{
	margin:0;
	padding:0;
}

#keywordInput{
 border-radius: 10px;
 }
 
h3{
 font-size: 15px;}
 
a.playlistLikeButton, a.playlistDetailsButton{
	padding: 0;
}

.card-text{
font-size: 10px;
}

.songcard{
min-width: 150px;
max-width: 15%;
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

<div class="main" style="padding:30px">

<h3>곡 추천 ></h3>
<div class="card-deck">
<c:forEach items="${topTracks}" var="track">
	<div class="card songcard" id="${track.trackId}">
	   <img src="http://coverartarchive.org/release/${track.coverimg}" onError="this.onerror=null;this.src='https://image.flaticon.com/icons/png/512/26/26805.png';"
	    class="card-img-top albumcover" alt="...">
	   <div class="card-body">
	     <h5 class="card-title">${track.trackName}</h5>
	     <p class="card-text">${track.artistName}</p>
	     <a href="#" class="btn btn-primary addplaylistbutton" id="${track.trackId}">추가</a>
	     <c:if test="${member ne null}">
	     <a href="/editTag?trackId=${track.trackId}" class="btn btn-primary edittagbutton" id="${track.trackId}">태그 수정</a>
	     </c:if>
	   </div>
	 </div>
</c:forEach>
</div>

<hr width="87%" align="right">
<h3>플레이리스트 추천 ></h3>
<div class="card-deck">
<c:forEach items="${topPlaylists}" var="playlist">
	<div class="card songcard" id="${playlist.playlistId}">
	   <img src="https://image.flaticon.com/icons/png/512/26/26805.png" class="card-img-top playlistcover" alt="...">
	   <div class="card-body">
	     <h5 class="card-title">${playlist.playlistName}</h5>
	     <p class="card-text">${playlist.description}</p>
	     <p class="card-text"><small class="text-muted">${playlist.memberId}</small></p>
	     <a href="/getPlaylist?playlistId=${playlist.playlistId}" class="btn playlistDetailsButton" id="${playlist.playlistId}"><img src="/img/icon/menu.png" alt="자세히보기" height="20px"> </a>
	     <a href="#" class="btn playlistLikeButton" id="${playlist.playlistId}"> 
	     <c:choose>
			<c:when test="${playlist.like}">
	     		<img id="${playlist.playlistId}" src="/img/icon/thumbs_glyph.png" height="20px" alt="좋아요 취소"> 
			</c:when>
			<c:otherwise>
	     		<img id="${playlist.playlistId}" src="/img/icon/thumbs_outline.png" height="20px" alt="좋아요"> 
			</c:otherwise>
		</c:choose>
	     </a>
	   </div>
	 </div>
</c:forEach>
</div>

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
	
	$('.playlistLikeButton').click(function(){
		<c:if test="${member ne null}">
		var id = $(this).attr('id');
		var $img = $(this).children('img');
		var imgsrc = $img.attr("src");
		$.ajax({
			type: "GET",
			url: "/likePlaylist?playlistId=" + id,
			success: function(data){	// ajax 잘 작동 됩니다.
				if(imgsrc === '/img/icon/thumbs_outline.png'){	// 조건문 작동 됩니다.
					$img.attr('src', "/img/icon/thumbs_glyph.png");
				} else {
					$img.attr('src', "/img/icon/thumbs_outline.png");
				}
			}, error: function(xhr, textStatus, errorThrown){
				alert(xhr.responseText);
			}
		});
		</c:if>
		<c:if test="${member eq null}">
			alert("로그인이 필요합니다.");
		</c:if>
	});
	
	$(window).resize(function(){
		$(".placeholdercard").css("width", "0");
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