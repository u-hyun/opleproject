<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div class="menu_div" style="float:left; width: 200px; height: 100%; ">
</div>
<div class="collapse" id="searchbar_div" style="height:15%;">
	<form action="searchResult">
		<input name="keyword" value="${keyword}" size="50">  <input type="submit" value="검색">
	</form>
</div>

<div class="main" style="padding:30px">
<h3>노래 ${recordingSearchResult.count}건 ><br></h3>
<table>
	<c:forEach items="${recordingSearchResult.recordings }" var="recording">
	
		<tr>
		<td rowspan="3"><img class="albumcover ${recording.id}" id="${recording.imageUrl}" src="" onError="this.onerror=null;this.src='https://image.flaticon.com/icons/png/512/26/26805.png';" style="width:100px;height:100px;"></td>
		<td><b>${recording.title}</b></td>
		</tr>
		<tr><td>
			<c:forEach items="${recording.artistcredit}" var="artist">
			${artist.name}
			</c:forEach>
		</td></tr>
		<tr><td>
			<input type="button" class="playbutton" id="${recording.videoId }" value="재생"/>
			<span class="video" id="${recording.videoId }"></span>
		</td><td>
			<input type="button" class="addplaylistbutton" id="${recording.id}" value="플레이리스트에 추가" />
		</td></tr>
	</c:forEach>
</table>
<hr>
</div>

<h3>유저 ${memberSearchResult.size()}건 ><br></h3>
<table>
	<c:forEach items="${memberSearchResult}" var="member">
	<tr>
	<td><img class="memberProfilePicture"></td>
	<td><b>${member.memberNickname}</b>
	</tr>
	</c:forEach>
</table>
<hr>



<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
	$('.menu_div').load("menu");
	
	// 앨범커버 가져오기
	$('.albumcover').each(function(i, obj) {
		var releasembid = $(this).attr('id');
		$.ajax({
			type:"GET",
			url:"https://coverartarchive.org/release/" + releasembid,
			dataType: "JSON",
			success: function(json){
				$('.albumcover#' + releasembid).attr('src', json.images[0].thumbnails.small);
			},
			error: function(xhr, textStatus, errorThrown){
				$('.albumcover#' + releasembid).attr('src', "https://image.flaticon.com/icons/png/512/26/26805.png");				
			}
		});
	});
	
	// 유튜브 재생버튼
	var isVideoOn = false;
	var currentVideoId = '';
	$('.playbutton').click(function(){
		var youtubeQuery = $(this).attr('id');
		var videoId = "";
		$.ajax({
			type: "GET",
			url: "https://youtube.googleapis.com/youtube/v3/search?part=id&maxResults=1&"
				+ "q=" + youtubeQuery
				+ "&key=AIzaSyCF96w0RUlRG5gCKGBC_4NvP3UL4Rg0Kzs",
			dataType: "JSON",
			success: function(json){
				videoId = json.items[0].id.videoId;
				if(!isVideoOn){	// 현재 재생중인 영상이 없을 때
					isVideoOn = true;
					currentVideoId = videoId;
					$('.video#' + youtubeQuery).html('<iframe class="youtube-video" width="300" height="50"'
						    + 'src="https://www.youtube.com/embed/' + videoId + '?autoplay=1&fs=0&autohide=0"'
							+ 'frameborder="0" allow="autoplay"> </iframe>');
					$('.playbutton#' + youtubeQuery).attr('value', '정지');
				} else {	// 현재 영상이 재생중일 때
					if(currentVideoId == videoId) {	// 중지 버튼을 눌렀을 때
						$('.video#' + youtubeQuery).html('');
						$('.playbutton#' + youtubeQuery).attr('value', '재생');
						isVideoOn = false;
					} else {	// 영상 재생 중 다른 영상 재생을 눌렀을 때
						$('.video').html('');
						$('.playbutton').attr('value', '재생');
						currentVideoId = videoId;
						$('.playbutton#' + youtubeQuery).attr('value', '정지');
						$('.video#' + youtubeQuery).html('<iframe class="youtube-video" width="300" height="50"'
							    + 'src="https://www.youtube.com/embed/' + videoId + '?autoplay=1&fs=0&autohide=0"'
								+ 'frameborder="0" allow="autoplay"> </iframe>');
					}
				}
			},
			error: function(xhr, textStatus, errorThrown) {
				$('.video#' + youtubeQuery).html('<span>영상을 찾을 수 없습니다.</span>');
			}
		});
	});
	
	
	$('.addplaylistbutton').click(function(){
		var id = $(this).attr('id');
		var releaseId = $("." + id).attr('id');
		var imgsrc = $("." + id).attr('src').split("/");
		var img = imgsrc[imgsrc.length - 1]
		$('#MoaModal .modal-content').load("addPlaylistModal?id=" + id + "&releaseId=" + releaseId + "&img=" + img);
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