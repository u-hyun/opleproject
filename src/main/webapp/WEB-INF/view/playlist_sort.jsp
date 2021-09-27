<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
#sortable {display:block; overflow:hidden; list-style-type: none; margin: 10px; padding: 10px; width: 60%; }
  #sortable li { margin: 3px 3px 3px 3px; padding: 20px; padding-left: 1.5em; font-size: 1.4em; height: 18px; line-height: 0px;}
  #sortable li span {margin-left: -20px; }
</style>
</head>
<body>
<div class="menu_div" style="float:left; width: 200px;">
</div>
<div class="collapse" id="searchbar_div" style="height:15%;">
	<form id="searchForm" action="searchResult" method="GET">
		<input id="keywordInput" name="keyword" size="50">  <input type="submit" value="검색">
	</form>
</div>
<ul id="sortable">
	<c:forEach items="${trackList}" var="track">
		<li class="ui-state-default" id="${track.playlistTrackId}"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>${track.trackName}</li>
	</c:forEach>
  <!-- <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>Item 1</li> -->
</ul>
<input type="button" id="submitButton" value="저장">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script>
$(function(){
	$('.menu_div').load("menu");
	$("#sortable").sortable();
    $("#sortable").disableSelection();
    
    $("#submitButton").click(function(){
    	var pTrackIds = [];
    	var listElements = $("#sortable").children();
    	$.each(listElements, function(i, element){
    		pTrackIds.push(element.id);
    	});
    	console.log(pTrackIds);
    	$.ajax({
    		url: "/playlist_sort",
    		type: "POST",
    		data: { pTrackIds: pTrackIds },
    		dataType: "json",
    		success: function(data){
    			alert("성공");
    		}
    	});
    	/* location.href = "/"; */
    });
});
</script>
</body>
</html>