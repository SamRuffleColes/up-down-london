package uk.co.samcoles.updownlondon.async;

import uk.co.samcoles.updownlondon.model.ProblemSet;
import uk.co.samcoles.updownlondon.model.ProblemsParser;
import uk.co.samcoles.updownlondon.model.ProblemsStore;
import uk.co.samcoles.updownlondon.utils.HttpUtils;
import android.content.AsyncTaskLoader;
import android.content.Context;

public class ProblemsLoader extends AsyncTaskLoader<AsyncResult<ProblemSet>> {

	private String feedUrl;
	private ProblemSet problemSet;
	private ProblemsStore store;

	public ProblemsLoader(Context context, String feedUrl) {
		super(context);
		this.feedUrl = feedUrl;
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
			problemSet = fetchProblemSetFromFeed();
			store.writeProblems(problemSet);
			return new AsyncResult<ProblemSet>(LoaderStatus.NETWORK, problemSet);
		} catch (Exception e) {
			return new AsyncResult<ProblemSet>(LoaderStatus.EXCEPTION, e);
		}
	}

	private ProblemSet fetchProblemSetFromFeed() throws Exception {
		String json = HttpUtils.get(feedUrl);
		ProblemsParser parser = new ProblemsParser(json);
		ProblemSet problemSet = parser.parse();
		return problemSet;
	}

}
