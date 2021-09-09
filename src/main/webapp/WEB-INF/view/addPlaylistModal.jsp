<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-header">
	<h5 class="modal-title" id="historyModalLabel">${member.memberId}님 플레이리스트에 추가: ${recording.title}</h5>
	<button class="close" type="button" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">x</span>
	</button>
</div>
<div class="modal-body">
	<div class="table-responsive">
		<div class="container">
		</div>
		<table class="table table-hover">
			<thead class="text-center">
				<tr class="content">
					<th class="text-center">
						플레이리스트 이름
					</th>
					<th class="text-center">
						추가하기
					</th>
				</tr>
			</thead>
			<tbody class="text-center">
				<tr class="content" style="font-size: 12px;">
					<td class="text-center">새 플레이리스트 만들기</td>
					<td class="text-center"><input type="button" value="추가"></td>
				</tr>
				<c:forEach items="${playlists}" var="playlist">
				<tr class="content" style="font-size: 12px;">
					<td class="text-center">${playlist.playlistName}</td>
					<td class="text-center"><input type="button" value="추가"></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="modal-footer">
	<button class="btn btn-secondary" type="button" data-dismiss="modal">닫기</button>
</div>
