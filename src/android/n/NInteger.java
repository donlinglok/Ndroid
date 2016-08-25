package android.n;

public class NInteger {
    public static Integer parse(final Object obj) {
	return parse(obj, "0");
    }

    public static Integer parse(final Object obj, final String def) {
	return Integer.parseInt(NString.parse(obj, def));
    }
}
