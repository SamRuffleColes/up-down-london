package uk.co.samcoles.updownlondon.ui;

import android.app.Fragment;

public class AboutActivity extends SimpleSinglePaneActivity {

	@Override
	protected Fragment onCreatePane() {
		return new AboutFragment();
	}

}
