package com.ople.service;

import java.util.List;
import java.util.Optional;

import com.ople.domain.Member;
import com.ople.domain.Playlist;

public interface PlaylistService {
	
	Playlist savePlaylist(Playlist playlist);

	List<Playlist> getPlaylistById(String memberId);

	List<Playlist> getTopPlaylists();

	List<Playlist> getPlaylist();

	Playlist getPlaylist(Long playlistId);

	void deletePlaylist(Long playlistId);

	Playlist onlyPlaylist(Long playlistId);

	void deletePlaylist(String memberId);

}
