package com.ople.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ople.domain.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
	ArrayList<Tag> findByTrackIdAndMemberId(String trackId, String memberId);
	List<Tag> findByTrackId(String trackId);
	@Transactional
	void deleteByTrackIdAndMemberId(String trackId, String memberId);
}
