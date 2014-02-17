package uk.co.samcoles.updownlondon.model;

import java.util.Comparator;

public class SortProblemsComparator implements Comparator<Problem> {
	@Override
	public int compare(Problem lhs, Problem rhs) {

		if (lhs.isResolved() && !rhs.isResolved()) {
			return -1;
		}

		if (!lhs.isResolved() && rhs.isResolved()) {
			return 1;
		}

		return 0;
	}
}
