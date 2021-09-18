package com.ople.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ople.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
		
}
