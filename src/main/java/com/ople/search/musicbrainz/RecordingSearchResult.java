package com.ople.search.musicbrainz;

import java.util.ArrayList;

public class RecordingSearchResult {
	int count;
	ArrayList<Recordings> recordings;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<Recordings> getRecordings() {
		return recordings;
	}
	public void setRecordings(ArrayList<Recordings> recordings) {
		this.recordings = recordings;
	}
	
	
}
