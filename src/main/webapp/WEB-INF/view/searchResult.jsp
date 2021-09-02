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
		<option value="song">곡 검색</option>
		<option value="artist">아티스트 검색</option>
	</select>
	<input name="keyword" value="${keyword}">  <input type="submit" value="검색">
</form>

<h3>${keyword} : ${recordingSearchResult.count}건<br></h3>
<table>
	<c:forEach items="${recordingSearchResult.recordings }" var="recording">
	
		<tr>
		<td rowspan="3"><img src="${recording.imageUrl }" style="width:100px;height:100px;"></td>
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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	var isVideoOn = false;
	var currentVideoId = '';
	$('.playbutton').click(function(){
		var videoId = $(this).attr('id');
		if(!isVideoOn){	// 현재 재생중인 영상이 없을 때
			isVideoOn = true;
			currentVideoId = videoId;
			$('.video#' + videoId).html('<iframe class="youtube-video" width="300" height="50"'
					    + 'src="https://www.youtube.com/embed/' + videoId + '?autoplay=1&fs=0&autohide=0"'
						+ 'frameborder="0" allow="autoplay"> </iframe>');
			$(this).attr('value') = '정지';
		} else {	// 현재 영상이 재생중일 때
			if(currentVideoId == videoId) {	// 중지 버튼을 눌렀을 때
				$('.video#' + videoId).html('');
				$(this).attr('value') = '재생';
				isVideoOn = false;
			} else {	// 영상 재생 중 다른 영상 재생을 눌렀을 때
				$('.video').html('');
				$('.playbutton').attr('value') = '재생';
				currentVideoId = videoId;
				$(this).attr('value') = '정지';
				$('.video#' + videoId).html('<iframe class="youtube-video" width="300" height="50"'
					    + 'src="https://www.youtube.com/embed/' + videoId + '?autoplay=1&fs=0&autohide=0"'
						+ 'frameborder="0" allow="autoplay"> </iframe>');
			}
		}
	});
});
</script>
</body>
</html>