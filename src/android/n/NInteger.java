package android.n;

public class NInteger {
	public static final Integer parse(final Object obj) {
		return parse(obj, "0");
	}

	public static final Integer parse(final Object obj, final String def) {
		Integer result = Integer.parseInt(def);
		try {
			result = Integer.parseInt(NString.parse(obj, def));
		} catch (final Exception e) {
		}
		return result;
	}
}
