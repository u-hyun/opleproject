<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-header">
	<h5 class="modal-title" id="historyModalLabel">로그인</h5>
	<button class="close" type="button" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">x</span>
	</button>
</div>
<div class="modal-body">
	<div class="table-responsive">
		<div class="container">
		</div>
		<form action="login">
		아이디: <input name="id"/><br>
		비밀번호: <input name="password"/><br>
		<input type="submit" value="로그인"/>
		<input type="button" value="회원가입"/>
		</form>
	</div>
</div>

<div class="modal-footer">
	<button class="btn btn-secondary" type="button" data-dismiss="modal">닫기</button>
</div>
