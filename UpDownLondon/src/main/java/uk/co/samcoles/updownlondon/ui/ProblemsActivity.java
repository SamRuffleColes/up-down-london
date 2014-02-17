package uk.co.samcoles.updownlondon.ui;

import uk.co.samcoles.updownlondon.R;
import android.app.Fragment;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ProblemsActivity extends SimpleSinglePaneActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_problems, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_about:
			startAboutActivity();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void startAboutActivity() {
		Intent intent = new Intent(this, AboutActivity.class);
		startActivity(intent);
	}

	@Override
	protected Fragment onCreatePane() {
		return new ProblemsFragment();
	}

}
