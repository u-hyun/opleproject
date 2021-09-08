package com.ople.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.ople.domain.Member;
import com.ople.search.album.ImageSearchResult;
import com.ople.search.musicbrainz.AliasSearchResult;
import com.ople.search.musicbrainz.Recording;
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
		
		/*
		 곡 검색 
		 */
		
		// REST로 MusicBrainz API에서 곡 정보를 JSON으로 받아옴
		String mbQueryUrl = "";
		if(type.equals("song")) {
			keyword = keyword.replaceAll(" ", "-");
			mbQueryUrl = "https://musicbrainz.org/ws/2/recording/?query=recording:"
					+ keyword + "&fmt=json";
		} else if (type.equals("artist")) {
			if(keyword.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
				AliasSearchResult aliasSearchResult = 
						restTemplate.getForObject("https://musicbrainz.org/ws/2/artist/?query=alias:" + keyword + "&fmt=json", 
								AliasSearchResult.class);
				keyword = aliasSearchResult.getArtists().get(0).getName();
			}
			keyword = keyword.replaceAll(" ", "-");
			mbQueryUrl = "https://musicbrainz.org/ws/2/recording/?query=artist:"
					+ keyword + "&fmt=json";
		}
		RecordingSearchResult recordingSearchResult = 
				restTemplate.getForObject(mbQueryUrl, RecordingSearchResult.class);
		// MB API에서 받아온 곡 하나씩 처리: 앨범커버 + 유튜브 링크
		for(Recordings recording : recordingSearchResult.getRecordings()) {
			// 앨범커버 가져오기: MB API의 Release ID -> 
			// Cover Art Archive API 사용해 앨범커버 이미지 URL 받아옴
			/*
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
			*/
			// 임시 
			if (recording != null) {
			String release = recording.getReleases().get(0).getId();
			recording.setImageUrl(release);
			}
			
			// 유튜브 링크 가져오기
			
			/*
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
			*/
			recording.setVideoId(recording.getTitle() + "_" + recording.getArtistcredit().get(0).getName()); //임시
			
		}
		
		m.addAttribute("recordingSearchResult", recordingSearchResult);
		
		/*
		 유저 검색
		 */
		/*
		List<Member> memberSearchResult = memberService.findByMemberNicknameContainingIgnoreCase(keyword);
		m.addAttribute(memberSearchResult);
		*/
		
		return "searchResult";
	}
	
	@RequestMapping("/addPlaylistModal")
	public String showModal(HttpServletRequest request, Model m, @RequestParam String id) {
		HttpSession session = request.getSession();
		
		/*
		 테스트용
		 */
		Member testMember = new Member();
		testMember.setMemberId("테스트아이디123");
		session.setAttribute("member", testMember);
		// 이곳에 테스트할 값들을 setAttribute로 추가하시면 됩니다.
		/*
		 테스트용 끝
		 */
		
		if(session.getAttribute("member") != null) {	// 세션에 로그인이 돼 있을 때
			m.addAttribute("member", session.getAttribute("member"));
			m.addAttribute("id", id);
			Recording recording = 
					restTemplate.getForObject("https://musicbrainz.org/ws/2/recording/" + id, Recording.class);
			m.addAttribute("recording", recording);
			return "addPlaylistModal";
		} else {	// 세션에 로그인이 안 돼 있을 때 -> 로그인 창으로
			return "loginModal";
		}
	}
	
	@RequestMapping("/menu")
	public String loadMenu() {
		return "menu";
	}
	
	@RequestMapping("searchbarModal")
	public String loadSearchbar() {
		return "searchbarModal";
	}
	
}
