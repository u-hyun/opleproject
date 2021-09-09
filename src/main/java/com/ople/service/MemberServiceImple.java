package com.ople.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ople.domain.Member;
import com.ople.persistence.MemberRepository;

@Service
public class MemberServiceImple implements MemberService {
	
	@Autowired
	MemberRepository memberRepo;

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
	public Member getNickname(Member member) {
		return null;
	}

}
