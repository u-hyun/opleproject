<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색</title>
</head>
<body>
검색
<form action="searchResult">
	<select name="type" id="type">
		<option value="song">곡 검색</option>
		<option value="artist">아티스트 검색</option>
	</select>
	<input name="keyword"/>
	<input type="submit" value="검색"/>
</form>
<div class="video"></div>
<input type="button" class="playbutton" value="재생">

<iframe class="youtube-video" width="300" height="50"'
src="https://www.youtube.com/embed/5NV6Rdv1a3I?autoplay=1&fs=0&autohide=0"'
frameborder="0" allow="autoplay"> </iframe>

<!-- 
https://youtube.googleapis.com/youtube/v3/search?part=snippet&q=daft%20punk%20get%20lucky&key=AIzaSyCF96w0RUlRG5gCKGBC_4NvP3UL4Rg0Kzs
 -->

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$('.playbutton').click(function(){
		$('.video').html('<iframe class="youtube-video" width="300" height="50"'
				    + 'src="https://www.youtube.com/embed/5NV6Rdv1a3I?autoplay=1&fs=0&autohide=0"'
					+ 'frameborder="0" allow="autoplay"> </iframe>');
	});
});
</script>
</body>
</html>