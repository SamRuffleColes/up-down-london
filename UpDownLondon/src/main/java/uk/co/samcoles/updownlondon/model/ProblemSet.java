package uk.co.samcoles.updownlondon.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ProblemSet implements Serializable {

	private static final long serialVersionUID = -7779299944552677798L;

	@SerializedName("_last_updated")
	private String lastUpdated;

	@SerializedName("problems")
	private List<Problem> problems;

	public String getLastUpdated() {
		return lastUpdated;
	}

	public List<Problem> getProblems() {
		return problems;
	}

}
