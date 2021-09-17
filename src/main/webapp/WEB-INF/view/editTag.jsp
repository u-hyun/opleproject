<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OPLE</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>

</style>
</head>
<body>
<div class="menu_div" style="float:left; width: 200px;">
</div>
<div class="collapse" id="searchbar_div" style="height:15%;">
	<form id="searchForm" action="#">
		<input id="keywordInput" name="keyword" size="50">  <input type="submit" value="검색">
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
	<div style=" display: none; " >
	<a class="dropdown-item" href="#" id="rock">Rock</a>
	    <input type="checkbox" class="" id="electronic">
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
	
	$('.dropdown-item').click(function(){
		$('#tagList').append('<li class="list-group-item" id="' + $(this).attr('id') + '">' + $(this).attr('id') + 
				'<a href="#" class="deleteBtn" id="' + $(this).attr('id') + '">삭제</a></li>');
	});
	
	$('#tagList').on('click', '.deleteBtn' , function(){
		$('.list-group-item#' + $(this).attr('id')).remove();
	});
	
	var taglist = [];
	$('#saveBtn').on('submit', function(e){
		e.preventDefault();
		$('ul li').each(function(){
			taglist.push($(this).text())
		});
	});
	
});
</script>
</body>
</html>