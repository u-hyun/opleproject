package com.ople.service;

import com.ople.domain.Track;

public interface TrackService {
	Track saveTrack(Track track);
	boolean checkTrack(Track track);
}
