package android.n;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/*
 * Copyright (c) 2014 Kenneth Tu <don.ling.lok@gmail.com>
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * @author Kenneth Tu
 * @version 1.0.0
 */
public abstract class NAdapter extends BaseAdapter {
	public final transient Activity activity;
	public final transient Context appContext;

	public NAdapter(final Activity activity) {
		super();
		this.activity = activity;
		appContext = activity.getApplicationContext();
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(final int position) {
		return null;
	}

	@Override
	public long getItemId(final int position) {
		return position;
	}

	public View getContentView(final int resid, final ViewGroup parent) {
		return LayoutInflater.from(appContext).inflate(resid, parent, false);
	}
}
