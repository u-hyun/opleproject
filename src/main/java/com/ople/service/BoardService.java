package com.ople.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ople.domain.Board;

public interface BoardService {

	void saveBoard(Board board);
	Board onlyBoard(Long commentId);
	void deleteBoard(Long commentId);
	List<Board> getBoardList(Long playlistId);
	
}
