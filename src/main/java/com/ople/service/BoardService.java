package com.ople.service;

import org.springframework.data.domain.Page;

import com.ople.domain.Board;

public interface BoardService {

	void saveBoard(Board board);
	Page<Board> getBoardList(int pNum);
	Board getBoard(Long num);
	Board onlyBoard(Long commentId);
	void deleteBoard(Long commentId);
	
}
