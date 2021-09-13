package com.ople.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ople.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	Page<Board> findByOrderByCommentIdDesc(Pageable page);
		
}
