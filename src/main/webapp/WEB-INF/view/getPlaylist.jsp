<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>플레이리스트 상세화면</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
#keywordInput {
	border-radius: 10px;
}

.btn-primary {
	margin: 0;
	padding: 0;
}

#center {
	width: 800px;
	margin-left: auto;
	margin-right: auto;
}

table {
	width: 800px;
	border-collapse: collapse;
	font-size: 90%;
}

a {
	margin: 10px auto;
}

#profile {
	border-radius: 30%;
}
</style>
</head>
<body>
	<div class="menu_div" style="float: left; width: 200px;"></div>
	<div class="collapse" id="searchbar_div" style="height: 15%;">
		<form id="searchForm" action="searchResult" method="GET">
			<input id="keywordInput" name="keyword" style="width: 50%"> <input
				type="image" src="/img/icon/search_outline.png" height="35px">
		</form>
	</div>
	<br>
	<br>
	<br>

	<div id="center">
		<h2>
			<b>${plist.playlistName}</b>
		</h2>
		<table border="0">
			<tr>
				<td width="130px"><c:if
						test="${plist.member.imagePath ne null}">
						<img src="${plist.member.imagePath}" style="border-radius: 50px"
							width="100px" height="100px">
					</c:if> <c:if test="${plist.member.imagePath eq null}">
						<img src="/img/icon/profile.png" style="border-radius: 50px"
							width="100px" height="100px">
					</c:if></td>
				<td>${plist.description}</td>
				<td width="60px"><img src="/img/icon/view.png" width="20px" height="20px">&emsp;${plist.viewCount}</td>
				<c:choose>
					<c:when test="${plist.like}">
						<td width="70px"><a href="#" class="btn playlistLikeButton"
							id="${plist.playlistId}"> <img class="likeimage"
								id="${plist.playlistId}" src="/img/icon/update_button.png"
								width="20px" height="20px" alt="좋아요취소"></a>${plist.likeCount}</td>
					</c:when>
					<c:otherwise>
						<td width="70px"><a href="#" class="btn playlistLikeButton"
							id="${plist.playlistId}"> <img class="likeimage"
								id="${plist.playlistId}" src="/img/icon/thumbs_outline.png"
								height="20px" alt="좋아요"></a>${plist.likeCount}</td>
					</c:otherwise>
				</c:choose>
				<c:if test="${plist.memberId eq member.memberId}">
					<td width="30px"><a href="/updatePlaylist/${plist.playlistId}"><img
							src="/img/icon/update_button.png" width="20px" height="20px"></a></td>
					<td width="30px"><a href="/deletePlaylist/${plist.playlistId}"><img
							src="/img/icon/delete_button.png" width="20px" height="20px"></a></td>
				</c:if>
			</tr>
		</table>
	</div>
	<br>
	<div id="center">
		<legend>PlaylistTrack</legend>
		<table border="0">
			<c:forEach items="${trackList}" var="track">
				<tr height="50px">
					<td width="40px"><img src="/img/icon/song.png" width="20px"
						height="20px"></td>
					<td>${track.trackName}</td>
					<td width="180px">${track.artistName}</td>
					<td width="280px">${track.albumName}</td>
					<c:if test="${plist.memberId eq member.memberId}">
						<td width="30px"><a href="/editTag?trackId=${track.trackId}"
							id="${track.trackId}"><img src="/img/icon/update_button.png"
								height="20px" alt="태그등록"></a></td>
						<td width="30px"><a
							href="/deleteTrack/${track.playlistTrackId}/${plist.playlistId}">
								<img src="/img/icon/delete_button.png" height="20px">
						</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<table border="0">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<c:if test="${plist.memberId eq member.memberId}">
					<td width="50px"><a
						href="/playlist_sort?playlistId=${plist.playlistId}"> <img
							src="/img/icon/menu.png" width="30px" height="30px" alt="순서변경"></a></td>
				</c:if>
			</tr>
		</table>
	</div>
	<br>
	<div id="center">
		<legend>Comment</legend>
		<form action="/insertBoard" method="post">
			<table border="0">
				<tr>
					<td><input type="hidden" name="playlistId"
						value="${plist.playlistId}"></td>
					<td><textarea name="content" cols="95" rows="2"></textarea></td>
					<td width="50px"><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<table border="0">
			<c:forEach items="${blist}" var="board">
				<tr height="50px">
					<td width="50px"><c:if
							test="${board.member.imagePath ne null}">
							<img src="${board.member.imagePath}" style="border-radius: 50px"
								width="35px" height="35px">
						</c:if> <c:if test="${board.member.imagePath eq null}">
							<img src="/img/icon/profile.png" style="border-radius: 50px"
								width="35px" height="35px">
						</c:if></td>
					<td width="100px">${board.member.memberNickname}</td>
					<td>${board.content}</td>
					<td width="120px" style="font-size: 50%;"><fmt:formatDate
							value="${board.commentDate}" pattern="yy.MM.dd HH:mm" /></td>
					<c:if test="${board.memberId eq member.memberId}">
						<td width="30px"><a href="/updateform/${board.commentId}"><img
								src="/img/icon/update_button.png" height="20px"></a></td>
						<td width="30px"><a
							href="/delete/${board.commentId}/${board.playlistId}"><img
								src="/img/icon/delete_button.png" height="20px"></a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script>
		$(function() {
			$('.menu_div').load("menu");
			$('.playlistLikeButton').click(function() {
				var id = $(this).attr('id');
				var imgsrc = $(this).children('img').attr("src");
				var $img = $(this).children('img');
				$.ajax({
					type : "GET",
					url : "/likePlaylist?playlistId=" + id,
					success : function(data) {
						if (imgsrc === '/img/icon/thumbs_outline.png') {
							$img.attr('src', "/img/icon/update_button.png");
						} else {
							$img.attr('src', "/img/icon/thumbs_outline.png");
						}
					},
					error : function(xhr, textStatus, errorThrown) {
						alert(xhr.responseText);
					}
				});
			});
		});
	</script>

</body>
</html>