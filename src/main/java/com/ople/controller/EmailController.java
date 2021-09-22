package com.ople.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ople.domain.EmailVO;
import com.ople.domain.Member;
import com.ople.service.EmailService;
import com.ople.service.MemberService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/send")
	public String[] sendMail(String emailAddress) throws Exception{
		
		EmailVO email = new EmailVO();
		
		String receiver = emailAddress; //(Receiver) opleteam4@gmail.com 
		String subject = "Ople 인증번호";
		String number = makeRandom();
		String content = "인증 번호는 " + number + "입니다.";
		
		email.setReceiver(receiver);
		email.setSubject(subject);
		email.setContent(content);
		
		Boolean result = emailService.sendMail(email);
		
		return new String[] {number, result.toString()};
	}
	
	//비밀번호 찾기
	@RequestMapping("/pwSend")
	public String[] sendPw(String emailAddress, String number) throws Exception{
				
		EmailVO email = new EmailVO();
		
		String receiver = emailAddress; //(Receiver) opleteam4@gmail.com 
		String subject = "Ople 비밀번호 찾기";
		
		String content = "회원님의 비밀번호는 " + number + "입니다.";
		
		email.setReceiver(receiver);
		email.setSubject(subject);
		email.setContent(content);
		
		//콘솔창에 띄워서 확인할 용도
		System.out.println(number);
		
		Boolean result = emailService.sendMail(email);
		
		return new String[] {number, result.toString()};
		
	}
	
	@RequestMapping("/checkname")
	public String[] checkName(String emailAddress, String memberName) {
		System.out.println(emailAddress);
		Member member = memberService.getMemberById(emailAddress);
		
		Boolean result = false;
		
		String password = "";
		
		if(member.getMemberName().equals(memberName)) {
			result = true;
			password = member.getMemberPw();
		}
		
		return new String[] {password, (result.toString())};
		
	}
	
	
	private String makeRandom() {
		Random r = new Random();
		String number = "";
		for(int i = 0; i < 6; i++) {
			number += r.nextInt(10);
		}
		System.out.println("number: " + number);
		return number;
	}

}
