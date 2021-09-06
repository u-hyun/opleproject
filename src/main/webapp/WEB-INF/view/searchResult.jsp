<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
</head>
<body>

<form action="searchResult">
	<select name="type" id="type">
		<option value="song" ${type == 'song' ? 'selected' : ''}>곡 검색</option>
		<option value="artist" ${type == 'artist' ? 'selected' : ''}>아티스트 검색</option>
	</select>
	<input name="keyword" value="${keyword}">  <input type="submit" value="검색">
</form>

<h3>노래 ${recordingSearchResult.count}건 ><br></h3>
<table>
	<c:forEach items="${recordingSearchResult.recordings }" var="recording">
	
		<tr>
		<td rowspan="3"><img class="albumcover" id="${recording.imageUrl}" src="" onError="this.onerror=null;this.src='https://image.flaticon.com/icons/png/512/26/26805.png';" style="width:100px;height:100px;"></td>
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
		</td></tr>
	</c:forEach>
</table>
<hr>
<!-- 
<h3>유저 ${memberSearchResult.count}건 ><br></h3>
<table>
	<c:forEach items="${memberSearchResult}" var="member">
	<tr>
	<td><img class="memberProfilePicture"></td>
	<td><b>${member.memberNickname}</b>
	</tr>
	</c:forEach>
</table>
<hr>
-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	
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
});
</script>
</body>
</html>