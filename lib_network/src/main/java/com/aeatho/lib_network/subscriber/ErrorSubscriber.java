package com.aeatho.lib_network.subscriber;

import com.aeatho.lib_network.error.ApiException;
import com.aeatho.lib_network.error.ERROR;
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
public abstract class ErrorSubscriber<T> extends Subscriber<T> {

  @Override public void onError(Throwable e) {
    if (e instanceof ApiException) {
      onError((ApiException) e);
    } else {
      onError(new ApiException(e, ERROR.UNKNOWN));
    }
  }

  /**
   * 错误回调
   */
  protected abstract void onError(ApiException ex);
}
