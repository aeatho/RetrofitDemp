package com.aeatho.lib_framework.base;

import android.content.Context;
import android.widget.Toast;
import com.aeatho.lib_framework.utils.UIUtils;
import com.aeatho.mylibrary.R;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.base
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 18:23
 * @version: V1.0
 */
public class GlobalException extends Exception {
  /**
   * 定义异常类型
   */
  public final static byte TYPE_NETWORK = 0x01;
  public final static byte TYPE_SOCKET = 0x02;
  public final static byte TYPE_HTTP_CODE = 0x03;
  public final static byte TYPE_HTTP_ERROR = 0x04;
  public final static byte TYPE_XML = 0x05;
  public final static byte TYPE_IO = 0x06;
  public final static byte TYPE_RUN = 0x07;
  public final static byte TYPE_JSON = 0x08;
  public final static byte TYPE_FILENOTFOUND = 0x09;
  public final static byte TYPE_UNKNOWN = 0x10;
  public final static byte TYPE_HTTP = 0x11;

  // 异常的类型
  private byte type;
  // 异常的状态码，这里一般是网络请求的状态码
  private int code;
  // 异常内容
  private String msg;

  private GlobalException(byte type, Throwable throwable, int code) {
    super(throwable);
    this.type = type;
    this.code = code;
    this.msg = throwable.getMessage();
  }

  private GlobalException(byte type, Throwable throwable) {
    this(type, throwable, 0);
  }

  public int getCode() {
    return this.code;
  }

  public int getType() {
    return this.type;
  }

  public String getMsg() {
    return this.msg;
  }

  public static GlobalException http(int code) {
    return new GlobalException(TYPE_HTTP_CODE, null, code);
  }

  public static GlobalException http(Throwable e) {
    return new GlobalException(TYPE_HTTP_ERROR, e);
  }

  public static GlobalException socket(Throwable throwable, int code) {
    return new GlobalException(TYPE_SOCKET, throwable, code);
  }

  // io异常
  public static GlobalException io(Exception e) {
    return io(e, 0);
  }

  // io异常
  public static GlobalException io(Throwable e, int code) {
    if (e instanceof UnknownHostException || e instanceof ConnectException) {
      return new GlobalException(TYPE_NETWORK, e, code);
    } else if (e instanceof IOException) {
      return new GlobalException(TYPE_IO, e, code);
    }
    return run(e);
  }

  public static GlobalException xml(Throwable e) {
    return new GlobalException(TYPE_XML, e);
  }

  public static GlobalException json(Throwable e) {
    return new GlobalException(TYPE_JSON, e);
  }

  public static GlobalException file(Throwable e) {
    return new GlobalException(TYPE_FILENOTFOUND, e);
  }

  public static GlobalException run(Throwable e) {
    return new GlobalException(TYPE_RUN, e);
  }

  public static GlobalException unKnown(Throwable e) {
    return new GlobalException(TYPE_UNKNOWN, e);
  }

  public static GlobalException socket(Throwable e) {
    return new GlobalException(TYPE_SOCKET, e);
  }

  public static GlobalException httpCommonError(Throwable e) {
    return new GlobalException(TYPE_HTTP, e);
  }

  /**
   * 提示友好的错误信息
   */
  public void show() {
    Context ctx = UIUtils.getContext();
    switch (this.getType()) {
      case TYPE_HTTP_CODE:
        String err = ctx.getString(R.string.http_exception_code_error, this.getCode());
        Toast.makeText(ctx, err, Toast.LENGTH_SHORT).show();
        break;
      case TYPE_HTTP_ERROR:
        Toast.makeText(ctx, R.string.http_exception_error, Toast.LENGTH_SHORT).show();
        break;
      case TYPE_SOCKET:
        Toast.makeText(ctx, R.string.socket_exception_error, Toast.LENGTH_SHORT).show();
        break;
      case TYPE_NETWORK:
        Toast.makeText(ctx, R.string.network_not_connected, Toast.LENGTH_SHORT).show();
        break;
      case TYPE_XML:
        Toast.makeText(ctx, R.string.xml_parser_error, Toast.LENGTH_SHORT).show();
        break;
      case TYPE_JSON:
        Toast.makeText(ctx, R.string.json_parser_error, Toast.LENGTH_SHORT).show();
        break;
      case TYPE_IO:
        Toast.makeText(ctx, R.string.io_exception_error, Toast.LENGTH_SHORT).show();
        break;
      case TYPE_RUN:
        Toast.makeText(ctx, R.string.app_run_code_error, Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
