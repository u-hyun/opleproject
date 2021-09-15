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
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public Long getPlaycount() {
		return playcount;
	}
	public void setPlaycount(Long playcount) {
		this.playcount = playcount;
	}
	public Long getTodayPlaycount() {
		return todayPlaycount;
	}
	public void setTodayPlaycount(Long todayPlaycount) {
		this.todayPlaycount = todayPlaycount;
	}
	public String getTopTags() {
		return topTags;
	}
	public void setTopTags(String topTags) {
		this.topTags = topTags;
	}
	public Long getTrackCount() {
		return trackCount;
	}
	public void setTrackCount(Long trackCount) {
		this.trackCount = trackCount;
	}
	
	
   
}
