package uk.co.samcoles.updownlondon.async;

public class AsyncResult<T> {

	protected final LoaderStatus status;
	protected final T data;
	protected final Exception exception;

	public AsyncResult(LoaderStatus status) {
		this.status = status;
		this.data = null;
		this.exception = null;
	}

	public AsyncResult(LoaderStatus status, T data) {
		this.status = status;
		this.data = data;
		this.exception = null;
	}

	public AsyncResult(LoaderStatus status, Exception exception) {
		this.status = status;
		this.data = null;
		this.exception = exception;
	}

	public LoaderStatus getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public Exception getException() {
		return exception;
	}

}
