package com.ople.service;

import java.util.List;

import com.ople.domain.PlaylistTrack;

public interface PlaylistTrackService {
	PlaylistTrack savePlaylistTrack(PlaylistTrack playlistTrack);
	List<PlaylistTrack> getPlaylistTrackByPlaylistId(Long playlistId);
	Long countByTrackId(String trackId);
	void deleteTrack(Long playlistTrackId);
	List<PlaylistTrack> getPlaylistTrack();
	PlaylistTrack getPlaylistTrackById(Long playlistTrackId);
	void deletePlaylistTrack(Long playlistId);
	void deletePlaylistTrack(String memberId);
	
	
}
