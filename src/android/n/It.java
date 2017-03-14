package android.n;

public class It {
	private It() {
	}

	public static final boolean isNull(final Object object) {
		return null == object || NString.parse(object).length() == 0;
	}

	public static final boolean isEqual(final Object objA, final Object objB) {
		if (isNull(objA)) {
			return objB.equals(objA);
		} else {
			return objA.equals(objB);
		}
	}
}
