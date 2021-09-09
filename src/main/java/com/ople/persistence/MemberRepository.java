package com.ople.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ople.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	Member findByMemberId(String memberId);
}