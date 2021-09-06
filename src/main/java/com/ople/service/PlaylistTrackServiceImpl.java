package com.ople.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ople.domain.PlaylistTrack;
import com.ople.persistence.PlaylistTrackRepository;

public class PlaylistTrackServiceImpl implements PlaylistTrackService{
	
	@Autowired
	PlaylistTrackRepository playlistTrackRepo;

	@Override
	public PlaylistTrack savePlaylistTrack(PlaylistTrack playlistTrack) {
		return playlistTrackRepo.save(playlistTrack);
	}

}
