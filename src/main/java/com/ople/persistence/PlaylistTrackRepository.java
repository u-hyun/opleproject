package com.ople.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ople.domain.PlaylistTrack;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, Long>{
	
	List<PlaylistTrack> findByPlaylistIdOrderByListOrderAsc(Long playlistId);
	
	Long countByTrackId(String TrackId);

	@Transactional
	@Modifying
	@Query("DELETE FROM PlaylistTrack p WHERE p.playlistId=?1")
	void deletePlaylistTrack(Long playlistId);
	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM PlaylistTrack p WHERE p.memberId=?1")
	void deletePlaylistTrack(String memberId);

}
