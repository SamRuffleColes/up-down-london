package uk.co.samcoles.updownlondon.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.content.Context;
import android.util.Log;

public class ProblemsStore {

	private static final String TAG = "uk.co.samcoles.updownlondon.model.ProblemsStore";
	private static final String FILE_NAME = "UpDownLondon.problemsStore";
	private Context context;

	public ProblemsStore(Context context) {
		this.context = context;
	}

	public void writeProblems(Serializable object) {
		try {
			FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.close();
		} catch (Exception e) {
			Log.e(TAG, "failed to write object to Problems Store");
		}
	}

	public ProblemSet readProblems() {
		try {
			FileInputStream fis = context.openFileInput(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ProblemSet problemSet = (ProblemSet) ois.readObject();
			ois.close();
			return problemSet;
		} catch (FileNotFoundException e) {
			Log.i(TAG, "Problems Store file not found (may just be first read): " + e.toString());
		} catch (Exception e) {
			Log.e(TAG, "failed to read object from Problems Store: " + e.toString());
		}
		return null;
	}
}
