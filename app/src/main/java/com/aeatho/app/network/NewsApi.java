package com.aeatho.app.network;

import com.aeatho.app.bean.PageVo;
import com.aeatho.app.bean.RoomRegisterVo;
import com.aeatho.lib_network.bean.HttpResponse;
import java.util.HashMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
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

  @POST("room/roomregister") Observable<HttpResponse<PageVo<RoomRegisterVo>>> getHouses(
      @QueryMap HashMap<String, String> values);
}
