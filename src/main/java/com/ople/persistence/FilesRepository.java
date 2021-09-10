package com.ople.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ople.domain.Files;

public interface FilesRepository extends JpaRepository<Files, Integer> {

	/* Files findByFno(int fno); */
	
	
}
