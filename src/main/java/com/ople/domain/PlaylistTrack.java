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
	private Long playlistTrackId;	// 자동생성 (숫자)
	private String memberId;		// 유저 이메일
	private Long playlistId;		// 자동생성 (숫자)
	private Long listOrder;			// 숫자
	private String trackId;			// recording mbid (문자열)
	public Long getPlaylistTrackId() {
		return playlistTrackId;
	}
	public void setPlaylistTrackId(Long playlistTrackId) {
		this.playlistTrackId = playlistTrackId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberid) {
		this.memberId = memberid;
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
	@Override
	public String toString() {
		return "PlaylistTrack [playlistTrackId=" + playlistTrackId + ", memberId=" + memberId + ", playlistId="
				+ playlistId + ", listOrder=" + listOrder + ", trackId=" + trackId + "]";
	}
	
	
	
}
