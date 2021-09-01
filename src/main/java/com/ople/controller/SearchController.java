package com.ople.controller;

import java.util.ArrayList;
import java.util.List;

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
		
		for(Recordings recording : recordingSearchResult.getRecordings()) {
			try {
				String release = recording.getReleases().get(0).getId();
				System.out.println("ReleaseId : " + release);	// 테스트용
				ImageSearchResult imageSearchResult =
						restTemplate.getForObject("https://coverartarchive.org/release/" 
								+ release, ImageSearchResult.class);
				System.out.println("CovArtArc Url : " + imageSearchResult.getRelease()); // 테스트용
				String imageUrl = imageSearchResult.getImages().get(0).getThumbnails().getThumbnailUrl();
				recording.setImageUrl(imageUrl);
			} catch(Exception e) {
				e.printStackTrace();
				recording.setImageUrl("blank");
			}
		}
		
		m.addAttribute("recordingSearchResult", recordingSearchResult);
		
		return "searchResult";
	}
}
