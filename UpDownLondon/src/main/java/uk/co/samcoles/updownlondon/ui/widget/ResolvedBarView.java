package uk.co.samcoles.updownlondon.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ResolvedBarView extends View {

	private Paint paint;

	public ResolvedBarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ResolvedBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ResolvedBarView(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
	}

	public void setResolved(boolean isResolved) {
		paint.setColor(isResolved ? Color.GREEN : Color.RED);
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawRect(0, 0, getWidth(), getBottom(), paint);
	}

}
