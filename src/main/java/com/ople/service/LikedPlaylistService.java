package com.ople.service;

import java.util.List;

import com.ople.domain.LikedPlaylist;

public interface LikedPlaylistService {
	List<LikedPlaylist> getLikedPlaylists(String memberId);
	boolean checkLike(String memberId, Long playlistId);
	void saveLike(LikedPlaylist likedPlaylist);
	void deleteLike(String memberId, Long playlistId);
}
