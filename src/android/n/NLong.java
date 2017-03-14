package android.n;

import android.util.Log;

public class NLong {
	public static final Long parse(final Object obj) {
		return parse(obj, "0");
	}

	public static final Long parse(final Object obj, final String def) {
		Long result = Long.parseLong(def);
		try {
			result = Long.parseLong(NString.parse(obj, def));
		} catch (final Exception e) {
			Log.e("", "", e);
		}
		return result;
	}
}
