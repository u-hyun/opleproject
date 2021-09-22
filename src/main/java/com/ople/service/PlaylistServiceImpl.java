package com.ople.service;

import java.util.List;

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

	@Override
	public List<Playlist> getPlaylistById(String memberId) {
		return playlistRepo.findByMemberId(memberId);
	}

	@Override
	public List<Playlist> getTopPlaylists() {
		return playlistRepo.findFirst5ByOrderByLikeCountDesc();
	}

}
