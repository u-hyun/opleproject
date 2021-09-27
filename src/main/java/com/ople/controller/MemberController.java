package com.ople.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ople.domain.EmailVO;
import com.ople.domain.Member;
import com.ople.service.MemberService;

@SessionAttributes("member")
@Controller
public class MemberController implements ApplicationContextAware {

	private WebApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}

	@Autowired
	private MemberService memberService;

	
	@ModelAttribute("member")
	public Member getMember() { 
		return new Member(); }
	 

	@GetMapping("/joinView")
	public String View() {
		return "member/joinView";
	}
	
	@GetMapping("/pwFindView")
	public String pwFindView() {
		return "member/pwFindView";
	}
	

	@RequestMapping("/pwChangeView")
	public String pwChangeView() {
		return "member/pwChangeView";
	}
	
	@PostMapping("/pwChange")
	public String pwChange(@ModelAttribute("member") Member member, String newPw) {
		memberService.saveMember(member, newPw);
		return "redirect:index.html";
	}
	
	

	@RequestMapping("/check_id")
	@ResponseBody
	public String check_id(String memberId) {

		Optional<Member> member = memberService.findMember(memberId);
		Member mem = member.orElse(new Member());
		return mem.getMemberId();// 값이 없으면(null) ""로 전송된다.
	}

	@PostMapping("/join")
	public String join(Member member, Model m) {
		Date today = java.sql.Timestamp.valueOf(LocalDateTime.now());
		member.setJoinDate(today);
		member.setAdmin('0');
		Member mem = memberService.saveMember(member);

		return "redirect:loginform";
	}

	@GetMapping("/loginform")
	public String loginView(@ModelAttribute("member") Member member) {

		return "member/loginView";
	}

	@RequestMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = memberService.getMember(member);
		if (findMember != null && findMember.getMemberPw().equals(member.getMemberPw())) {
			model.addAttribute("member", findMember);
			model.addAttribute("loginFailed", false);
//			HttpSession session = request.getSession();
//			session.setAttribute("member", findMember);
      
			//getBoardList대신 메인페이지 경로로 바꿔줄 것
			return "redirect:/";
		} else {
			model.addAttribute("loginFailed", true);
			return "member/loginView";
		}
	}
  
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();// @SessionAttributes를 활용해 Session에 남긴 데이터를 정리
		//index.html 대신에 메인? 넣을것
		return "redirect:/";
  }
	
	@RequestMapping("/mypageView")
	public String mypageView(@ModelAttribute("member") Member member, Model m) {
		
		//태그
		String tags = member.getLikedTags();
		String[] tagsList;
		if (tags != null)
			tagsList = tags.split(",");
		else
			tagsList = new String[1];
		m.addAttribute("tagsList",tagsList);
		
		return "member/mypageView";
	}
	
	
	
	@PostMapping("/updateForm")
	public String update(@ModelAttribute("member") Member member, MultipartFile pImage, @RequestParam(value="likedTags", required=false) String tags) {
		
		String id = member.getMemberId();
		
		//프로필사진
		if(pImage.getOriginalFilename() != "") {
		String path = getPath(pImage);
		
	
		System.out.println("ID::"+id);
		//memberService.profileImage(path, id);

		member.setImagePath(path);
		}
		
//		//태그 수정 (Original)
//		for (int i=0; i<tags.length; i++) {
//		    member.setLikedTags(tags[i]);
//		}
		
		//태그 수정
		/*
		 * for (int i=0; i<tags.length; i++) {
		 * 
		 * }
		 */
		member.setLikedTags(tags);
		
		
		memberService.saveMember(member);
		return "redirect:/";
	}

	// 사진 업로드 관련
	// 실제 업로드할 경로 만드는 부분
	private String getPath(MultipartFile pImage) {

		String oriName = pImage.getOriginalFilename(); // 저장 된 파일의 원본 이름
		
		int index = oriName.lastIndexOf(".");
		String ext = oriName.substring(index + 1); // 파일 이름 겹치지 않게 지정
		Random r = new Random();
		String fileName = System.currentTimeMillis() + "_" + r.nextInt(50) + "." + ext;

		String path = context.getServletContext().getRealPath("/img/profileImg/" + fileName);
		System.out.println("PATH::"+path);
		try {
			pImage.transferTo(new File(path));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return "/img/profileImg/" + fileName;
	}

	@RequestMapping("/deleteMemberView")
	public String deleteView() {
		return "member/deleteMemberView";
	}
	
	
	@PostMapping("/deleteMember")
	public String delete(@ModelAttribute("model") Member member, String memberPw, SessionStatus status) {
		Member findMember = memberService.getMember(member);
		if (findMember != null && findMember.getMemberPw().equals(memberPw)) {
			status.setComplete();
			memberService.delete(member);
		} else {
			return "redirect:/deleteMemberView";
		}
		return "redirect:/";
	}
	
	
	

}
