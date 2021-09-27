package com.ople.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity
@Data
public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long commentId;
	private Long playlistId;
	@Column(name="MEMBER_ID")
	private String memberId;
	private String content;
	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date commentDate;
	private Long likeCount;
	private Long commentLike;
   
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", insertable = false, updatable = false)
	Member member;


}