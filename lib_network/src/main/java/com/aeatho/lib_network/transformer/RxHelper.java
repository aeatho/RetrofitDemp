package com.aeatho.lib_network.transformer;

import com.aeatho.lib_network.bean.HttpResponse;
import rx.Observable;
import rx.Subscriber;
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
 * @date: 16/9/6 01:02
 * @version: V1.0
 */
public class RxHelper {

  /**
   * 对结果进行预处理
   */
  public static <T> Observable.Transformer<HttpResponse<T>, T> handleResult() {
    return new Observable.Transformer<HttpResponse<T>, T>() {
      @Override public Observable<T> call(Observable<HttpResponse<T>> tObservable) {
        return tObservable.flatMap(new Func1<HttpResponse<T>, Observable<T>>() {
          @Override public Observable<T> call(HttpResponse<T> result) {
            if (result.isSuccessful()) {
              return createData(result.getData());
            } else {
              return Observable.error(new RuntimeException(result.getMsg()));
            }
          }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  /**
   * 创建成功的数据
   */
  private static <T> Observable<T> createData(final T data) {
    return Observable.create(new Observable.OnSubscribe<T>() {
      @Override public void call(Subscriber<? super T> subscriber) {
        try {
          subscriber.onNext(data);
          subscriber.onCompleted();
        } catch (Exception e) {
          subscriber.onError(e);
        }
      }
    });
  }
}
