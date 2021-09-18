package com.ople.service;

import java.util.List;

import com.ople.domain.Playlist;
import com.ople.domain.PlaylistTrack;

public interface PlaylistService {
	
	Playlist savePlaylist(Playlist playlist);

	List<Playlist> getPlaylist();

	List<Playlist> getPlaylistById(String memberId);

}
