package com.ople.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ople.domain.Playlist;
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
	public List<Track> getTopTracksByTag(String tags) {
		return trackRepo.findFirst5ByTopTagsContainingOrderByTrackCountDesc(tags);
	}

	@Override
	public List<Track> getTopTracksByTags(List<String> tags) {
		List<Track> tagList = new ArrayList<>();
		for (String tag : tags) {
			tagList.addAll(trackRepo.findFirst5ByTopTagsContainingOrderByTrackCountDesc(tag));
		}
		// 합친 리스트가 5개가 넘을 수도 있으므로 먼저 trackCount로 재정렬
		Collections.sort(tagList, Comparator.comparingLong(Track::getTrackCount));
		Collections.reverse(tagList);	// 오름차순 -> 내림차순 정렬
		if(tagList.size() > 5)	// 리스트 수가 5개가 넘을 경우 5개로 줄여줌
			tagList = tagList.subList(0, 4);
		return tagList;
	}

	@Override
	public List<Track> getTrack() {
			return trackRepo.findAll();
		}
	}
