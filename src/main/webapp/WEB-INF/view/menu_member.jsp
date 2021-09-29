<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-light">
	<a class="navbar-brand" href="/" style="float:left;"><img src="img/logo/logo.png" height="30px"></a>
	<a class="btn" style="float:right;margin:0;padding:0;" data-toggle="collapse" href="#searchbar_div" role="button" aria-expanded="false" aria-controls="collapseExample">
	<img src="/img/icon/search_outline.png" height="30px" >
	</a>
<!-- 	<a class="btn btn-primary" style="float:right;margin:0;padding:0;" data-toggle="collapse" href="#searchbar_div" role="button" aria-expanded="false" aria-controls="collapseExample">
	    검색
	</a> -->
	<div>
	<b>${member.memberNickname}</b>님 반갑습니다.
	</div>
	<ul class="nav flex-column">
		<li class="nav-item"><a class="nav-link active" id="logout" href="/logout">로그아웃</a>
		</li>
		<li class="nav-item"><a class="nav-link" id="mypage" href="/mypageView">마이페이지</a>
		</li>
		<li class="nav-item"><a class="nav-link" id="playlist" href="/playlist">플레이리스트</a></li>
		<c:if test="${member.admin eq '1'.charAt(0)}">
		<li class="nav-item"><a class="nav-link" id="playlist" href="/admin">관리자 메뉴</a></li>		
		</c:if>
		<li class="nav-item"><a class="nav-link" id="about" href="/admin">사이트 정보</a></li>		
	</ul>
	<div style="position: relative; width: 600px; height: 800px;">    
	
    <div style="position: absolute; bottom: 5px; ">
    Project Ople
    </div>
    </div>
</nav>
