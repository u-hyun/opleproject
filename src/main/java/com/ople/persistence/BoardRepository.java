package com.ople.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ople.domain.Board;
import com.ople.domain.PlaylistTrack;

public interface BoardRepository extends JpaRepository<Board, Long>{

	List<Board> findByPlaylistIdOrderByCommentIdDesc(Long playlistId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Board b WHERE b.playlistId=?1")
	void deleteBoard(Long playlistId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Board b WHERE b.memberId=?1")
	void deleteBoard(String memberId);
		
}
