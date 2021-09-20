package com.ople.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ople.domain.PlaylistTrack;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, Long>{
	List<PlaylistTrack> findByPlaylistId(Long playlistId);
	Long countByTrackId(String TrackId);
}