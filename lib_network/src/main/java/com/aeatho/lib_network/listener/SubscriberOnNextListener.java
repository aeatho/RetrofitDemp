package com.aeatho.lib_network.listener;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.http.listener
 * @Description: 成功回调处理
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 16:53
 * @version: V1.0
 */
public interface SubscriberOnNextListener<T> {
  void onNext(T t);
}
