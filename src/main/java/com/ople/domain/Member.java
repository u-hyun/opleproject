package com.ople.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class Member implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MEMBER_ID")
	private String memberId;
	private String memberPw;
	private String memberName;
	private Date joinDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private char admin;
	private String memberNickname;
	private char gender;
	private String profileComment;
	private String likedTags;
	
	@Column(length=600)
	private String imagePath;
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public char getAdmin() {
		return admin;
	}
	public void setAdmin(char admin) {
		this.admin = admin;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getProfileComment() {
		return profileComment;
	}
	public void setProfileComment(String profileComment) {
		this.profileComment = profileComment;
	}
	public String getLikedTags() {
		return likedTags;
	}
	public void setLikedTags(String likedTags) {
		this.likedTags = likedTags;
	}
  
}
