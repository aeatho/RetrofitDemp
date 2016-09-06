package com.aeatho.lib_framework.base;

import android.app.Application;
import android.os.Handler;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: RetrofitDemp
 * @Location: com.aeatho.retrofit.base
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/9/5 18:27
 * @version: V1.0
 */
public class GlobalApplication extends Application {
  private static GlobalApplication _context;

  //获取到主线程的handler
  private static Handler mMainThreadHanler;
  //获取到主线程
  private static Thread mMainThread;
  //获取到主线程的id
  private static int mMainThreadId;

  @Override public void onCreate() {
    super.onCreate();
    _context = this;
    init();
  }

  public static GlobalApplication getInstance() {
    return _context;
  }

  private void init() {
    mMainThreadHanler = new Handler();
    mMainThread = Thread.currentThread();
    //获取到调用线程的id
    mMainThreadId = android.os.Process.myTid();

  }

  public static Handler getMainThreadHandler() {
    return mMainThreadHanler;
  }

  public static Thread getMainThread() {
    return mMainThread;
  }

  public static int getMainThreadId() {
    return mMainThreadId;
  }
}
