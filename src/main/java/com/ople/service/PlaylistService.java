package com.ople.service;

import java.util.List;

import com.ople.domain.Playlist;

public interface PlaylistService {
	Playlist savePlaylist(Playlist playlist);
	
	List<Playlist> getPlaylistById(String memberId);

}
