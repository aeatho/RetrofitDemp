package com.aeatho.lib_network.transformer;

import com.aeatho.lib_network.bean.HttpResponse;
import com.aeatho.lib_network.error.ExceptionEngine;
import com.aeatho.lib_network.error.ResultException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.http.transformer
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 17:14
 * @version: V1.0
 */
public class Transformers {

  // 统一错误处理
  public static <T> Observable.Transformer<HttpResponse<T>, T> handleResult() {
    return new Observable.Transformer<HttpResponse<T>, T>() {
      @Override public Observable<T> call(Observable<HttpResponse<T>> responseObservable) {
        return responseObservable.map(new Func1<HttpResponse<T>, T>() {
          @Override public T call(HttpResponse<T> tResponse) {
            if (!tResponse.isSuccessful()) {
              throw new ResultException(tResponse.getCode(), tResponse.getMsg());
            }
            return tResponse.getData();
          }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
          @Override public Observable<? extends T> call(Throwable throwable) {
            return Observable.error(ExceptionEngine.handleException(throwable));
          }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }
}
