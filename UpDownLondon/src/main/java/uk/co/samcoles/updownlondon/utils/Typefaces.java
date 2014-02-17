package uk.co.samcoles.updownlondon.utils;

import java.util.Hashtable;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

public class Typefaces {

	private static final String TAG = "Typefaces";

	private static final Hashtable<String, Typeface> TYPEFACES = new Hashtable<String, Typeface>();

	public static Typeface get(Context context, String assetPath) {
		synchronized (TYPEFACES) {
			if (!TYPEFACES.containsKey(assetPath)) {
				try {
					Typeface typeface = Typeface.createFromAsset(context.getAssets(), assetPath);
					TYPEFACES.put(assetPath, typeface);
				} catch (Exception e) {
					Log.e(TAG, "Could not get typeface '" + assetPath + "' because " + e.getMessage());
					return null;
				}
			}
			return TYPEFACES.get(assetPath);
		}
	}

}
