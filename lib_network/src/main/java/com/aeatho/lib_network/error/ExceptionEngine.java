package com.aeatho.lib_network.error;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.json.JSONException;
import retrofit2.adapter.rxjava.HttpException;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.http.error
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 17:43
 * @version: V1.0
 */
public class ExceptionEngine {

  public static ApiException handleException(Throwable e) {
    ApiException ex;
    if (e instanceof HttpException) {
      HttpException httpException = (HttpException) e;
      ex = new ApiException(e, ErrorCode.HTTP_ERROR);
      if (httpException.code() >= 400 && httpException.code() <= 599) {
      } else {
        ex.message = "网络错误";  //均视为网络错误
      }
      return ex;
    } else if (e instanceof CommonResultException) {    //服务器返回的错误
      CommonResultException resultException = (CommonResultException) e;
      ex = new ApiException(resultException, resultException.code);
      ex.message = resultException.message;
      return ex;
    } else if (e instanceof JsonParseException
        || e instanceof JSONException
        || e instanceof ParseException) {
      ex = new ApiException(e, ErrorCode.PARSE_ERROR);
      ex.message = "解析错误";            //均视为解析错误
      return ex;
    } else if (e instanceof UnknownHostException
        || e instanceof ConnectException
        || e instanceof SocketException) {
      ex = new ApiException(e, ErrorCode.NETWORD_ERROR);
      ex.message = "连接失败";  //均视为网络错误
      return ex;
    } else {
      ex = new ApiException(e, ErrorCode.UNKNOWN);
      ex.message = "未知错误";          //未知错误
      return ex;
    }
  }
}
