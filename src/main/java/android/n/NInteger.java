package android.n;

import android.util.Log;

public class NInteger {
	public static final Integer parse(final Object obj) {
		return parse(obj, "0");
	}

	public static final Integer parse(final Object obj, final String def) {
		Integer result = Integer.parseInt(def);
		try {
			result = Integer.parseInt(NString.parse(obj, def));
		} catch (final Exception e) {
			Log.e("", "", e);
		}
		return result;
	}
}
