package com.ople.service;

import java.util.ArrayList;
import java.util.List;
import com.ople.domain.Tag;

public interface TagService {
	String[] getTags(String trackId, String memberId);
	void updateTags(String trackId, String memberId, String[] tagList);
	List<Tag> getTrackTags(String trackId);
}
