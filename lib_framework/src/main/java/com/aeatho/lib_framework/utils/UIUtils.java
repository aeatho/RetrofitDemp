package com.aeatho.lib_framework.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.aeatho.lib_framework.base.GlobalApplication;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: Retrofit
 * @Location: com.aeatho.retrofit.utils
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/8/30 17:57
 * @version: V1.0
 */
public class UIUtils {
  public static Context getContext() {
    return GlobalApplication.getInstance();
  }

  public static Thread getUIThread() {
    return GlobalApplication.getMainThread();
  }

  public static long getUIThreadId() {
    return GlobalApplication.getMainThreadId();
  }

  /**
   * 获取主线程的handler
   */
  public static Handler getHandler() {
    return GlobalApplication.getMainThreadHandler();
  }

  /**
   * 延时在主线程执行runnable
   */
  public static boolean postDelayed(Runnable runnable, long delayMillis) {
    return getHandler().postDelayed(runnable, delayMillis);
  }

  /**
   * 在主线程执行runnable
   */
  public static boolean post(Runnable runnable) {
    return getHandler().post(runnable);
  }

  /**
   * 获取资源
   */
  public static Resources getResources() {
    return getContext().getResources();
  }

  /**
   * 获取文字
   */
  public static String getString(int resId) {
    return getResources().getString(resId);
  }

  public static String getString(int resId, int... content) {
    return getResources().getString(resId, content);
  }

  /**
   * 获取文字数组
   */
  public static String[] getStringArray(int resId) {
    return getResources().getStringArray(resId);
  }

  /**
   * 获取dimen
   */
  public static int getDimens(int resId) {
    return getResources().getDimensionPixelSize(resId);
  }

  /**
   * 获取drawable
   */
  public static Drawable getDrawable(int resId) {
    return getResources().getDrawable(resId);
  }

  /**
   * 获取颜色
   */
  public static int getColor(int resId) {
    return getResources().getColor(resId);
  }

  /**
   * 获取颜色选择器
   */
  public static ColorStateList getColorStateList(int resId) {
    return getResources().getColorStateList(resId);
  }

  //判断当前的线程是不是在主线程
  public static boolean isRunInUIThread() {
    return android.os.Process.myTid() == getUIThreadId();
  }

  public static void runInMainThread(Runnable runnable) {
    if (isRunInUIThread()) {
      runnable.run();
    } else {
      post(runnable);
    }
  }
}
