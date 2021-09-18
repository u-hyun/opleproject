<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-light">
	<a class="navbar-brand" href="/" style="float:left; font-size: 30px;">OPLE</a>
	<a class="btn btn-primary" style="float:right;margin:0;padding:0;" data-toggle="collapse" href="#searchbar_div" role="button" aria-expanded="false" aria-controls="collapseExample">
	    검색
	</a>
	<b>${member.memberNickname}</b>님 반갑습니다.
	<ul class="nav flex-column">
		<li class="nav-item"><a class="nav-link active" id="logout" href="logout">로그아웃</a>
		</li>
		<li class="nav-item"><a class="nav-link" id="mypage" href="mypage">마이페이지</a>
		</li>
		<li class="nav-item"><a class="nav-link" id="playlist" href="playlist">플레이리스트</a></li>
		<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
		</li>
	</ul>
	<div style="position: relative; width: 600px; height: 800px;">    

    <div style="position: absolute; bottom: 5px; ">
    Project Ople
    </div>
</nav>

