package com.aeatho.app.network;

import com.aeatho.app.bean.MovieEntity;
import com.aeatho.lib_network.bean.HttpResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 16:13
 * @version: V1.0
 */
public interface NewsApi {

  @GET("top250") Observable<HttpResponse<MovieEntity>> getTopMovie(@Query("start") int start,
      @Query("count") int count);
}
