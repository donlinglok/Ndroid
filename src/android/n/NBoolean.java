package android.n;

public class NBoolean {
	public static final Boolean parse(final Object obj) {
		return parse(obj, "false");
	}

	public static final Boolean parse(final Object obj, final String def) {
		Boolean result = Boolean.parseBoolean(def);
		try {
			result = Boolean.parseBoolean(NString.parse(obj, def));
		} catch (final Exception e) {
		}
		return result;
	}
}
