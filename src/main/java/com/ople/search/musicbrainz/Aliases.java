package com.ople.search.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Aliases {
	@JsonProperty("sort-name")
	String sortname;
	String name;
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
