package com.ople.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * Entity implementation class for Entity: Playlist
 *
 */
@Entity
@Data
public class Playlist implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long playlistId;
	private String playlistName;
	private String memberId;
	private String description;
	private Long viewCount;
	private Long likeCount;
	private String customTag;
	
	public Long getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}
	public String getPlaylistName() {
		return playlistName;
	}
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getViewCount() {
		return viewCount;
	}
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	public Long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}
	public String getCustomTag() {
		return customTag;
	}
	public void setCustomTag(String customTag) {
		this.customTag = customTag;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
