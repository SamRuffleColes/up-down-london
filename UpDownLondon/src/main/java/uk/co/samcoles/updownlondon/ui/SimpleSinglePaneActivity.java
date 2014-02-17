package uk.co.samcoles.updownlondon.ui;

import uk.co.samcoles.updownlondon.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public abstract class SimpleSinglePaneActivity extends Activity {
	private Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_singlepane);
		initFragment(savedInstanceState);
	}

	private void initFragment(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			fragment = onCreatePane();
			getFragmentManager().beginTransaction().add(R.id.content_frame, fragment, "single_pane").commit();
		} else {
			fragment = getFragmentManager().findFragmentByTag("single_pane");
		}
	}

	protected abstract Fragment onCreatePane();

	public Fragment getFragment() {
		return fragment;
	}

}
