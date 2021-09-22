package com.ople.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;


@Entity
@Data
public class Tag implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long tagId;	// 자동생성 (숫자)
	private String trackId;	// 태그가 등록된 곡
	private String tagName;	// 태그명 (장르명)
	private String memberId;	// 태그를 등록한 회원
	
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
   
}
