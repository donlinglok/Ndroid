package android.n;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;

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
public class NFragmentActivity extends FragmentActivity {
	public transient FragmentActivity activity;
	public transient Context appContext;

	public transient int deviceHeight;
	public transient int deviceWidth;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		onCreate(savedInstanceState, true);
	}

	private final transient Thread.UncaughtExceptionHandler onRuntimeError = new Thread.UncaughtExceptionHandler() {
		@SuppressLint("NewApi")
		@Override
		public void uncaughtException(final Thread thread, final Throwable exception) {
			exception.printStackTrace();
			final Intent intent = appContext.getPackageManager().getLaunchIntentForPackage(appContext.getPackageName());
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
			startActivity(intent);
			System.exit(0);
		}
	};

	public void onCreate(final Bundle savedInstanceState, final boolean isUndead) {
		super.onCreate(savedInstanceState);

		activity = this;
		appContext = activity.getApplicationContext();

		deviceHeight = getResources().getDisplayMetrics().heightPixels;
		deviceWidth = getResources().getDisplayMetrics().widthPixels;

		if (isUndead) {
			Thread.setDefaultUncaughtExceptionHandler(onRuntimeError);
		}

		setConfigCallback((WindowManager) getApplicationContext().getSystemService("window"));

		setStatusBarColor(0);
	}

	@SuppressLint("NewApi")
	public void setStatusBarColor(final int color) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			final Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(color);
		}
	}

	@Override
	public void onDestroy() {
		setConfigCallback(null);
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(final Bundle bundle) {
		// No call for super(). Bug on API Level > 11.
	}

	private transient ProgressDialog progressDialog;

	public void showProgressDialog() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (null == progressDialog) {
					progressDialog = new ProgressDialog(appContext);
					progressDialog.setCancelable(false);
				}
				if (!progressDialog.isShowing() && !activity.isFinishing()) {
					progressDialog.show();
				}
			}
		});
	}

	public void closeProgressDialog() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (null != progressDialog && progressDialog.isShowing() && !activity.isFinishing()) {
					progressDialog.cancel();
				}
			}
		});
	}

	public void fadeIn(final View view) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (View.VISIBLE != view.getVisibility()) {
					view.startAnimation(AnimationUtils.loadAnimation(appContext, android.R.anim.fade_in));
					view.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	public void fadeOut(final View view) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (View.GONE != view.getVisibility()) {
					view.startAnimation(AnimationUtils.loadAnimation(appContext, android.R.anim.fade_out));
					view.setVisibility(View.GONE);
				}
			}
		});
	}

	public final void setConfigCallback(final WindowManager windowManager) {
		try {
			Field field = WebView.class.getDeclaredField("mWebViewCore");
			field = field.getType().getDeclaredField("mBrowserFrame");
			field = field.getType().getDeclaredField("sConfigCallback");
			field.setAccessible(true);
			final Object configCallback = field.get(null);

			if (configCallback == null) {
				return;
			}

			field = field.getType().getDeclaredField("mWindowManager");
			field.setAccessible(true);
			field.set(configCallback, windowManager);
		} catch (final NoSuchFieldException localNoSuchFieldException) {
		} catch (final IllegalAccessException localIllegalAccessException) {
		} catch (final IllegalArgumentException localIllegalArgumentException) {
		}
	}
}