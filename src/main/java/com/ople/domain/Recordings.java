package com.ople.domain;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recordings {
	String id;
	String title;
	@JsonProperty("artist-credit")
	ArrayList<ArtistCredit> artistcredit;
	ArrayList<Releases> releases;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<ArtistCredit> getArtistcredit() {
		return artistcredit;
	}
	public void setArtistcredit(ArrayList<ArtistCredit> artistcredit) {
		this.artistcredit = artistcredit;
	}
	public ArrayList<Releases> getReleases() {
		return releases;
	}
	public void setReleases(ArrayList<Releases> releases) {
		this.releases = releases;
	}
	
	
	
	
}
