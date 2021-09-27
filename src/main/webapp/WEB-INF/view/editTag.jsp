<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OPLE</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
#keywordInput{
 border-radius: 10px;
 }
</style>
</head>
<body>
<div class="menu_div" style="float:left; width: 200px;">
</div>
<div class="collapse" id="searchbar_div" style="height:15%;">
	<form id="searchForm" action="searchResult" method="GET">
	<input id="keywordInput" name="keyword" style="width:50%">  <input type="image" src="/img/icon/search_outline.png" height="35px">
	</form>
</div>
<div class="main" style="padding:30px">
<h3>태그 수정: ${track.trackName}</h3>
<form action="editTag" method="post">
	<div class="dropdown">
	  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    태그 선택
	  </button> <input type="submit" value="저장" id="saveBtn">
	  <br>
	  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	    <a class="dropdown-item" href="#" id="rock">Rock</a>
	    <a class="dropdown-item" href="#" id="electronic">Electronic</a>
	    <a class="dropdown-item" href="#" id="pop">Pop</a>
	    <a class="dropdown-item" href="#" id="funk">Funk</a>
	    <a class="dropdown-item" href="#" id="metal">Metal</a>
	    <a class="dropdown-item" href="#" id="jazz">Jazz</a>
	    <a class="dropdown-item" href="#" id="hiphop">Hip-hop</a>
	    <a class="dropdown-item" href="#" id="classical">Classical</a>
	    <a class="dropdown-item" href="#" id="blues">Blues</a>
	    <a class="dropdown-item" href="#" id="acoustic">Acoustic</a>
	    <a class="dropdown-item" href="#" id="instrumental">Instrumental</a>
	    <a class="dropdown-item" href="#" id="soundtrack">Soundtrack</a>
	  </div>
	</div>
	<ul class="list-group" id="tagList">
	</ul>
	<div style=' display:none; '> <!-- 보이지 않는 체크박스 리스트 (form submit 용) -->
		<input name="trackId" value="${track.trackId}">
		
	    <input type="checkbox" class="tagBox" name="tagBox" value="rock">
	    <input type="checkbox" class="tagBox" name="tagBox" value="electronic">
	    <input type="checkbox" class="tagBox" name="tagBox" value="pop">
	    <input type="checkbox" class="tagBox" name="tagBox" value="funk">
	    <input type="checkbox" class="tagBox" name="tagBox" value="metal">
	    <input type="checkbox" class="tagBox" name="tagBox" value="jazz">
	    <input type="checkbox" class="tagBox" name="tagBox" value="hiphop">
	    <input type="checkbox" class="tagBox" name="tagBox" value="classical">
	    <input type="checkbox" class="tagBox" name="tagBox" value="blues">
	    <input type="checkbox" class="tagBox" name="tagBox" value="acoustic">
	    <input type="checkbox" class="tagBox" name="tagBox" value="instrumental">
	    <input type="checkbox" class="tagBox" name="tagBox" value="soundtrack">
	    
	</div>
</form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
$(function(){
	$('.menu_div').load("menu");
	
	$("#searchForm").on('submit', function(e){
		e.preventDefault();
		$('.main').load("searchResult?keyword=" + $('#keywordInput').val());
	});
	
	<c:forEach items='${tagList}' var='tagname'>
		$('#tagList').append("<li class='list-group-item' id='${tagname}'>${tagname}" + 
				"&emsp;<a href='#' class='deleteBtn' style='text-align:right;' id='${tagname}'>삭제</a></li>");
		$(".tagBox[value='${tagname}']").attr('checked', 'checked');
	</c:forEach>
	
	$('.dropdown-item').click(function(){
		// 드랍다운 아이템을 클릭 -> 상응하는 체크박스가 체크돼있지 않을 때
		if(!$(".tagBox[value='" + $(this).attr('id') + "']").is(':checked')){
		$('#tagList').append('<li class="list-group-item" id="' + $(this).attr('id') + '">' + $(this).attr('id') + 
				'&emsp;<a href="#" class="deleteBtn" style="text-align:right;" id="' + $(this).attr('id') + '">삭제</a></li>');
		$(".tagBox[value='" + $(this).attr('id') + "']").attr('checked', 'checked');
		} // 이미 해당 아이템이 리스트에 올라가 있으면 아무것도 하지 않음.
	});
	
	$('#tagList').on('click', '.deleteBtn' , function(){
		$('.list-group-item#' + $(this).attr('id')).remove();
		$(".tagBox[value='" + $(this).attr('id') + "']").removeAttr('checked');
	});
	
	
});
</script>
</body>
</html>