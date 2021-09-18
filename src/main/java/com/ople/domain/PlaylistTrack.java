package com.ople.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * Entity implementation class for Entity: PlaylistTrack
 *
 */
@Entity
@Data
public class PlaylistTrack implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long playlistTrackId;	// 자동생성 (숫자)
	private String memberId;		// 유저 이메일
	private Long playlistId;		// 자동생성 (숫자)
	private Long listOrder;			// 숫자
	private String trackId;			// recording mbid (문자열)
	
}