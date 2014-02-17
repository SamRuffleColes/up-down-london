package uk.co.samcoles.updownlondon.async;

import uk.co.samcoles.updownlondon.model.ProblemSet;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

public class ProblemsLoaderCallbacks implements LoaderCallbacks<AsyncResult<ProblemSet>> {

	private Context context;
	private OnLoadFinishedListener<ProblemSet> onLoadFinishedListener;
	private String feedUrl;

	public ProblemsLoaderCallbacks(Context context, OnLoadFinishedListener<ProblemSet> onLoadFinishedListener,
			String feedUrl) {
		this.context = context;
		this.onLoadFinishedListener = onLoadFinishedListener;
		this.feedUrl = feedUrl;
	}

	@Override
	public Loader<AsyncResult<ProblemSet>> onCreateLoader(int id, Bundle args) {
		return new ProblemsLoader(context, feedUrl);
	}

	@Override
	public void onLoadFinished(Loader<AsyncResult<ProblemSet>> loader, AsyncResult<ProblemSet> result) {
		onLoadFinishedListener.onLoadFinished(result);
	}

	@Override
	public void onLoaderReset(Loader<AsyncResult<ProblemSet>> loader) {
		loader.reset();
	}

}
