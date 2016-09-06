package com.aeatho.lib_network.subscriber;

import android.content.Context;
import android.widget.Toast;
import com.aeatho.lib_network.error.ApiException;
import com.aeatho.lib_network.progress.ProgressCancelListener;
import com.aeatho.lib_network.progress.ProgressDialogHandler;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.http.subscribers
 * @Description: * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 16:54
 * @version: V1.0
 */
public abstract class ProgressRxSubscriber<T> extends RxSubscriber<T>
    implements ProgressCancelListener {
  //private SubscriberOnNextListener<T> mSubscriberOnNextListener;
  private ProgressDialogHandler mProgressDialogHandler;

  private Context mContext;

  public ProgressRxSubscriber(Context context) {
    this(context, true);
  }

  public ProgressRxSubscriber(Context context,
      boolean cancel) {
    //this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    this.mContext = context;
    this.mProgressDialogHandler = new ProgressDialogHandler(context, this, cancel);
  }

  private void showProgressDialog() {
    if (mProgressDialogHandler != null) {
      mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG)
          .sendToTarget();
    }
  }

  private void dismissProgressDialog() {
    if (mProgressDialogHandler != null) {
      mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)
          .sendToTarget();
      mProgressDialogHandler = null;
    }
  }

  /**
   * 订阅开始时调用
   * 显示ProgressDialog
   */
  @Override public void onStart() {
    showProgressDialog();
  }

  /**
   * 完成，隐藏ProgressDialog
   */
  @Override public void onCompleted() {
    dismissProgressDialog();
  }

  ///**
  // * 对错误进行统一处理
  // * 隐藏ProgressDialog
  // */
  //
  @Override protected void onError(ApiException ex) {
    dismissProgressDialog();
    Toast.makeText(mContext, ex.message, Toast.LENGTH_SHORT).show();
  }
  //
  ///**
  // * 将onNext方法中的返回结果交给Activity或Fragment自己处理
  // *
  // * @param t 创建Subscriber时的泛型类型
  // */
  //@Override public void onNext(T t) {
  //  if (mSubscriberOnNextListener != null) {
  //    mSubscriberOnNextListener.onNext(t);
  //  }
  //}

  /**
   * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
   */
  @Override public void onCancelProgress() {
    if (!this.isUnsubscribed()) {
      this.unsubscribe();
    }
  }
}
