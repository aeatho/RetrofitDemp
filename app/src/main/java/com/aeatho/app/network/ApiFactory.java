package com.aeatho.app.network;
import com.aeatho.lib_network.RetrofitFactory;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 16:11
 * @version: V1.0
 */
public class ApiFactory {

  public static NewsApi getNewsApi() {
    return RetrofitFactory.getInstance().get(NewsApi.class);
  }

  public static NewsApi getSyncNewsApi() {
    return RetrofitFactory.getInstance().getSync(NewsApi.class);
  }
}
