package com.aeatho.lib_network;

import com.aeatho.lib_network.interceptor.TokenInterceptor;
import com.aeatho.lib_network.utils.GsonHelper;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: Retrofit
 * @Location: com.aeatho.retrofit.http
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/8/30 17:34
 * @version: V1.0
 */
public class RetrofitFactory {
  //public static final String API_SERVER = UIUtils.getString(R.string.base_url);
  public static final String API_SERVER = "http://dev.unovo.com.cn/saas20/api/1/AptGuest/";

  private volatile static RetrofitFactory instance;

  public static RetrofitFactory getInstance() {
    if (instance == null) {
      synchronized (RetrofitFactory.class) {
        if (instance == null) {
          instance = new RetrofitFactory();
        }
      }
    }
    return instance;
  }

  public <T> T get(Class<T> tClass) {
    return getRetrofit(true).create(tClass);
  }

  // 创建一个同步的retrofit客户端，其实就是不要去使用RxJava的adapter啦
  public <T> T getSync(Class<T> tClass) {
    return getRetrofit(false).create(tClass);
  }

  private Retrofit getRetrofit(boolean useRxJava) {
    Retrofit.Builder builder = new Retrofit.Builder().client(getClient()).baseUrl(API_SERVER)
        // 增加返回值为String的支持
        .addConverterFactory(ScalarsConverterFactory.create())
        // 增加返回值为Gson的支持(以实体类返回)
        .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson()));
    // 增加返回值为Oservable<T>的支持
    if (useRxJava) builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    return builder.build();
  }

  private static OkHttpClient getClient() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
    interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BASIC
        : HttpLoggingInterceptor.Level.BASIC);
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(interceptor)
        .retryOnConnectionFailure(true)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS);
    if (BuildConfig.DEBUG) {
      builder.addNetworkInterceptor(new StethoInterceptor());
    }
    builder.addNetworkInterceptor(new TokenInterceptor());
    return builder.build();
  }
}
