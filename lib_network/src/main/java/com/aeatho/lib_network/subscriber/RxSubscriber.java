package com.aeatho.lib_network.subscriber;

import com.aeatho.lib_network.error.ApiException;
import com.aeatho.lib_network.error.ErrorCode;
import rx.Subscriber;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.http.subscriber
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 18:05
 * @version: V1.0
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

  @Override public void onCompleted() {

  }

  @Override public void onError(Throwable e) {
    if (e instanceof ApiException) {
      onError((ApiException) e);
    } else {
      onError(new ApiException(e, ErrorCode.UNKNOWN));
    }
  }

  @Override public void onNext(T t) {
    onSuccess(t);
  }

  /**
   * 错误回调
   */
  protected void onError(ApiException ex) {

  }

  protected abstract void onSuccess(T response);
}
