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
	private String playlistTrackId;
	private String memberid;
	private Long playlistId;
	private Long listOrder;
	private Long trackId;
   
}
