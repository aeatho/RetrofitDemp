package com.aeatho.lib_network.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.http.progress
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 16:41
 * @version: V1.0
 */
public class ProgressDialogHandler extends Handler {
  public static final int SHOW_PROGRESS_DIALOG = 1;
  public static final int DISMISS_PROGRESS_DIALOG = 2;

  private ProgressDialog pd;

  private WeakReference<Context> mActivity;
  private boolean cancelable;
  private ProgressCancelListener mProgressCancelListener;

  public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
      boolean cancelable) {
    super();
    this.mActivity = new WeakReference<>(context);
    this.mProgressCancelListener = mProgressCancelListener;
    this.cancelable = cancelable;
    initProgressDialog();
  }

  /**
   * 初始化加载框
   */
  private void initProgressDialog() {
    Context context = mActivity.get();
    if (pd == null && context != null) {
      pd = new ProgressDialog(context);
      pd.setCancelable(cancelable);
      if (cancelable) {
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
          @Override public void onCancel(DialogInterface dialogInterface) {
            mProgressCancelListener.onCancelProgress();
          }
        });
      }
    }
  }

  /**
   * 显示加载框
   */
  private void showProgressDialog() {
    Context context = mActivity.get();
    if (pd == null || context == null) return;
    if (!pd.isShowing()) {
      pd.show();
    }
  }

  /**
   * 隐藏
   */
  private void dismissProgressDialog() {
    if (pd != null && pd.isShowing()) {
      pd.dismiss();
    }
  }

  @Override public void handleMessage(Message msg) {
    switch (msg.what) {
      case SHOW_PROGRESS_DIALOG:
        showProgressDialog();
        break;
      case DISMISS_PROGRESS_DIALOG:
        dismissProgressDialog();
        break;
    }
  }
}
