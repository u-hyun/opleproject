package com.ople.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ople.domain.Tag;
import com.ople.persistence.TagRepository;

@Service
public class TagServiceImpl implements TagService{
	
	@Autowired
	TagRepository tagRepo;

	@Override
	public String[] getTags(String trackId, String memberId) {
		ArrayList<Tag> tagArrayList = tagRepo.findByTrackIdAndMemberId(trackId, memberId);
		String[] tagList = new String[tagArrayList.size()];
		for(int i = 0; i < tagArrayList.size(); i++) {
			tagList[i] = tagArrayList.get(i).getTagName();
		}
		return tagList;
	}

	@Override
	public void updateTags(String trackId, String memberId, String[] tagList) {
		tagRepo.deleteByTrackIdAndMemberId(trackId, memberId);
		for(String tagName : tagList) {
			Tag tag = new Tag();
			tag.setMemberId(memberId);
			tag.setTagName(tagName);
			tag.setTrackId(trackId);
			tagRepo.save(tag);
		}
	}

}
