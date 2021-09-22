<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ople : 비밀번호 찾기</title>
<style>
table {
	margin: auto;
}

h4{
text-align: center;}
</style>
</head>
<body>
<h4>비밀번호 찾기</h4>


<form method="post" id="pwFindForm">
<table>
<tr><td><input type="email" name="memberId" id="memberId" placeholder="이메일"></td>
<td><div id="id_msg"></div></td></tr>
<tr><td><input name="memberName" id="memberName" placeholder="이름"> </td></tr>


<tr>
<td align="center"><input type="button" id="pwSend" value="전송">&emsp;
<input type="button" value="취소" onclick="history.back()"></td></tr>
</table>
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

let num="";
$(function(){
	
	 $("#pwSend").click(function(){
		 let email = $("#memberId").val();
		 let membername = $("#memberName").val();
		 let namecheck = false;
		 
		if(!email){
				$("#id_msg").css("display","block").html("아이디를 입력하세요");
				return false;
			} 
		
		$.ajax({url:"/checkname",
				data:"emailAddress="+email +"&memberName=" + membername,
				dataType:"json"}
				).done(function(data){
					if(eval(data[1])){
						sendEmail(data[0]);
					}
				}).fail(function(xhr, textStatus, errorThrown){
					alert(xhr.responseText);
				});	
	}) //click
	
	
	
	function sendEmail(password){
		 let email = $("#memberId").val();
		 let membername = $("#memberName").val();
		 $.ajax({url:"/pwSend",
			 	data:"emailAddress="+email + "&number=" + password,
				dataType:"json"}
			).done(function(data){
				if(eval(data[1])){
					num = data[0];
					alert("메일로 비밀번호가 전송되었습니다.");
					history.back();
				}
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("전송 실패");
			});
		 
	 }
})

</script>
</body>
</html>