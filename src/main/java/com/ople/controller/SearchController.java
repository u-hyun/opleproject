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
	
	/**************
	 	검색 메서드
	 **************/
	@RequestMapping("/searchResult")	// keyword 검색어를 GET으로 받아옴
	public String searchResult(Model m, @RequestParam String keyword) {
		m.addAttribute("keyword", keyword);
		
		// REST로 MusicBrainz API에서 곡 정보를 JSON으로 받아옴
		RecordingSearchResult recordingSearchResult = 
				restTemplate.getForObject("https://musicbrainz.org/ws/2/recording/?query=recording:"
											+ keyword + "&fmt=json", RecordingSearchResult.class);
		
		// 앨범커버 - MB API에서 받아온 곡 하나씩 처리: 
		// MB API의 Release ID -> 
		// Cover Art Archive API 사용해 앨범커버 이미지 URL 받아옴
		for(Recordings recording : recordingSearchResult.getRecordings()) {
			try {
				String release = recording.getReleases().get(0).getId();
				
				// Cover Art Archive API 호출
				ImageSearchResult imageSearchResult =
						restTemplate.getForObject("https://coverartarchive.org/release/" 
								+ release, ImageSearchResult.class);
				String imageUrl = imageSearchResult.getImages().get(0).getThumbnails().getThumbnailUrl();
				recording.setImageUrl(imageUrl);
				
			} catch(Exception e) {	// 앨범 커버를 찾지 못한 경우
				e.printStackTrace();
				recording.setImageUrl("blank");
			}
		}
		
		m.addAttribute("recordingSearchResult", recordingSearchResult);
		
		return "searchResult";
	}
}
