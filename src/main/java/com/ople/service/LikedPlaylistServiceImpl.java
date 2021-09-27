package com.ople.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ople.domain.LikedPlaylist;
import com.ople.persistence.LikedPlaylistRepository;

@Service
public class LikedPlaylistServiceImpl implements LikedPlaylistService{
	
	@Autowired
	LikedPlaylistRepository likedPlaylistRepo;
	
	@Override
	public List<LikedPlaylist> getLikedPlaylists(String memberId) {
		return likedPlaylistRepo.findByMemberId(memberId);
	}

	@Override
	public void saveLike(LikedPlaylist likedPlaylist) {
		likedPlaylistRepo.save(likedPlaylist);
	}

	@Override
	public boolean checkLike(String memberId, Long playlistId) {
		LikedPlaylist check = likedPlaylistRepo.findByMemberIdAndPlaylistId(memberId, playlistId);
		if(check != null)
			return true;
		else
			return false;
	}

	@Override
	public void deleteLike(String memberId, Long playlistId) {
		LikedPlaylist like = likedPlaylistRepo.findByMemberIdAndPlaylistId(memberId, playlistId);
		likedPlaylistRepo.delete(like);
	}
	
}
