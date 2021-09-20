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
import com.ople.persistence.PlaylistRepository;
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
	
	@RequestMapping("/getList")
	public String getBoardList(Model m, @ModelAttribute("member")Member member) {
		
		if(member.getMemberId() == null) {
			return "redirect:loginform";
		}
		List<Board> bList = boardService.getBoardList();
		m.addAttribute("blist", bList);
		List<Playlist> pList = playlistService.getPlaylist();
		m.addAttribute("plist", pList);
		List<PlaylistTrack> ptList = playlistTrackService.getPlaylistTrack();
		m.addAttribute("ptlist", ptList);
		List<Track> pTrack = trackService.getTrack();
		m.addAttribute("ptrack", pTrack);
		
		return "/playlist/playlist";
	}
		
	@GetMapping("/insertBoard") 
	public String insertBoardView() {
		return "/board/insertBoard"; 
	}

	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @ModelAttribute("member")Member member) {
		board.setMemberId(member.getMemberId());
		boardService.saveBoard(board);
		return "redirect:getList";
	}

	@RequestMapping("/content/{commentId}")
	public String getBoard(@PathVariable Long commentId, Model m) {
		Board board = boardService.getBoard(commentId);
		m.addAttribute("board", board);
		return "/board/getBoard";
	}
	
	@GetMapping("/updateform/{commentId}")
	public String updateform(@PathVariable Long commentId, Model m) {
		Board board = boardService.onlyBoard(commentId);
		m.addAttribute("board", board);
		return "/board/updateform";
	}

	@PostMapping("/update")
	public String update(Board board) {
		boardService.saveBoard(board);
		return "redirect:getList";
	}

	@GetMapping("/delete/{commentId}")
	public String delete(@PathVariable Long commentId) {
		boardService.deleteBoard(commentId);
		return "redirect:getList";
	}
		
}