package com.ople.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ople.domain.Board;
import com.ople.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepo;	
	
	@Override
	public List<Board> getBoardList(Long playlistId){
		return boardRepo.findByPlaylistIdOrderByCommentIdDesc(playlistId);
	}
	
	@Override
	public void saveBoard(Board board) {
		boardRepo.save(board);
	}
		
	@Override
	public Board onlyBoard(Long commentId) {
		return boardRepo.getById(commentId);
	}
	
	@Override
	public void deleteComment(Long commentId) {
		boardRepo.deleteById(commentId);
	}
	
	@Override
	public void deleteBoard(Long playlistId) {
		boardRepo.deleteBoard(playlistId);
	}
	
	@Override
	public void deleteBoard(String memberId) {
		boardRepo.deleteBoard(memberId);
	}

	
}
