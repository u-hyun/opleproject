package com.ople.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ople.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	Member findByMemberId(String memberId);
	List<Member> findByMemberNicknameContainingIgnoreCase(String memberNickname);
}
