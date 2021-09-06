package com.ople.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ople.domain.PlaylistTrack;

public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, String>{

}
