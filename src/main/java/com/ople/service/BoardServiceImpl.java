package com.ople.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ople.domain.Board;
import com.ople.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepo;	
	
	@Override
	public Page<Board> getBoardList(int pNum){
		Pageable page = PageRequest.of(pNum-1, 5);
		return boardRepo.findByOrderByCommentIdDesc(page);
	}
	
	@Override
	public void saveBoard(Board board) {
		boardRepo.save(board);
	}
	
	@Override
	public Board getBoard(Long commentId) {
		return boardRepo.getById(commentId);
	}
	
	@Override
	public Board onlyBoard(Long commentId) {
		return boardRepo.getById(commentId);
	}
	
	@Override
	public void deleteBoard(Long commentId) {
		boardRepo.deleteById(commentId);
	}
	
	
}
