package com.aeatho.app;

import com.aeatho.lib_framework.base.GlobalApplication;
import com.facebook.stetho.Stetho;

/**
 * STAY HUNGRY, STAY FOOLISH!
 *
 * @Prject: Retrofit
 * @Location: com.aeatho.retrofit
 * @Description: TODO
 * @author: loQua.Xee
 * @email: shyscool@163.com
 * @date: 16/8/30 17:56
 * @version: V1.0
 */
public class App extends GlobalApplication {

  @Override public void onCreate() {
    super.onCreate();
    initStetho();
  }

  private void initStetho() {
    Stetho.initializeWithDefaults(this);
  }
}
