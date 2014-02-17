package uk.co.samcoles.updownlondon.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import uk.co.samcoles.updownlondon.R;
import uk.co.samcoles.updownlondon.model.Problem;
import uk.co.samcoles.updownlondon.ui.widget.ResolvedBarView;
import uk.co.samcoles.updownlondon.utils.Typefaces;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProblemsAdapter extends BaseAdapter {

	private Context context;
	private List<Problem> problems;

	public ProblemsAdapter(Context context) {
		problems = new ArrayList<Problem>();
		this.context = context;
	}

	public void setProblems(List<Problem> problems) {
		this.problems = problems;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return problems.size();
	}

	@Override
	public Object getItem(int position) {
		return problems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			v = inflateNewView(parent);
		}
		Problem problem = (Problem) getItem(position);
		ViewHolder viewHolder = (ViewHolder) v.getTag();
		viewHolder.bar.setResolved(problem.isResolved());
		viewHolder.station.setText(problem.getStation());
		viewHolder.info.setText(problem.getTrackernetText());
		String formattedTime = formatTimeString(problem.getTrackernetTime());
		viewHolder.time.setText(formattedTime);
		return v;
	}

	private View inflateNewView(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.li_problem, parent, false);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.bar = (ResolvedBarView) v.findViewById(R.id.bar);
		viewHolder.station = (TextView) v.findViewById(R.id.station);
		viewHolder.info = (TextView) v.findViewById(R.id.info);
		viewHolder.time = (TextView) v.findViewById(R.id.time);
		setFonts(viewHolder);
		v.setTag(viewHolder);
		return v;
	}

	private void setFonts(ViewHolder viewHolder) {
		viewHolder.station.setTypeface(Typefaces.get(context, "fonts/Roboto-Bold.ttf"));
		viewHolder.info.setTypeface(Typefaces.get(context, "fonts/Roboto-Light.ttf"));
		viewHolder.time.setTypeface(Typefaces.get(context, "fonts/RobotoCondensed-Light.ttf"));
	}

	private String formatTimeString(String timeString) {
		try {
			DateTime time = DateTime.parse(timeString);
			DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm, dd MMM");
			return time.toString(formatter);
		} catch (Exception e) {
			return "";
		}
	}

	private class ViewHolder {
		ResolvedBarView bar;
		TextView station;
		TextView info;
		TextView time;
	}

}
