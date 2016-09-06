package com.aeatho.lib_network.error;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.lib_network.error
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/6 16:13
 * @version: V1.0
 */
public class SessionInvalidException extends RuntimeException {

  public SessionInvalidException() {
    super("登录失效,请重新登录");
  }
}
