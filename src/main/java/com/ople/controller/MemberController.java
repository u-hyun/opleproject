package com.ople.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ople.domain.Member;
import com.ople.service.MemberService;
@SessionAttributes("member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/loginform")
	public String loginView(@ModelAttribute("member")Member member) {
		
		return "member/loginView";
	}
	
	@RequestMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = memberService.getMember(member);
		
		if(findMember != null && findMember.getMemberPw().equals(member.getMemberPw())) {
			model.addAttribute("member", findMember);
			//getBoardList대신 메인페이지 경로로 바꿔줄 것
			return "redirect:getBoardList";
		}else {
			return "redirect:loginform";
		}
	}

	
}
