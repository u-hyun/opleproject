package com.ople.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.ople.domain.Member;
import com.ople.domain.Playlist;
import com.ople.domain.PlaylistTrack;
import com.ople.search.album.ImageSearchResult;
import com.ople.search.musicbrainz.AliasSearchResult;
import com.ople.search.musicbrainz.Aliases;
import com.ople.search.musicbrainz.Artists;
import com.ople.search.musicbrainz.Recording;
import com.ople.search.musicbrainz.RecordingSearchResult;
import com.ople.search.musicbrainz.Recordings;
import com.ople.search.youtube.YoutubeSearchResult;
import com.ople.service.MemberService;
import com.ople.service.PlaylistService;
import com.ople.service.PlaylistTrackService;
import com.sun.mail.iap.Response;

@Controller
public class SearchController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MemberService memberService;
	@Autowired
	PlaylistService playlistService;
	@Autowired
	PlaylistTrackService playlistTrackService;
	
	@RequestMapping("/")
	public String mainPage() {
		return "main";
	}
	
	@RequestMapping("/search")
	public String search() {
		return "search";
	}
	
	/**************
	 	검색 메서드
	 **************/
	@RequestMapping("/searchResult")	// keyword 검색어를 GET으로 받아옴
	public String searchResult(Model m, 
					@RequestParam String keyword) {
		m.addAttribute("keyword", keyword);
		List<Member> memberSearchResult = memberService.findByMemberNicknameContainingIgnoreCase(keyword);
		m.addAttribute("memberSearchResult", memberSearchResult);
		/*
		 곡 검색 
		 */
		
		// 검색어 정리 - 한글 처리
		List<String> queries = new ArrayList<>();
		queries.add(keyword);
		List<Artists> artists;
		if(keyword.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {	// 한글 단어가 존재할시 키워드로 alias 검색
			AliasSearchResult aliasSearchResult = 
					restTemplate.getForObject("https://musicbrainz.org/ws/2/artist/?query=alias:" + keyword + "&fmt=json", 
							AliasSearchResult.class);
			artists = aliasSearchResult.getArtists();	// alias 검색 결과: artist가 여러명이 있을 수 있고,
			if(artists != null) {
				for(Artists artist : artists) {				// 각 artist별로 여러개의 alias가 있을 수 있음.
					if(artist.getAliases() != null) {		// 각 artist의 각 alias를 확인해 검색어에 포함돼있을시
						for(Aliases alias : artist.getAliases()) {	// 해당 alias를 가진 artist의 영문명을 한글 alias와 교체해 쿼리 리스트에 추가
							if(keyword.contains(alias.getName())) {	// 검색은 쿼리 리스트 안의 항목들을 OR연산자로 구분해 함.
								queries.add(keyword.replace(alias.getName(), artist.getName()));
							}
						}
					}
				}
			}
		}
		// 쿼리 리스트를 포매팅해 한줄의 쿼리문으로 만듬
		// 한 쿼리 안에 있는 단어들끼리는 AND 연산자,
		// 각 쿼리들 사이에는 OR 연산자로 구분함.
		String fmtQuery = "";
		for(int i = 0; i < queries.size(); i++) {
			fmtQuery += "(";
			String[] splitQuery = queries.get(i).split(" ");
			for(int j = 0; j < splitQuery.length; j++) {
				fmtQuery += "\"" + splitQuery[j] + "\"";
				if(j < (splitQuery.length - 1)) // 마지막 항목이 아니라면
					fmtQuery += "AND";
			}
			fmtQuery += ")";
			if(i < (queries.size() - 1))	// 마지막 항목이 아니라면
				fmtQuery += "OR";
		}
		
		String mbQueryUrl = "";
		/* keyword = keyword.replaceAll(" ", "-"); */
		mbQueryUrl = "https://musicbrainz.org/ws/2/recording/?query="
				+ fmtQuery + "&fmt=json";
		System.out.println(mbQueryUrl);
		RecordingSearchResult recordingSearchResult = 
				restTemplate.getForObject(mbQueryUrl, RecordingSearchResult.class);
		// MB API에서 받아온 곡 하나씩 처리: 앨범커버 + 유튜브 링크
		if(recordingSearchResult != null) {
			for(Recordings recording : recordingSearchResult.getRecordings()) {
				
				if (recording != null && recording.getReleases() != null) {
				String release = recording.getReleases().get(0).getId();
				recording.setImageUrl(release);
				}
				
				recording.setVideoId(recording.getTitle() + "_" + recording.getArtistcredit().get(0).getName()); //임시
				
			}
		}
		
		m.addAttribute("recordingSearchResult", recordingSearchResult);
		
		return "searchResult";
	}
	
	@RequestMapping("/addPlaylistModal")
	public String showModal(HttpServletRequest request, Model m, @RequestParam String id) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("member") != null) {	// 세션에 로그인이 돼 있을 때
			Member member = (Member)session.getAttribute("member");
			m.addAttribute("member", member);
			m.addAttribute("id", id);
			Recording recording = 
					restTemplate.getForObject("https://musicbrainz.org/ws/2/recording/" + id, Recording.class);
			m.addAttribute("recording", recording);
			List<Playlist> playlists = playlistService.getPlaylistById(member.getMemberId());
			m.addAttribute("playlists", playlists);
			return "addPlaylistModal";
		} else {	// 세션에 로그인이 안 돼 있을 때 -> 로그인 창으로
			return "loginModal";
		}
	}
	
	@RequestMapping("/menu")
	public String loadMenu(HttpServletRequest request, Model m) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		if(member != null) {
			m.addAttribute(member);
			return "menu_member";
		}
		else
			return "menu";
	}
	
	@RequestMapping("searchbarModal")
	public String loadSearchbar() {
		return "searchbarModal";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("newPlaylist")
	@ResponseBody
	public void newPlaylist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String playlistName, @RequestParam String trackId) {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		JSONObject resultJson = new JSONObject();
		
		try {
			// 새로운 플레이리스트 생성
			Playlist playlist = new Playlist();
			playlist.setPlaylistName(playlistName);
			playlist.setMemberId(member.getMemberId());
			playlist.setDescription("");
			playlist.setCustomTag("");
			playlist.setLikeCount((long) 0);
			playlist.setViewCount((long) 0);
			playlist = playlistService.savePlaylist(playlist);
			
			// 새로운 플레이리스트에 곡 넣기
			Long playlistId = playlist.getPlaylistId();
			PlaylistTrack track = new PlaylistTrack();
			track.setTrackId(trackId);
			track.setPlaylistId(playlistId);
			track.setMemberId(member.getMemberId());
			track.setListOrder(Long.valueOf(0)); // 신규 플레이리스트(곡없음): 0번째 곡으로 설정
			System.out.println(track.toString());
			playlistTrackService.savePlaylistTrack(track);
			
			resultJson.put("success", true);
		} catch(Exception e) {
			resultJson.put("success", false);
			e.printStackTrace();
		}
			response.setContentType("application/json");
			try {
				response.getWriter().write(resultJson.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@RequestMapping("addPlaylist")
	@ResponseBody
	public void addPlaylist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long playlistId, @RequestParam String trackId) {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		JSONObject resultJson = new JSONObject();
		
		try {
			// 플레이리스트에 곡 넣기
			List<PlaylistTrack> pTrackList = playlistTrackService.getPlaylistTrackByPlaylistId(playlistId);
			PlaylistTrack track = new PlaylistTrack();
			track.setTrackId(trackId);
			track.setPlaylistId(playlistId);
			track.setMemberId(member.getMemberId());
			track.setListOrder(Long.valueOf(pTrackList.size()));	// 현재 플레이리스트에 있는 곡수 = 플레이리스트 안의 곡순서
			playlistTrackService.savePlaylistTrack(track);
			System.out.println(track.toString());
			resultJson.put("success", true);
		} catch(Exception e) {
			resultJson.put("success", false);
			e.printStackTrace();
		}
		response.setContentType("application/json");
		try {
			response.getWriter().write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
