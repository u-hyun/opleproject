package com.ople.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ople.domain.Member;
import com.ople.domain.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
	List<Playlist> findByMemberId(String memberId);
	List<Playlist> findFirst5ByOrderByLikeCountDesc();
	
	@Transactional
	@Modifying
	@Query("UPDATE Playlist p SET p.viewCount = p.viewCount+1 WHERE p.playlistId=?1")
	int updateViewCount(Long playlistId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Playlist p WHERE p.memberId=?1")
	void deletePlaylist(String memberId);
	
}
