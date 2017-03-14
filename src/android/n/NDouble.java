package android.n;

import android.util.Log;

public class NDouble {
	public static final Double parse(final Object obj) {
		return parse(obj, "0");
	}

	public static final Double parse(final Object obj, final String def) {
		Double result = Double.parseDouble(def);
		try {
			result = Double.parseDouble(NString.parse(obj, def));
		} catch (final Exception e) {
			Log.e("", "", e);
		}
		return result;
	}
}
