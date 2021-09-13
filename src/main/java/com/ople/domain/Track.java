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
	int playcount;
	int todayPlaycount;
	String topTags;
	int trackCount;
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
	public int getPlaycount() {
		return playcount;
	}
	public void setPlaycount(int playcount) {
		this.playcount = playcount;
	}
	public int getTodayPlaycount() {
		return todayPlaycount;
	}
	public void setTodayPlaycount(int todayPlaycount) {
		this.todayPlaycount = todayPlaycount;
	}
	public String getTopTags() {
		return topTags;
	}
	public void setTopTags(String topTags) {
		this.topTags = topTags;
	}
	public int getTrackCount() {
		return trackCount;
	}
	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}
	
	
   
}
