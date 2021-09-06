package com.ople.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ople.domain.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

}