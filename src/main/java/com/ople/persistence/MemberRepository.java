package com.ople.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ople.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
