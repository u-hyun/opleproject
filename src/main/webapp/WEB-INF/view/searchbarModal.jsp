<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-header">
	<form action="searchResult">
		<select name="type" id="type">
			<option value="song">곡 검색</option>
			<option value="artist">아티스트 검색</option>
		</select>
		<input name="keyword"/>
		<input id="searchBtn" type="submit" value="검색"/>
	</form>
</div>

