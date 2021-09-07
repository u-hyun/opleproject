<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Ople : 회원 가입</title>
<style>
	h4{text-align: center;}
	table{margin: auto;}
</style>
</head>
<body>
<h4>회원 가입</h4>
<form action="/join" method="post" id="joinForm">
	<table >
		<tr><td>아이디</td></tr>
		<tr><td>
		<input type="email" name="memberId" id="memberId" placeholder="이메일">
		<!-- 이메일 인증방식 추가 -->
		<input type="button" id="id_check" value="인증">&emsp;&emsp;
		<div id="id_msg"></div></td>
		<td>
		<div><input id="ck_num"><input type="button" id="ck_b" value="인증 확인"></div>
		<div id="result"></div></td>
		</tr>
		
		<tr>
		<td>비밀번호</td>
		<td>비밀번호 확인</td>
		</tr>
		<tr>
		<td><input name="memberPw" id="memberPw" type="password" placeholder="비밀번호">
		<div id="pw_msg"></div></td>
		
		<!-- 비밀번호 Ajax 다시 -->
		<td><input name="memberPw2" id="memberPw2" type="password" placeholder="비밀번호 확인">
		<div id="pw_msg2"></div></td>
		</tr>
		
		<tr>
		<td>이름</td>
		<td>생년월일</td>
		</tr>
		<tr>
		<td><input name="memberName" id="memberName" placeholder="이름">
		<div id="name_msg"></div></td>
		<td><input name="birthday" id="birthday" placeholder="yyyy/MM/dd">
		</tr>
		
		<tr>
		<td>닉네임</td>
		<td>성별</td>
		</tr>
		<tr>
		<td><input name="memberNickname" id="memberNickname" placeholder="닉네임"></td>
		<td>
		<input type="radio" name="gender" value="male">남성
		<input type="radio" name="gender" value="female">여성
		</td>
		
		<tr><td>프로필</td></tr>
		<tr><td colspan="2"><textarea rows="5" cols="60" placeholder="소개글을 입력하세요"></textarea></td>
		
		<tr><td>관심있는 장르</td></tr>
		<tr>
		<td colspan="2">
		<input type="checkbox" name="likedTags" id="likedTags" value="rock">rock&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="electronic">electronic&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="pop">pop&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="funk">funk
		</td>
		</tr>
		
		<tr>
		<td colspan="2">
		<input type="checkbox" name="likedTags" id="likedTags" value="metal">metal&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="jazz">jazz&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="hip-hop">hip-hop&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="classical">classical
		</td>
		</tr>
		
		<tr>
		<td colspan="2">
		<input type="checkbox" name="likedTags" id="likedTags" value="blues">blues&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="acoustic">acoustic&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="instrumental">instrumental&emsp;
		<input type="checkbox" name="likedTags" id="likedTags" value="soundtrack">soundtrack
		</td>
		</tr>		
		
		
		
		<tr><td colspan="2" align="center"><input type="submit" value="가입"></td></tr>
	</table>
	
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	let num ="";

	$(function(){
		
		$("#id_check").click(function(){
			
			let emailVal = $("#memberId").val();
			let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			if(emailVal.match(regExp) == null){
				$("#id_msg").html("올바른 이메일 형식이 아닙니다.")
				return false;
			}else{
			}
			
			let memberId = $("#memberId").val();	
			if(!memberId){
				$("#id_msg").html("아이디를 입력하세요");
				return false;
			}
			
			$.ajax({url:"/check_id",
			 	data:"memberId="+memberId,
				dataType:"text"}
			).done(function(data){
				if(data == ""){
					$("#id_msg").html("사용할 수 있는 아이디 입니다.")
					$("#id_msg").append("<input type='hidden' id='id_ck' value='1'>");
				}else{
					$("#id_msg").html("이미 사용중인 아이디 입니다.")
				}
			})
			
			 $.ajax({url:"/send",
				 	data:"emailAddress="+emailVal,
					dataType:"json"}
				).done(function(data){
					if(eval(data[1])){
						num = data[0];
						alert("메일이 전송되었습니다. 인증번호를 입력하세요.")
						$("#input,#result").css("display","block");
					}
				}); 
			}) 
			$("#ck_b").click(function(){
				let ck_num = $("#ck_num").val();
				if(ck_num == num){
					$("#result").html("인증이 확인되었습니다.")
					$("#result").append("<input type='hidden' id='ck' value='1'>");
				}else{
					$("#result").html("인증 실패했습니다. 다시 확인하세요.");
				}
		});
	
		
		$("#joinForm").submit(function(){
			if(!$("#memberId").val()){
				$("#id_msg").html("아이디를 입력해야 합니다.")
				return false;
			}if(!$("#memberPw").val()){
				$("#pw_msg").html("비밀번호를 입력해야 합니다.")
				return false;
			}if(!$("#memberPw2").val() != !$("#memberPw").val()){
				$("#pw_msg2").html("비밀번호가 일치하지 않습니다.")
			}if(!$("#memberName").val()){
				$("#name_msg").html("이름를 입력해야 합니다.")
				return false;
			}if($("#id_ck").val() != 1){
				$("#id_msg").html("아이디 중복 체크 하셔야 합니다.")
				return false;
			}if($("#ck").val() != 1){
				$("#result").html("이메일 인증을 입력해야 합니다.")
				return false;
			}
		})
	})

</script>

</body>
</html>