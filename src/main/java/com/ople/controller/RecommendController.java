package com.ople.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ople.domain.Member;
import com.ople.domain.Tag;
import com.ople.domain.Track;
import com.ople.service.TagService;
import com.ople.service.TrackService;

@Controller
public class RecommendController {
	
	@Autowired
	TrackService trackService;
	@Autowired
	TagService tagService;
	
	@GetMapping("/editTag")
	public String editTag(HttpServletRequest request, Model m, @RequestParam String trackId) {
		HttpSession session = request.getSession();
		String memberId = ((Member)session.getAttribute("member")).getMemberId();
		Track track = trackService.findTrack(trackId).get();
		m.addAttribute("track", track);
		String[] tagList = tagService.getTags(trackId, memberId);
		m.addAttribute("tagList", tagList);
		return "editTag";
	}
	
	@PostMapping("/editTag")
	public String submitTag(HttpServletRequest request, 
				@RequestParam("tagBox")String[] tags,
				@RequestParam String trackId) {
		HttpSession session = request.getSession();
		String memberId = ((Member)session.getAttribute("member")).getMemberId();
		tagService.updateTags(trackId, memberId, tags);
		
		// 곡의 topTags 업데이트
		List<Tag> tagList = tagService.getTrackTags(trackId);
		if(tagList != null) {
			Map<String, Integer> tagSortMap = new HashMap<>();
			for(Tag tag : tagList) {
				int count;
				try {
					count = tagSortMap.get(tag.getTagName());
				} catch (Exception e) {
					count = 0;
				}
				tagSortMap.put(tag.getTagName(), count + 1);
			}
			System.out.println(tagSortMap.toString());
			List<Entry<String, Integer>> tagSortList = new ArrayList<>(tagSortMap.entrySet());
			tagSortList.sort(Entry.comparingByValue());
			Collections.reverse(tagSortList);	// sort()는 오름차순으로 정렬하므로 순서를 뒤집어야 함
			Track track = trackService.findTrack(trackId).get();
			String topTags = tagSortList.get(0).getKey();
			if(tagSortList.size() > 1)
				topTags += "," + tagSortList.get(1).getKey();
			if(tagSortList.size() > 2)
				topTags += "," + tagSortList.get(2).getKey();
			track.setTopTags(topTags);
			trackService.saveTrack(track);
		}
		return "redirect:/";
	}
}
