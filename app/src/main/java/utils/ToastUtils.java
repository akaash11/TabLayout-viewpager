package utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class ToastUtils {

	public static void showToast(@NonNull Context context, @StringRes int messageResId) {
		if (null != context) {
			Toast.makeText(context, context.getString(messageResId), Toast.LENGTH_LONG).show();
		}
	}

	public static void showToast(@NonNull Context context, String message) {
		if (null != context) {
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		}
	}

	public static void showToastOnUiThread(@NonNull final Context context, @StringRes final int messageResId) {
		if (null != context) {
			new Handler(Looper.getMainLooper()).post(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(context, context.getString(messageResId), Toast.LENGTH_LONG).show();
				}
			});
		}
	}

	public static void showToastOnUiThread(@NonNull final Context context, final String message) {
		if (null != context) {
			new Handler(Looper.getMainLooper()).post(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(context, message, Toast.LENGTH_LONG).show();
				}
			});
		}
	}
}