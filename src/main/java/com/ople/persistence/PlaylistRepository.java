package com.ople.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ople.domain.Member;
import com.ople.domain.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
	List<Playlist> findByMemberId(String memberId);
	List<Playlist> findFirst5ByOrderByLikeCountDesc();
}
