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
${keyword} : ${recordingSearchResult.count}건<br>
<c:forEach items="${recordingSearchResult.recordings }" var="recording">
${recording.id} : ${recording.title } <br>
</c:forEach>
</body>
</html>