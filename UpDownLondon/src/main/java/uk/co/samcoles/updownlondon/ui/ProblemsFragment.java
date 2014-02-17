package uk.co.samcoles.updownlondon.ui;

import uk.co.samcoles.updownlondon.R;
import uk.co.samcoles.updownlondon.async.AsyncResult;
import uk.co.samcoles.updownlondon.async.OnLoadFinishedListener;
import uk.co.samcoles.updownlondon.async.ProblemsCacheLoaderCallbacks;
import uk.co.samcoles.updownlondon.async.ProblemsLoaderCallbacks;
import uk.co.samcoles.updownlondon.model.ProblemSet;
import uk.co.samcoles.updownlondon.ui.adapter.ProblemsAdapter;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

public class ProblemsFragment extends ListFragment implements OnLoadFinishedListener<ProblemSet>, OnRefreshListener {

	private static final String TAG = "uk.co.samcoles.updownlondon.ui.ProblemsFragment";
	private static final int PROBLEMS_CACHE_LOADER_ID = 0;
	private static final int PROBLEMS_LOADER_ID = 1;

	private ProblemsAdapter adapter;
	private PullToRefreshLayout pullToRefresh;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initAdapter();
		loadCache();
		refreshData();
	}

	private void initAdapter() {
		adapter = new ProblemsAdapter(getActivity());
		setListAdapter(adapter);
	}

	private void loadCache() {
		LoaderManager loaderManager = getLoaderManager();
		loaderManager.initLoader(PROBLEMS_CACHE_LOADER_ID, null, new ProblemsCacheLoaderCallbacks(getActivity(), this,
				loaderManager));
	}

	private void refreshData() {
		String feedUrl = getString(R.string.feed_url);
		getLoaderManager().restartLoader(PROBLEMS_LOADER_ID, null,
				new ProblemsLoaderCallbacks(getActivity(), this, feedUrl));
	}

	@Override
	public void onRefreshStarted(View view) {
		refreshData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_pulltorefresh_list, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initPullToRefresh(view);
		initListViewLayoutAnimation();
	}

	private void initPullToRefresh(View view) {
		pullToRefresh = (PullToRefreshLayout) view.findViewById(R.id.pulltorefresh);
		ActionBarPullToRefresh.from(getActivity()).allChildrenArePullable().listener(this).setup(pullToRefresh);
	}

	private void initListViewLayoutAnimation() {
		AnimationSet animSet = (AnimationSet) AnimationUtils.loadAnimation(getActivity(), R.anim.layout_listview);
		LayoutAnimationController controller = new LayoutAnimationController(animSet, 0.5f);
		getListView().setLayoutAnimation(controller);
	}

	@Override
	public void onLoadFinished(final AsyncResult<ProblemSet> result) {
		switch (result.getStatus()) {
		case NETWORK:
			pullToRefresh.setRefreshComplete();
		case DISK:
			onLoadSuccess(result.getData());
			break;
		case EXCEPTION:
			onLoadException(result.getException());
			break;
		}
	}

	private void onLoadSuccess(ProblemSet problemSet) {
		adapter.setProblems(problemSet.getProblems());
	}

	private void onLoadException(Exception exception) {
		try {
			throw exception;
		} catch (Exception e) {
			// TODO handle thems exceptions
			Log.e(TAG, e.toString());
		}
	}

}
