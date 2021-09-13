<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title>목록보기</title>
<style>
	#center{width:700px; margin-left: auto; margin-right: auto;}
	table{width: 700px; border-collapse : collapse;}
	th{ background-color: orange; width: 150px;}
	a{margin: 10px auto;}
	#page{text-align: center;}
</style>
</head>
<body>
<div>
playlistinfo
<table>
	
</table>
</div>
<div>
playlisttrack
</div>
Comment
<div id="center">
<div align="right" id="loginid">${member.memberNickname} 로그인 중</div>
<div align="right"><a href="logout">로그아웃</a></div>
<table border="1">
	<c:forEach items= "${blist}" var="board">
	<tr><td>${board.memberId}</td>
		<td>${board.content}</td>
		<td><fmt:formatDate value="${board.commentDate}" pattern="dd.MM.yyyy HH:mm a"/></td>
		<td>${board.likeCount}</td>
		<td><a href="/content/${board.commentId}" >수정</a></td>
	</tr>
	</c:forEach>	
</table>
<div id="page">
<c:if test="${begin > 2 }">
	<a href="/getBoardList?p=${begin-1}">[이전]</a>
</c:if>
	<c:forEach begin="${begin }" end="${end}" var ="i">
		<a href="/getBoardList?p=${i}">[${i}]</a>
	</c:forEach>
<c:if test="${end < totalPage }">
	<a href="/getBoardList?p=${end+1}">[다음]</a>
</c:if>
</div>
<div align="right"><a href="insertBoard">새글 등록</a></div>
</body>
</html>