package com.ople.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * Entity implementation class for Entity: Track
 *
 */
@Entity
@Data
public class Track implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	String trackId;
	String url;
	String trackName;
	String artistName;
	String albumName;
	Long playcount;
	Long todayPlaycount;
	String topTags;
	Long trackCount;
	String coverimg;

}