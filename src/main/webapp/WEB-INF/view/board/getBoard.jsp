<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
	table{ border-collapse : collapse;}
</style>
</head>
<body>
<table border="1">
	<tr><td>글번호</td><td>${board.commentId}</td></tr>
	<tr><td>내용</td><td>${board.content}</td></tr>
	<tr><td>등록일</td><td><fmt:formatDate value="${board.commentDate}" pattern="dd.MM.yyyy HH:mm:ss a"/> </td></tr>
	<tr><td colspan="2">
	<c:if test="${board.memberId eq member.memberId}">
		<a href="/updateform/${board.commentId}">글 수정</a>	
		<a href="/delete/${board.commentId}">글 삭제</a>
	</c:if>
	<a href="/getBoardList">글 목록</a>
	</td></tr>
</table>
</body>
</html>