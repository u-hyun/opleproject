package com.ople.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ople.domain.LikedPlaylist;

@Repository
public interface LikedPlaylistRepository extends JpaRepository<LikedPlaylist, Long>{
	List<LikedPlaylist> findByMemberId(String memberId);
	LikedPlaylist findByMemberIdAndPlaylistId(String memberId, Long playlistId);
}
