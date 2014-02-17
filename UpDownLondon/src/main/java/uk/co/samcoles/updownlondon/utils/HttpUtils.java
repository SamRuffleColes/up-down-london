package uk.co.samcoles.updownlondon.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public abstract class HttpUtils {

	private static final String TAG = "uk.co.samcoles.updownlondon.utils.HttpUtils";

	public static String get(String fileUrl) {
		try {
			URL url = new URL(fileUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			try {
				InputStream feedStream = new BufferedInputStream(urlConnection.getInputStream());
				return convertInputStreamToString(feedStream);
			} finally {
				urlConnection.disconnect();
			}
		} catch (Exception e) {
			Log.e(TAG, "Failed to download " + fileUrl + ": " + e.toString());
			return null;
		}
	}

	private static String convertInputStreamToString(InputStream stream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		StringBuilder total;
		try {
			total = new StringBuilder(stream.available());
			String line;
			while ((line = reader.readLine()) != null) {
				total.append(line);
			}
			return total.toString();
		} catch (IOException e) {
			Log.e(TAG, e.toString());
			return null;
		}
	}

}
