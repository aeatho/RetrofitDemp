package com.aeatho.lib_network.error;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.lib_network.error
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/6 13:12
 * @version: V1.0
 */
public class ApiException extends Exception{
  public int code;
  public String message;

  public ApiException(Throwable throwable, int code) {
    super(throwable);
    this.code = code;
    this.message = throwable.getMessage();
  }
}
