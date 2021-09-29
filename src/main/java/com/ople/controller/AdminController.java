package com.ople.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ople.domain.Member;
import com.ople.service.MemberService;

@Controller
public class AdminController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/admin")
	public String admin() {
		return "/admin/admin";
	}
	
	@RequestMapping("/admin_stat")
	public String adminStat(Model m) {
		m.addAttribute("memberCount", memberService.countMember());
		List<Long> memberCountList = new ArrayList<>();
		List<Long> newMemberCountList = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			Date targetdate = Date.valueOf(LocalDate.now().minus(5-i, ChronoUnit.MONTHS));
			Long count = memberService.countByDateUpTo(targetdate);
			memberCountList.add(count);
		}
		for(int i = 0; i < 6; i++) {
			Date today = Date.valueOf(LocalDate.now());
			Date targetdatestart = Date.valueOf(LocalDate.now().minus(6-i, ChronoUnit.MONTHS));
			Date targetdateend = Date.valueOf(LocalDate.now().minus(5-i, ChronoUnit.MONTHS));
			Long count = memberService.countByDate(
					targetdatestart, 
					targetdateend);
			System.out.println("Count: " + count);
			newMemberCountList.add(count);
		}
		
		String[] tags = {"rock", "electronic", "pop", "funk", "metal", "jazz", "hip-hop", "classical", "blues", "acoustic", "instrumental", "soundtrack"};
		List<Long> likedTagCountList = new ArrayList<>();
		for(int i = 0; i < 12; i++) {
			long count = memberService.countLikedTag(tags[i]);
			likedTagCountList.add(count);
		}
		m.addAttribute("memberCountList", memberCountList);
		m.addAttribute("newMemberCountList", newMemberCountList);
		m.addAttribute("likedTagCountList", likedTagCountList);
		return "/admin/admin_stat";
	}
	
	@RequestMapping("/admin_member")
	public String adminMember() {
		return "/admin/admin_member";
	}
	
	@RequestMapping("/admin_member_search")
	@ResponseBody
	public List<Member> adminMemberSearch(@RequestParam String keyword){
		List<Member> resultList = memberService.findByIdAndNickname(keyword);
		return resultList;
	}
	
	@RequestMapping("/admin_member_get")
	@ResponseBody
	public Member adminMemberGet(@RequestParam String memberId) {
		Member member = memberService.findMember(memberId).get();
		return member;
	}
	
	@RequestMapping("/admin_member_delete")
	@ResponseBody
	public void adminMemberDelete(@RequestParam String memberId) {
		memberService.delete(memberService.findMember(memberId).get());
	}
	
	@RequestMapping("/admin_member_makeadmin")
	@ResponseBody
	public void adminMemberMakeAdmin(@RequestParam String memberId) {
		Member member = memberService.findMember(memberId).get();
		member.setAdmin('1');
		memberService.saveMember(member);
	}
	
	@RequestMapping("/admin_playlist")
	public String adminPlaylist() {
		return "/admin/admin_playlist";
	}
	
}

