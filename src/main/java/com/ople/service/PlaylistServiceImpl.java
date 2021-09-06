package com.ople.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ople.domain.Playlist;
import com.ople.persistence.PlaylistRepository;

@Service
public class PlaylistServiceImpl implements PlaylistService{
	
	@Autowired
	PlaylistRepository playlistRepo;

	@Override
	public Playlist savePlaylist(Playlist playlist) {
		return playlistRepo.save(playlist);
	}

}