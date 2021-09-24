package com.ople.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ople.domain.Member;

public interface MemberService {
	Member saveMember(Member member, String newPw);
	Member saveMember(Member member);

	Member getMember(Member member);

	Optional<Member> findMember(String memberId);

	void delete(Member member);
	
	Member getMemberById(String memberId);
	
	List<Member> findByMemberNicknameContainingIgnoreCase(String memberNickname);
  //마이페이지에서 저장된 닉네임 불러오기
	Member getNickname(Member member);
	
	void profileImage(String path, String id);
	
	List<Member> findByIdAndNickname(String keyword);
	
	long countMember();
	
	long countByDate(Date start, Date end);
	long countByDateUpTo(Date date);
	long countLikedTag(String tag);
}
