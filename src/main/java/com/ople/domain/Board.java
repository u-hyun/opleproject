package com.ople.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity
@Getter
@Setter
@ToString
public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long commentId;
	private Long playlistId;
	@Column(updatable = false)
	private String memberId;
	private String content;
	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date commentDate;
	private Long likeCount;
	private Long commentLike;
   
}