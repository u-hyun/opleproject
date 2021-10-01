package com.ople.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * Entity implementation class for Entity: LikedPlaylist
 *
 */
@Entity
@Data
public class LikedPlaylist implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long likedPlaylistId;
	private String memberId;
	private Long playlistId;
	
	public Long getLikedPlaylistId() {
		return likedPlaylistId;
	}
	public void setLikedPlaylistId(Long likedPlaylistId) {
		this.likedPlaylistId = likedPlaylistId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Long getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}
	
	
}
