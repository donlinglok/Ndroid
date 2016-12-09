package android.n;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

public class NDialog extends Dialog {
	public final transient  Activity activity;
	public final transient  Context appContext;

	public NDialog(final Activity activity, final int resId) {
		super(activity, resId);
		this.activity = activity;
		appContext = activity.getApplicationContext();
		getWindow().setGravity(Gravity.CENTER);
	}
}
