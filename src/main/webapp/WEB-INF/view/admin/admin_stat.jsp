<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OPLE 관리자 페이지</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
	.chart{display:none;}
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
	<div id="statMenu">
		<h4>통계 확인</h4>
		<table>
			<tr>
				<td width="150"><a href="#" id="memberStatButton">회원수</a></td>
				<td width="150"><a href="#" id="newMemberStatButton">신규회원</a></td>
				<td width="150"><a href="#" id="tagStatButton">태그</a></td>
			</tr>
		</table>
	</div>
	<div class="chart" id="memberStat">
		<canvas id="memberStatChart" width="600" height="400"></canvas>
		총 회원수: ${memberCount}
	</div>
	<div class="chart" id="newMemberStat">
	<canvas id="newMemberStatChart" width="600" height="400"></canvas>
	</div>
	<div class="chart" id="tagStat">
	<canvas id="tagStatChart" width="600" height="400"></canvas>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
<script>
$(function(){
	$('.menu_div').load("menu");
	$('#memberStatButton').click(function(){
		$('.chart').css('display', 'none');
		$('#memberStat').css('display', 'block');
	});
	
	$('#newMemberStatButton').click(function(){
		$('.chart').css('display', 'none');
		$('#newMemberStat').css('display', 'block');
	});
	
	$('#tagStatButton').click(function(){
		$('.chart').css('display', 'none');
		$('#tagStat').css('display', 'block');
	});
	
});

var date = new Date();
date.setMonth(date.getMonth() - 5);
var dates = [];
for(var i = 0; i < 6; i++) {
	var tmpdate = new Date();
	tmpdate.setMonth(date.getMonth() + i);
	dates.push(tmpdate.toLocaleDateString());
}


var memberctx = document.getElementById('memberStatChart').getContext('2d');
var memberStatChart = new Chart(memberctx, {
    type: 'line',
    data: {
        labels: dates,
        datasets: [{
            label: '회원수 통계',
            data: ${memberCountList},
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: false
            }
        },
        responsive: false
    }
});

var newMemberctx = document.getElementById('newMemberStatChart').getContext('2d');
var newMemberStatChart = new Chart(newMemberctx, {
    type: 'line',
    data: {
        labels: dates,
        datasets: [{
            label: '신규회원수 통계',
            data: ${newMemberCountList},
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: false
            }
        },
        responsive: false
    }
});

var tagctx = document.getElementById('tagStatChart').getContext('2d');
var tagStatChart = new Chart(tagctx, {
    type: 'bar',
    data: {
        labels: ["rock", "electronic", "pop", "funk", "metal", "jazz", "hip-hop", "classical", "blues", "acoustic", "instrumental", "soundtrack"],
        datasets: [{
            label: '태그 통계',
            data: ${likedTagCountList},
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: false
            }
        },
        responsive: false
    }
});
</script>
</body>
</html>