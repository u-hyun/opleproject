package com.ople.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String adminStat() {
		return "/admin/admin_stat";
	}
	
	@RequestMapping("/admin_member")
	public String adminMember() {
		return "/admin/admin_member";
	}
	
	@RequestMapping("/admin_playlist")
	public String adminPlaylist() {
		return "/admin/admin_playlist";
	}
	
	@RequestMapping("/admin_member_search")
	@ResponseBody
	public List<Member> adminMemberSearch(@RequestParam String keyword){
		List<Member> resultList = memberService.findByIdAndNickname(keyword);
		return resultList;
	}
}
