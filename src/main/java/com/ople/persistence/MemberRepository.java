package com.ople.persistence;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ople.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	Member findByMemberId(String memberId);
	List<Member> findByMemberNicknameContainingIgnoreCase(String memberNickname);
	List<Member> findByMemberIdContainingIgnoreCase(String memberId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Member m SET m.imagePath = ?1 where m.memberId = ?2")
	int imageUpoload(String imagePath, String memberId);
	
	// 통계용
	long count();
	long countByJoinDateBetween(Date start, Date end);
	long countByJoinDateLessThanEqual(Date date);
	long countByLikedTagsContaining(String tag);
	
}
