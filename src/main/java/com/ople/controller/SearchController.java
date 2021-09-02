package com.ople.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ople.search.album.ImageSearchResult;
import com.ople.search.musicbrainz.RecordingSearchResult;
import com.ople.search.musicbrainz.Recordings;
import com.ople.search.youtube.YoutubeSearchResult;

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
	public String searchResult(Model m, 
							@RequestParam String type, 
							@RequestParam String keyword) {
		m.addAttribute("keyword", keyword);
		m.addAttribute("type", type);
		
		// REST로 MusicBrainz API에서 곡 정보를 JSON으로 받아옴
		String mbQueryUrl = "";
		if(type.equals("song")) {
			mbQueryUrl = "https://musicbrainz.org/ws/2/recording/?query=recording:"
					+ keyword + "&fmt=json";
		} else if (type.equals("artist")) {
			mbQueryUrl = "https://musicbrainz.org/ws/2/recording/?query=artist:"
					+ keyword + "&fmt=json";
		}
		RecordingSearchResult recordingSearchResult = 
				restTemplate.getForObject(mbQueryUrl, RecordingSearchResult.class);
		// MB API에서 받아온 곡 하나씩 처리: 앨범커버 + 유튜브 링크
		for(Recordings recording : recordingSearchResult.getRecordings()) {
			// 앨범커버 가져오기: MB API의 Release ID -> 
			// Cover Art Archive API 사용해 앨범커버 이미지 URL 받아옴
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
			
			// 유튜브 링크 가져오기
			try {
				String title = recording.getTitle();
				String artist = recording.getArtistcredit().get(0).getName();
				YoutubeSearchResult youtubeSearchResult = 
						restTemplate.getForObject("https://youtube.googleapis.com/youtube/v3/search?part=snippet&"
								+ "q=" + artist + title
								+ "&key=AIzaSyCF96w0RUlRG5gCKGBC_4NvP3UL4Rg0Kzs"
								, YoutubeSearchResult.class);
				String videoId = youtubeSearchResult.getItems().get(0).getId().getVideoId();
				recording.setVideoId(videoId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		m.addAttribute("recordingSearchResult", recordingSearchResult);
		
		return "searchResult";
	}
}
