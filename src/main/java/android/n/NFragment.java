package android.n;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

/*
 * Copyright (c) 2014 Kenneth Tu <don.ling.lok@gmail.com>
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * @author Kenneth Tu
 * @version 1.0.0
 */
/**
 *
 * @author kennetht
 *
 */
public class NFragment extends Fragment {
	public transient NFragmentActivity activity;
	public transient Context appContext;

	public transient int layoutId;

	public NFragment(final int layoutId) {
		this.layoutId = layoutId;
	}

	@Override
	public void onAttach(final Activity activity) {
		super.onAttach(activity);

		this.activity = (NFragmentActivity) activity;
		appContext = activity.getApplicationContext();
	}
}