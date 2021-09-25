package com.ople.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ople.domain.Member;
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
			tagList.addAll(trackRepo.findByTopTagsContainingOrderByTrackCountDesc(tag));
		}
		List<Track> trackList = new ArrayList<>(new HashSet<>(tagList)); // 중복 제거
		// 합친 리스트가 5개가 넘을 수도 있으므로 먼저 trackCount로 재정렬
		Collections.sort(trackList, Comparator.comparingLong(Track::getTrackCount));
		Collections.reverse(trackList);	// 오름차순 -> 내림차순 정렬
		try {
		System.out.println("최다: " + trackList.get(0).getTrackCount());
		System.out.println("2: " + trackList.get(1).getTrackCount());
		System.out.println("3: " + trackList.get(2).getTrackCount());
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(trackList.size() > 5)	// 리스트 수가 5개가 넘을 경우 5개로 줄여줌
			trackList = trackList.subList(0, 5);
		return trackList;
	}
	@Override
	public List<Track> getTrack() {
			return trackRepo.findAll();
	}
}
