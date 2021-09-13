package com.ople.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ople.domain.Track;

public interface TrackRepository extends JpaRepository<Track, String>{

}
