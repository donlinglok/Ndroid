package android.n;

import android.util.Log;

public class NFloat {
	public static final Float parse(final Object obj) {
		return parse(obj, "0");
	}

	public static final Float parse(final Object obj, final String def) {
		Float result = Float.parseFloat(def);
		try {
			result = Float.parseFloat(NString.parse(obj, def));
		} catch (final Exception e) {
			Log.e("", "", e);
		}
		return result;
	}
}
