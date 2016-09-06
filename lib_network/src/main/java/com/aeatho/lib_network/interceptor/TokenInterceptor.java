package com.aeatho.lib_network.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.http.interceptor
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/6 10:14
 * @version: V1.0
 */
public class TokenInterceptor implements Interceptor {
  @Override public Response intercept(Chain chain) throws IOException {
    Response response = chain.proceed(chain.request());

    String responseBody = response.body().string();

    return response;
  }
}
