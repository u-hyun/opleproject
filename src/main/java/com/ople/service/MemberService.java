package com.ople.service;

import java.util.Optional;

import com.ople.domain.Member;

public interface MemberService {
	Member saveMember(Member member);

	Member getMember(Member member);

	Optional<Member> findMember(String memberId);

	void delete(Member member);
	
	Member getMemberById(String memberId);

}