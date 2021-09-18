package com.ople.service;

import java.util.List;
import java.util.Optional;

import com.ople.domain.Track;

public interface TrackService {
	Track saveTrack(Track track);
	boolean checkTrack(Track track);
	Optional<Track> findTrack(String trackId);
	List<Track> getTopTracks();
	List<Track> getTopTracksByTags(String tags);
}
