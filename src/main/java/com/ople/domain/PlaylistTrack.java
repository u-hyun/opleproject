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
	@Column(name="ROWID")
	private String rowid;	// Primary Key 가 없는 테이블이므로 열 번호 (rowid)를 PK로 씀
	private String memberId;
	private Long playlistId;
	private Long order;
	private Long trackId;
   
}
