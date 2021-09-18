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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long playlistId;
	private String playlistName;
	private String memberId;
	private String description;
	private Long viewCount;
	private Long likeCount;
	private String customTag;
   
}
