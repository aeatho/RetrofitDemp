package com.aeatho.lib_network.error;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.http.error
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 17:51
 * @version: V1.0
 */
public class CommonResultException extends RuntimeException {
  public int code;
  public String message;

  public CommonResultException(int code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }
}
