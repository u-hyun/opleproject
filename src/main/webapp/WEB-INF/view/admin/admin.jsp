<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OPLE 관리자 페이지</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
#keywordInput{
 border-radius: 10px;
 }
</style>
</head>
<body>
<div class="menu_div" style="float:left; width: 200px;"></div>
<div class="collapse" id="searchbar_div" style="height:15%;">
	<form id="searchForm" action="searchResult" method="GET">
		<input id="keywordInput" name="keyword" style="width:50%">  <input type="image" src="/img/icon/search_outline.png" height="35px">
	</form>
</div>
<div class="main" style="padding:30px">
	<div class="list-group">
	  <a href="/admin_stat" class="list-group-item list-group-item-action">웹사이트 통계</a>
	  <a href="/admin_member" class="list-group-item list-group-item-action">회원 관리</a>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
$(function(){
	$('.menu_div').load("menu");
})
</script>
</body>
</html>