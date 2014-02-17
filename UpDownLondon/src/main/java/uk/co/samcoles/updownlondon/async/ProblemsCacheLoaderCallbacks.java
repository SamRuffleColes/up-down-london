package uk.co.samcoles.updownlondon.async;

import uk.co.samcoles.updownlondon.model.ProblemSet;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

public class ProblemsCacheLoaderCallbacks implements LoaderCallbacks<AsyncResult<ProblemSet>> {

	private Context context;
	private OnLoadFinishedListener<ProblemSet> onLoadFinishedListener;
	private LoaderManager loaderManager;

	public ProblemsCacheLoaderCallbacks(Context context, OnLoadFinishedListener<ProblemSet> onLoadFinishedListener,
			LoaderManager loaderManager) {
		this.context = context;
		this.onLoadFinishedListener = onLoadFinishedListener;
		this.loaderManager = loaderManager;
	}

	@Override
	public Loader<AsyncResult<ProblemSet>> onCreateLoader(int id, Bundle args) {
		return new ProblemsCacheLoader(context);
	}

	@Override
	public void onLoadFinished(Loader<AsyncResult<ProblemSet>> loader, AsyncResult<ProblemSet> result) {
		onLoadFinishedListener.onLoadFinished(result);
		loaderManager.destroyLoader(loader.getId());
	}

	@Override
	public void onLoaderReset(Loader<AsyncResult<ProblemSet>> loader) {
		loader.reset();
	}

}
