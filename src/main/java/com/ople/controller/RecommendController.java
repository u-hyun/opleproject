package com.ople.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ople.domain.LikedPlaylist;
import com.ople.domain.Member;
import com.ople.domain.Playlist;
import com.ople.domain.Tag;
import com.ople.domain.Track;
import com.ople.service.LikedPlaylistService;
import com.ople.service.PlaylistService;
import com.ople.service.TagService;
import com.ople.service.TrackService;

@Controller
public class RecommendController {
	
	@Autowired
	TrackService trackService;
	@Autowired
	TagService tagService;
	@Autowired
	LikedPlaylistService likedPlaylistService;
	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/editTag")
	public String editTag(HttpServletRequest request, Model m, @RequestParam String trackId) {
		HttpSession session = request.getSession();
		String memberId = ((Member)session.getAttribute("member")).getMemberId();
		Track track = trackService.findTrack(trackId).get();
		m.addAttribute("track", track);
		String[] tagList = tagService.getTags(trackId, memberId);
		m.addAttribute("tagList", tagList);
		return "editTag";
	}
	
	@PostMapping("/editTag")
	public String submitTag(HttpServletRequest request, 
				@RequestParam("tagBox")String[] tags,
				@RequestParam String trackId) {
		HttpSession session = request.getSession();
		String memberId = ((Member)session.getAttribute("member")).getMemberId();
		tagService.updateTags(trackId, memberId, tags);
		
		// 곡의 topTags 업데이트
		List<Tag> tagList = tagService.getTrackTags(trackId);
		if(tagList != null) {
			Map<String, Integer> tagSortMap = new HashMap<>();
			for(Tag tag : tagList) {
				int count;
				try {
					count = tagSortMap.get(tag.getTagName());
				} catch (Exception e) {
					count = 0;
				}
				tagSortMap.put(tag.getTagName(), count + 1);
			}
			System.out.println(tagSortMap.toString());
			List<Entry<String, Integer>> tagSortList = new ArrayList<>(tagSortMap.entrySet());
			tagSortList.sort(Entry.comparingByValue());
			Collections.reverse(tagSortList);	// sort()는 오름차순으로 정렬하므로 순서를 뒤집어야 함
			Track track = trackService.findTrack(trackId).get();
			String topTags = tagSortList.get(0).getKey();
			if(tagSortList.size() > 1)
				topTags += "," + tagSortList.get(1).getKey();
			if(tagSortList.size() > 2)
				topTags += "," + tagSortList.get(2).getKey();
			track.setTopTags(topTags);
			trackService.saveTrack(track);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/likePlaylist")
	@ResponseBody
	public String likePlaylist(HttpServletRequest request, @RequestParam Long playlistId) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		if(member != null) {
			if(likedPlaylistService.checkLike(member.getMemberId(), playlistId)) {
				// 이미 라이크 돼있을 때
				likedPlaylistService.deleteLike(member.getMemberId(), playlistId);
				Playlist playlist = playlistService.getPlaylist(playlistId);
				playlist.setLikeCount(playlist.getLikeCount() - 1);
				playlistService.savePlaylist(playlist);
			} else { // 라이크 안 돼 있을 때
				LikedPlaylist like = new LikedPlaylist();
				like.setMemberId(member.getMemberId());
				like.setPlaylistId(playlistId);
				likedPlaylistService.saveLike(like);
				Playlist playlist = playlistService.getPlaylist(playlistId);
				playlist.setLikeCount(playlist.getLikeCount() + 1);
				playlistService.savePlaylist(playlist);
			}
			return "success";
		} else { // 로그인 안 돼 있을 때
			return "로그인이 필요한 기능입니다.";
		}
	}
	
	@RequestMapping("/getLikes")
	@ResponseBody
	public String getLikes(HttpServletRequest request, Model m) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		List<LikedPlaylist> likeList = likedPlaylistService.getLikedPlaylists(member.getMemberId());
		m.addAttribute("likeList", likeList);
		return null;
	}
}
