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
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Long getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public Long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}
	public Long getCommentLike() {
		return commentLike;
	}
	public void setCommentLike(Long commentLike) {
		this.commentLike = commentLike;
	}
   
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", insertable = false, updatable = false)
	Member member;


}