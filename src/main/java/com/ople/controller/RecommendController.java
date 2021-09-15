package com.ople.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ople.domain.Track;
import com.ople.service.TrackService;

@Controller
public class RecommendController {
	
	@Autowired
	TrackService trackService;
	
	@GetMapping("/editTag")
	public String editTag(Model m, @RequestParam String trackId) {
		Track track = trackService.findTrack(trackId).get();
		m.addAttribute("track", track);
		return "editTag";
	}
	
	@PostMapping("/editTag")
	public String submitTag() {
		return "redirect:playlist";
	}
}
