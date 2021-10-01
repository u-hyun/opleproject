<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OPLE - About Us</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
.main{
text-align:center;
padding:20px;
font-size:15px;
}
.card{
min-width: 14%;
max-width: 15%;
padding: 15px;
}
.card-text{
font-size: 12px;
}
.card-img-top{
padding:10px;
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
<div class="main">
<h3>OPLE 팀</h3>
	<div class="card-deck justify-content-center">
			<div class="card">
				<img src="img/devprofile/loris.png" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">변동혁</h5>
					<p class="card-text"><small class="text-muted">플레이리스트</small></p>
					<p class="card-text">감성 소통 프로그래머~</p>
				</div>
			</div>
			<div class="card">
				<img src="img/devprofile/u-hyun.jpg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">박유현</h5>
					<p class="card-text"><small class="text-muted">회원</small></p>
					<p class="card-text">바지 사장</p>
				</div>
			</div>
			<div class="card">
				<img src="img/devprofile/hanbyul.png" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">우한별</h5>
					<p class="card-text"><small class="text-muted">검색</small></p>
					<p class="card-text">화이팅</p>
				</div>
			</div>
	</div>
	<div style="margin-top:50px;">
	<a href="admin">테스트용 관리자 페이지</a>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
$(function(){
	$('.menu_div').load("menu");
});
</script>

</body>
</html>