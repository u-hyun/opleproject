package com.ople.service;

import java.util.List;

import com.ople.domain.PlaylistTrack;

public interface PlaylistTrackService {
	PlaylistTrack savePlaylistTrack(PlaylistTrack playlistTrack);
	List<PlaylistTrack> getPlaylistTrackByPlaylistId(Long playlistId);
	Long countByTrackId(String trackId);
	List<PlaylistTrack> getPlaylistTrack();
}