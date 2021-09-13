package com.ople.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ople.domain.Member;
import com.ople.service.MemberService;
@SessionAttributes("member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@ModelAttribute("member")
	public Member getMember() {
		return new Member();
	}
	
	@GetMapping("/joinView")
	public String View() {
		return "member/joinView";
	}
	
	@GetMapping("/mypageView")
	public String mypageView() {
		return "member/mypageView";
	}
	
	@RequestMapping("/check_id")
	@ResponseBody
	public String check_id(String memberId) {
	
		Optional<Member> member = memberService.findMember(memberId);
		Member mem =  member.orElse(new Member());
		return mem.getMemberId();//값이 없으면(null) ""로 전송된다.
	}
	
	@PostMapping("/join")
	public String join(Member member, Model m) {
		Date today = java.sql.Timestamp.valueOf(LocalDateTime.now());
		member.setJoinDate(today);
		Member mem = memberService.saveMember(member);
		
		return "redirect:loginform";
	}  
	
	@GetMapping("/loginform")
	public String loginView(@ModelAttribute("member")Member member) {
		
		return "member/loginView";
	}
	
	@RequestMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = memberService.getMember(member);
		if(findMember != null && findMember.getMemberPw().equals(member.getMemberPw())) {
			model.addAttribute("member", findMember);
//			HttpSession session = request.getSession();
//			session.setAttribute("member", findMember);
			//getBoardList대신 메인페이지 경로로 바꿔줄 것
			return "redirect:index.html";
		}else {
			return "redirect:loginform";
		}
	}

	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();// @SessionAttributes를 활용해 Session에 남긴 데이터를 정리
		//index.html 대신에 메인? 넣을것
		return "redirect:index.html";		
	}
	
	@PostMapping("/updateForm")
	public String update(@ModelAttribute("member") Member member) {
		memberService.saveMember(member);
		return "redirect:index.html";
	}
	
	@GetMapping("/deleteMember")
	public String delete(@ModelAttribute("model") Member member, String password, SessionStatus status) {
		Member findMember = memberService.getMember(member);
		if(findMember != null && findMember.getMemberPw().equals(password)) {
			status.setComplete();
			memberService.delete(member);
		}else {
			return "redirect:/updateForm";
		}
		return "redirect:index.html";
	}
	
}
