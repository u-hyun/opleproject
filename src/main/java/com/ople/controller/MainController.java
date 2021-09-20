package com.ople.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ople.domain.Member;
import com.ople.domain.Playlist;
import com.ople.domain.Track;
import com.ople.service.PlaylistService;
import com.ople.service.TrackService;

@Controller
public class MainController {
	
	@Autowired
	PlaylistService playlistService;
	@Autowired
	TrackService trackService;
	
	@RequestMapping("/")
	public String mainPage(HttpServletRequest request, Model m) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		if(member != null) {	// 로그인 했을 때의 메인화면
			String likedTags = member.getLikedTags();
			List<String> tags = Arrays.asList(likedTags.split(","));
			System.out.println(tags.toString());
			List<Track> topTracks = trackService.getTopTracksByTags(tags);
			List<Playlist> topPlaylists = playlistService.getTopPlaylists();
			m.addAttribute("topTracks", topTracks);
			m.addAttribute("topPlaylists", topPlaylists);
		} else {	// 로그인 하지 않았을 때의 메인화면		
			List<Track> topTracks = trackService.getTopTracks();
			List<Playlist> topPlaylists = playlistService.getTopPlaylists();
			m.addAttribute("topTracks", topTracks);
			m.addAttribute("topPlaylists", topPlaylists);
		}
		return "main";
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
}
