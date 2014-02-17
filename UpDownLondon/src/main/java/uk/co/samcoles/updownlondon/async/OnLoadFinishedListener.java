package uk.co.samcoles.updownlondon.async;

public interface OnLoadFinishedListener<D> {
	public void onLoadFinished(AsyncResult<D> result);
}
