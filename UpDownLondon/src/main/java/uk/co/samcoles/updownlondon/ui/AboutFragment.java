package uk.co.samcoles.updownlondon.ui;

import uk.co.samcoles.updownlondon.R;
import uk.co.samcoles.updownlondon.utils.Typefaces;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AboutFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_about, container, false);

		Button feedbackButton = (Button) v.findViewById(R.id.feedback);
		feedbackButton.setOnClickListener(new FeedbackClickListener());

		TextView furtherInfoLink = (TextView) v.findViewById(R.id.furtherInfo);
		furtherInfoLink.setOnClickListener(new OpenWebsiteFAQListener());

		applyRobotoTypefaceToAllViewsRecursively(v);

		return v;
	}

	private void applyRobotoTypefaceToAllViewsRecursively(View view) {
		if (view instanceof ViewGroup) {
			ViewGroup vg = (ViewGroup) view;
			for (int i = 0; i < vg.getChildCount(); i++) {
				View child = vg.getChildAt(i);
				applyRobotoTypefaceToAllViewsRecursively(child);
			}
		} else if (view instanceof TextView || view instanceof Button) {
			TextView tv = (TextView) view;
			tv.setTypeface(Typefaces.get(getActivity(), "fonts/Roboto-Light.ttf"));
		}
	}

	private void sendFeedbackEmail() {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		Resources r = getResources();
		emailIntent.putExtra(Intent.EXTRA_EMAIL, r.getStringArray(R.array.feedback_emails));
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, r.getString(R.string.feedback_subject));
		emailIntent.setType("message/rfc822");
		startActivity(Intent.createChooser(emailIntent, null));
	}

	private void openWebsiteFAQ() {
		String faqUrl = getString(R.string.url_faq);
		Uri uri = Uri.parse(faqUrl);

		Intent faqIntent = new Intent(Intent.ACTION_VIEW);
		faqIntent.setData(uri);
		startActivity(faqIntent);
	}

	private class FeedbackClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			sendFeedbackEmail();
		}
	}

	private class OpenWebsiteFAQListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			openWebsiteFAQ();
		}
	}

}
