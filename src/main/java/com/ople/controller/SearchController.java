package com.ople.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ople.domain.ImageSearchResult;
import com.ople.domain.RecordingSearchResult;
import com.ople.domain.Recordings;

@Controller
public class SearchController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/search")
	public String search() {
		return "search";
	}
	
	@RequestMapping("/searchResult")
	public String searchResult(Model m, @RequestParam String keyword) {
		m.addAttribute("keyword", keyword);
		RecordingSearchResult recordingSearchResult = 
				restTemplate.getForObject("https://musicbrainz.org/ws/2/recording/?query=recording:"
		+ keyword + "&fmt=json", RecordingSearchResult.class);
		m.addAttribute("recordingSearchResult", recordingSearchResult);
		String release = recordingSearchResult.getRecordings().get(0).getReleases().get(0).getId();
		try {
			ImageSearchResult imageSearchResult =
				restTemplate.getForObject("https://coverartarchive.org/release/" 
						+ release, ImageSearchResult.class);
			m.addAttribute("imageSearchResult", imageSearchResult);
		} catch(Exception e) {
			
		}
		return "searchResult";
	}
}
