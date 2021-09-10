package com.ople.service;

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

}
