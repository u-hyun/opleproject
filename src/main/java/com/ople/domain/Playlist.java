package com.ople.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Playlist
 *
 */
@Entity
@Getter
@Setter
public class Playlist implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private Long playlistId;
	private String playlistName;
	private String memberId;
	private String description;
	private Long viewCount;
	private Long likeCount;
	private String customTag;
   
}
