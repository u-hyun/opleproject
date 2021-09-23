package com.ople.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ople.domain.Member;
import com.ople.domain.Playlist;
import com.ople.domain.PlaylistTrack;
import com.ople.domain.Track;
import com.ople.domain.Board;
import com.ople.service.BoardService;
import com.ople.service.PlaylistService;
import com.ople.service.PlaylistTrackService;
import com.ople.service.TrackService;

@SessionAttributes("member")
@Controller
public class PlaylistController {
	
	@ModelAttribute("member")
	public Member getMember() {
		return new Member();
	}
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private PlaylistTrackService playlistTrackService;
	@Autowired
	private TrackService trackService;
	
	
	@GetMapping("/playlist")
	public String getPlayList(Model m, @ModelAttribute("member")Member member) {
		
		if(member.getMemberId() == null) {
			return "redirect:loginform";
		}
		
		List<Playlist> playlists = playlistService.getPlaylistById(member.getMemberId());
		m.addAttribute("playlists", playlists);
		
		return "playlist";
	}
		
	@RequestMapping("/getPlaylist")
	public String getBoardList(Model m, @RequestParam Long playlistId,
			@ModelAttribute("member")Member member) {
		if(member.getMemberId() == null) {
			return "redirect:loginform";
		}
		
		Playlist pList = playlistService.getPlaylist(playlistId);
		m.addAttribute("plist", pList);
		List<Board> bList = boardService.getBoardList(playlistId);
		m.addAttribute("blist", bList);
		List<PlaylistTrack> ptList = playlistTrackService.getPlaylistTrack();
		m.addAttribute("ptlist", ptList);
		List<Track> pTrack = trackService.getTrack();
		m.addAttribute("ptrack", pTrack);
		m.addAttribute("playlistId", playlistId);
		
		return "getPlaylist";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @ModelAttribute("member")Member member, Long playlistId) {
		board.setMemberId(member.getMemberId());
		boardService.saveBoard(board);	
		return "redirect:getPlaylist?playlistId="+playlistId;
	}
	
	@GetMapping("/updateform/{commentId}")
	public String updateform(@PathVariable Long commentId, Model m) {
		Board board = boardService.onlyBoard(commentId);
		m.addAttribute("board", board);
		return "updateform";
	}

	@PostMapping("/update")
	public String update(Board board, Long playlistId) {
		boardService.saveBoard(board);
		return "redirect:getPlaylist?playlistId="+playlistId;
	}

	@GetMapping("/delete/{commentId}/{playlistId}")
	public String delete(@PathVariable Long commentId,@PathVariable Long playlistId) {
		boardService.deleteBoard(commentId);
		return "redirect:/getPlaylist?playlistId="+playlistId;
	}
		
}
