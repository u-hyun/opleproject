package com.ople.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * Entity implementation class for Entity: Tag
 *
 */
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


}