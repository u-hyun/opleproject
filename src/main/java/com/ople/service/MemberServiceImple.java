package com.ople.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ople.domain.Member;
import com.ople.persistence.MemberRepository;

@Service
public class MemberServiceImple implements MemberService {
	
	@Autowired
	MemberRepository memberRepo;
	
	//비밀번호 변경
	@Override
	public Member saveMember(Member member, String newPw) {
		member.setMemberPw(newPw);
		return memberRepo.save(member);
	}
	
	@Override
	public Member saveMember(Member member) {
		return memberRepo.save(member);
	}

	@Override
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getMemberId());
		if(findMember.isPresent())
			return findMember.get();
		else
			return null;
	}

	@Override
	public Optional<Member> findMember(String memberId) {
		Optional<Member> member = memberRepo.findById(memberId);
		return member;
	}

	@Override
	public void delete(Member member) {
		memberRepo.delete(member);

	}

	@Override
	public Member getMemberById(String memberId) {
		return memberRepo.findByMemberId(memberId);
	}

	@Override
	public List<Member> findByMemberNicknameContainingIgnoreCase(String memberNickname) {
		return memberRepo.findByMemberNicknameContainingIgnoreCase(memberNickname);
	}

	public Member getNickname(Member member) {
		return null;
	}

	@Override
	public void profileImage(String path, String id) {
		memberRepo.imageUpoload(path, id);
	}

	@Override
	public List<Member> findByIdAndNickname(String keyword) {
		List<Member> idList = memberRepo.findByMemberIdContainingIgnoreCase(keyword);
		List<Member> nickList = memberRepo.findByMemberNicknameContainingIgnoreCase(keyword);
		idList.addAll(nickList);
		List<Member> memberList = new ArrayList<>(new HashSet<>(nickList)); // 중복 제거
		return memberList;
	}
	
}