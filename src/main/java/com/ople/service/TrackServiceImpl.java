package com.ople.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ople.domain.Track;
import com.ople.persistence.TrackRepository;

@Service
public class TrackServiceImpl implements TrackService{
	
	@Autowired
	TrackRepository trackRepo;
	
	@Override
	public Track saveTrack(Track track) {
		return trackRepo.save(track);
	}

	@Override
	public boolean checkTrack(Track track) {
		Optional<Track> foundTrack = trackRepo.findById(track.getTrackId());
		return foundTrack.isPresent();
	}

	@Override
	public Optional<Track> findTrack(String trackId) {
		return trackRepo.findById(trackId);
	}

	@Override
	public List<Track> getTopTracks() {
		return trackRepo.findFirst5ByOrderByTrackCountDesc();
	}

	@Override
	public List<Track> getTopTracksByTags(String tags) {
		return trackRepo.findFirst5ByTopTagsContainingOrderByTrackCountDesc(tags);
	}

}
