package android.n;

import android.util.Log;

public class NBoolean {
	public static final Boolean parse(final Object obj) {
		return parse(obj, "false");
	}

	public static final Boolean parse(final Object obj, final String def) {
		Boolean result = Boolean.parseBoolean(def);
		try {
			result = Boolean.parseBoolean(NString.parse(obj, def));
		} catch (final Exception e) {
			Log.e("", "", e);
		}
		return result;
	}
}
