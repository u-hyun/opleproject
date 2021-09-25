package com.ople.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ople.domain.Track;

public interface TrackRepository extends JpaRepository<Track, String>{
	List<Track> findFirst5ByOrderByTrackCountDesc();
	List<Track> findFirst5ByTopTagsContainingOrderByTrackCountDesc(String tags);
	List<Track> findFirst5ByTopTagsInOrderByTrackCountDesc(List<String> tags);
	List<Track> findByTopTagsContainingOrderByTrackCountDesc(String tags);
}
