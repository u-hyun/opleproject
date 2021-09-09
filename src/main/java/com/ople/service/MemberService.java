package com.ople.service;

import java.util.Optional;

import com.ople.domain.Member;

public interface MemberService {
	Member saveMember(Member member);

	Member getMember(Member member);

	Optional<Member> findMember(String memberId);

	void delete(Member member);
	
	//마이페이지에서 저장된 닉네임 불러오기
	Member getNickname(Member member);

}
