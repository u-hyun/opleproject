<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>목록보기</title>
<style>
	#center{width:900px; margin-left: auto; margin-right: auto;}
	table{width: 900px; border-collapse : collapse;}
	th{ background-color: orange; width: 150px;}
	a{margin: 10px auto;}
	#page{text-align: center;}
</style>
</head>
<body>
<div id="playlistinfo"></div>
<div align="right" id="loginid">${member.memberNickname} 로그인 중</div>
<div align="right"><a href="logout">로그아웃</a></div>
<h4>playlist info</h4>
<div id="center">
<table>
	<c:forEach items= "${plist}" var="playlist">
	<tr><td>${playlist.playlistName}</td>
		<td>${playlist.description}</td>
		<td>${playlist.viewCount}</td>
		<td>${playlist.likeCount}</td>
	</tr>
	</c:forEach>	
</table>
</div>
<h4>playlistTrack</h4>
<div id="center">
<table>
	<c:forEach items= "${ptlist}" var="track">
	<tr><td>$track.trackName}</td>
		<td>${track.artistName}</td>
		<td>${track.albumName}</td>
		<td>${track.trackId}</td>
	</tr>
	</c:forEach>	
</table>
</div>
<h4>Comment</h4>
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
		<td><a href="/delete/${board.commentId}">삭제</a></td>
		</c:if>
	</tr>
	</c:forEach>	
</table>
</body>
</html>