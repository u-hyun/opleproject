package com.ople.search.musicbrainz;

import java.util.List;

public class Artists {
	String id;
	String name;
	List<Aliases> aliases;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Aliases> getAliases() {
		return aliases;
	}
	public void setAliases(List<Aliases> aliases) {
		this.aliases = aliases;
	}
	
}
