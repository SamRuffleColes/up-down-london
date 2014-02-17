package uk.co.samcoles.updownlondon.async;

import uk.co.samcoles.updownlondon.model.ProblemSet;
import uk.co.samcoles.updownlondon.model.ProblemsStore;
import android.content.AsyncTaskLoader;
import android.content.Context;

public class ProblemsCacheLoader extends AsyncTaskLoader<AsyncResult<ProblemSet>> {

	private ProblemSet problemSet;
	private ProblemsStore store;

	public ProblemsCacheLoader(Context context) {
		super(context);
		store = new ProblemsStore(context);
	}

	@Override
	public void deliverResult(AsyncResult<ProblemSet> data) {
		if (isReset()) {
			return;
		}
		this.problemSet = data.getData();
		super.deliverResult(data);
	}

	@Override
	protected void onReset() {
		super.onReset();
		onStopLoading();
		problemSet = null;
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
	}

	@Override
	protected void onStopLoading() {
		cancelLoad();
	}

	@Override
	public AsyncResult<ProblemSet> loadInBackground() {
		try {
			problemSet = loadCachedProblemSetFromDisk();
			store.writeProblems(problemSet);
			return new AsyncResult<ProblemSet>(LoaderStatus.DISK, problemSet);
		} catch (Exception e) {
			return new AsyncResult<ProblemSet>(LoaderStatus.EXCEPTION, e);
		}
	}

	private ProblemSet loadCachedProblemSetFromDisk() {
		problemSet = store.readProblems();
		return problemSet;
	}

}
