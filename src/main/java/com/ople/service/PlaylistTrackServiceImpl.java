package com.ople.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ople.domain.PlaylistTrack;
import com.ople.persistence.PlaylistTrackRepository;

@Service
public class PlaylistTrackServiceImpl implements PlaylistTrackService{
	
	@Autowired
	PlaylistTrackRepository playlistTrackRepo;

	@Override
	public PlaylistTrack savePlaylistTrack(PlaylistTrack playlistTrack) {
		return playlistTrackRepo.save(playlistTrack);
	}

	@Override
	public List<PlaylistTrack> getPlaylistTrackByPlaylistId(Long playlistId) {
		return playlistTrackRepo.findByPlaylistIdOrderByListOrderAsc(playlistId);
	}

	@Override
	public Long countByTrackId(String trackId) {
		return playlistTrackRepo.countByTrackId(trackId);
	}

	@Override
	public List<PlaylistTrack> getPlaylistTrack() {
		return playlistTrackRepo.findAll();
	}

	@Override
	public PlaylistTrack getPlaylistTrackById(Long playlistTrackId) {
		return playlistTrackRepo.getById(playlistTrackId);
	}

}
