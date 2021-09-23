<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OPLE 관리자 페이지</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="menu_div" style="float:left; width: 200px;"></div>
<div class="collapse" id="searchbar_div" style="height:15%;">
	<form id="searchForm" action="searchResult" method="GET">
		<input id="keywordInput" name="keyword" size="50">  <input type="submit" value="검색">
	</form>
</div>
<div class="main" style="padding:30px">
	<input id="memberSearch" placeholder="아이디/닉네임"><input type="button" id="searchButton" value="검색">
	<input type="button" id="infoButton" value="상세정보" disabled>
	<input type="button" id="deleteButton" value="회원 삭제" disabled>
	<input type="button" id="adminButton" value="관리자로 승격" disabled>
	<div id="selectedMember"></div>
	<table id="memberTable"></table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
$(function(){
	var selectedId;
	$('.menu_div').load("menu");
	$('#searchButton').click(function(){
		if($('#memberSearch').val() != '') {	// 검색어 빈칸 체크
			var keyword = $('#memberSearch').val();
			$.ajax({
				type: "GET",
				url: "/admin_member_search?keyword=" + keyword,
				success: function(data) {
					disable_buttons();
					clear_result_table();
					for(var i=0 in data) {
						$('#memberTable').append(
							'<tr><th><a href="#" class="memberSelect" id=' + data[i].memberId + '>' + data[i].memberId +
							'</a></th><th>' + data[i].memberNickname +
							'</th><th>' + data[i].joinDate.slice(0, 10) + 
							'</th><th>' + data[i].admin + '</th></tr>'
						);
					}
				}
			});
		} else {
			alert("검색어를 입력하세요.");
		}
	});
	
	$('#memberTable').on('click', '.memberSelect' , function(){
		var id = $(this).attr('id');
		selectedId = id;
		$('tr').css("background-color", "transparent");
		$(this).parent().parent().css("background-color", "yellow");
		enable_buttons();
	});
});

function enable_buttons() {
	document.getElementById("infoButton").disabled = false;
	document.getElementById("deleteButton").disabled = false;
	document.getElementById("adminButton").disabled = false;
}

function disable_buttons(){
	document.getElementById("infoButton").disabled = true;
	document.getElementById("deleteButton").disabled = true;
	document.getElementById("adminButton").disabled = true;
}

function clear_result_table(){
	document.getElementById("memberTable").innerHTML = 
		"<table id='memberTable'><tr><th width='250'>아이디</th><th width='100'>닉네임</th><th width='100'>가입일</th><th width='100'>관리자</th></tr></table>";
}

</script>
</body>
</html>