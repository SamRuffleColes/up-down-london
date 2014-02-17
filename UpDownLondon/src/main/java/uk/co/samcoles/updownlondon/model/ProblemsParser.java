package uk.co.samcoles.updownlondon.model;

import java.util.Collections;

import com.google.gson.Gson;

public class ProblemsParser {

	private String json;
	private Gson gson;

	public ProblemsParser(String json) {
		this.json = json;
		gson = new Gson();
	}

	public ProblemSet parse() throws Exception {
		ProblemSet problemSet = gson.fromJson(json, ProblemSet.class);
		Collections.sort(problemSet.getProblems(), new SortProblemsComparator());
		return problemSet;
	}

}
