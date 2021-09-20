package com.ople.controller;

import java.util.ArrayList;

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
		return "search";
	}
}