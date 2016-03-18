package android.support.v1;

import android.app.Activity;
import android.view.View;

public class ActivityN extends Activity {
	public View getView(final int id) {
		return super.findViewById(id);
	}
}
