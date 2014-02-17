package uk.co.samcoles.updownlondon.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Problem implements Serializable {

	private static final long serialVersionUID = -6033677893032908583L;

	@SerializedName("trackernettext")
	private String trackernetText;

	@SerializedName("trackernettime")
	private String trackernetTime;

	@SerializedName("twittertime")
	private String twitterTime;

	@SerializedName("resolved")
	private boolean isResolved;

	@SerializedName("station")
	private String station;

	public String getTrackernetText() {
		if (trackernetText == null) {
			trackernetText = "";
		}
		return trackernetText;
	}

	public String getTrackernetTime() {
		if (trackernetTime == null) {
			trackernetTime = "";
		}
		return trackernetTime;
	}

	public String getTwitterTime() {
		if (twitterTime == null) {
			twitterTime = "";
		}
		return twitterTime;
	}

	public boolean isResolved() {
		return isResolved;
	}

	public String getStation() {
		if (station == null) {
			station = "";
		}
		return station;
	}

}
