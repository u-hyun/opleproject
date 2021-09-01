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
<input name="keyword" value="${keyword}">  <input type="submit" value="검색">
</form>

<h3>${keyword} : ${recordingSearchResult.count}건<br></h3>
<table>
<c:forEach items="${recordingSearchResult.recordings }" var="recording">

<tr>
<td rowspan="2"><img src="${recording.imageUrl }" style="width:100px;height:100px;"></td>
<td><h4>${recording.title}</h4></td>
</tr>
<tr><td>
	<c:forEach items="${recording.artistcredit}" var="artist">
	${artist.name}
	</c:forEach>
</td></tr>
</c:forEach>
</table>
</body>
</html>