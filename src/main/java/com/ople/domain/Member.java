package com.ople.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Member implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEMBERID")
	private String memberId;
	private String memberPw;
	private String memberName;
	private Date joinDate;
	private Date birthday;
	private char admin;
	private String memberNickname;
	private char gender;
	private String profileComment;
	private String likedTags;
   
}
