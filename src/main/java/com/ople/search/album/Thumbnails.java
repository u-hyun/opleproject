package com.ople.search.album;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thumbnails {
	@JsonProperty("small")
	String thumbnailUrl;

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	
	
}
