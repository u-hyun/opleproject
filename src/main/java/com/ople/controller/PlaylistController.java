package com.ople.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

		Playlist pList = playlistService.getPlaylist(playlistId);
		m.addAttribute("plist", pList);
		
		List<Board> bList = boardService.getBoardList(playlistId);
		m.addAttribute("blist", bList);
		
		List<PlaylistTrack> ptList = playlistTrackService.getPlaylistTrackByPlaylistId(playlistId);
		m.addAttribute("ptlist", ptList);
		
		List<Track> trackList = new ArrayList<>();
			for(PlaylistTrack pTrack : ptList) {
				Track track = trackService.findTrack(pTrack.getTrackId()).get();
				track.setPlaylistTrackId(pTrack.getPlaylistTrackId());
				trackList.add(track);
			}
		m.addAttribute("trackList", trackList);
		
		
		m.addAttribute("playlistId", playlistId);
		
		return "getPlaylist";
	}
	
	@GetMapping("/updatePlaylist/{playlistId}")
	public String updatePlaylist(@PathVariable Long playlistId, Model m) {
		Playlist pList = playlistService.onlyPlaylist(playlistId);
		m.addAttribute("plist", pList);
		return "updatePlaylist";
	}

	@PostMapping("/update")
	public String update(Playlist playlist, Long playlistId) {
		playlistService.savePlaylist(playlist);
		return "redirect:/getPlaylist?playlistId="+playlistId;
	}	
	
	@GetMapping("/deletePlaylist/{playlistId}")
	public String deletePlaylist(@PathVariable Long playlistId) {
		playlistService.deletePlaylist(playlistId);
		playlistTrackService.deletePlaylistTrack(playlistId);
		boardService.deleteBoard(playlistId);
		return "redirect:/playlist";
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

	@PostMapping("/updateBoard")
	public String update(Board board, Long playlistId) {
		boardService.saveBoard(board);
		return "redirect:getPlaylist?playlistId="+playlistId;
	}

	@GetMapping("/delete/{commentId}/{playlistId}")
	public String delete(@PathVariable Long commentId,@PathVariable Long playlistId) {
		boardService.deleteComment(commentId);
		return "redirect:/getPlaylist?playlistId="+playlistId;
	}
	
	@GetMapping("/deleteTrack/{playlistTrackId}/{playlistId}")
	public String deleteTrack(@PathVariable Long playlistTrackId,@PathVariable Long playlistId) {
		playlistTrackService.deleteTrack(playlistTrackId);
		return "redirect:/getPlaylist?playlistId="+playlistId;
	}
	
	@GetMapping("/playlist_sort")
	public String playlistSort(Model m, @RequestParam Long playlistId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		Playlist playlist = playlistService.getPlaylist(playlistId);
		if(member != null && member.getMemberId().equals(playlist.getMemberId())) {
			List<PlaylistTrack> pTrackList = playlistTrackService.getPlaylistTrackByPlaylistId(playlistId);
			List<Track> trackList = new ArrayList<>();
			for(PlaylistTrack pTrack : pTrackList) {
				Track track = trackService.findTrack(pTrack.getTrackId()).get();
				track.setPlaylistTrackId(pTrack.getPlaylistTrackId());
				trackList.add(track);
			}
			m.addAttribute("playlistId", playlistId);
			m.addAttribute(trackList);
			return "playlist_sort";
		} else {
			return "redirect:/getPlaylist?playlistId="+playlistId;
		}
	}
	
	@PostMapping("/playlist_sort")
	public String playlistSortSubmit(@RequestParam(value="pTrackIds[]") String[] pTrackIds) {
		for(int i = 0; i < pTrackIds.length; i++) {
			PlaylistTrack pTrack = playlistTrackService.getPlaylistTrackById(Long.valueOf(pTrackIds[i]));
			pTrack.setListOrder(Long.valueOf(i));
			playlistTrackService.savePlaylistTrack(pTrack);
		}
		return "success";
	}
		
}
