package com.ople.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: PlaylistTrack
 *
 */
@Entity
@Getter
@Setter
public class PlaylistTrack implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String playlistTrackId;
	private String memberid;
	private Long playlistId;
	private Long listOrder;
	private String trackId;
	public String getPlaylistTrackId() {
		return playlistTrackId;
	}
	public void setPlaylistTrackId(String playlistTrackId) {
		this.playlistTrackId = playlistTrackId;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public Long getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}
	public Long getListOrder() {
		return listOrder;
	}
	public void setListOrder(Long listOrder) {
		this.listOrder = listOrder;
	}
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
