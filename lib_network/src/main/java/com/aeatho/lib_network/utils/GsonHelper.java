package com.aeatho.lib_network.utils;

import android.os.Build;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GsonHelper {

  public static Gson mGson;

  static {
    GsonBuilder gsonBuilder;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      gsonBuilder = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT,
          Modifier.STATIC);
    } else {
      gsonBuilder = new GsonBuilder();
    }
    gsonBuilder.serializeNulls();
    gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
    mGson = gsonBuilder.create();
  }

  public static Gson getGson() {
    return mGson;
  }

  public static String createGsonString(Object object) {
    return getGson().toJson(object);
  }

  public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
    T t = getGson().fromJson(gsonString, cls);
    return t;
  }

  public static <T> T changeGsonToBean(String gsonString, Type type) {
    T t = getGson().fromJson(gsonString, type);
    return t;
  }

  public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
    return getGson().fromJson(gsonString, new TypeToken<List<T>>() {
    }.getType());
  }

  public static <T> List<Map<String, T>> changeGsonToListMaps(String gsonString) {
    List<Map<String, T>> list = null;
    list = getGson().fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
    }.getType());
    return list;
  }

  public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
    Map<String, T> map = null;
    map = getGson().fromJson(gsonString, new TypeToken<Map<String, T>>() {
    }.getType());
    return map;
  }
}
