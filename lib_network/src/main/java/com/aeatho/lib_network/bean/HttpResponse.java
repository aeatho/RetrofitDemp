package com.aeatho.lib_network.bean;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.http.bean
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/6 01:03
 * @version: V1.0
 */
public class HttpResponse<T> {
  private int code;
  private String msg;

  private T data;

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public T getData() {
    return data;
  }

  public boolean isSuccessful() {
    return code == 0;
  }

  @Override public String toString() {
    return "HttpResponse{" +
        "code='" + code + '\'' +
        ", msg='" + msg + '\'' +
        ", data=" + data +
        '}';
  }
}
