package com.aeatho.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.aeatho.app.bean.PageVo;
import com.aeatho.app.bean.RoomRegisterVo;
import com.aeatho.app.network.ApiFactory;
import com.aeatho.lib_network.listener.SubscriberOnNextListener;
import com.aeatho.lib_network.subscriber.ProgressSubscriber;
import com.aeatho.lib_network.transformer.Transformers;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  private String TAG = "test";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final HashMap<String, String> values = new HashMap<>();

    //带图片带价格通用传参
    values.put("roomStatus", "1005002,1005003,1005005");
    values.put("selectTags", "true");
    values.put("selectPictures", "true");
    values.put("selectCommunity", "true");
    values.put("selectTags", "true");
    values.put("page", "1");
    values.put("pageSize", "20");
    //values.put("cityId", AccountCache.getLastCityId());
    values.put("recommend", "1");

    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        ApiFactory.getNewsApi()
            .getHouses(values)
            .compose(Transformers.<PageVo<RoomRegisterVo>>handleResult())
            //.compose(RxHelper.handleResult())
            //.compose(Transformers.<MovieEntity>switchSchedulers())
            .subscribe(new ProgressSubscriber<PageVo<RoomRegisterVo>>(
                new SubscriberOnNextListener<PageVo<RoomRegisterVo>>() {
                  @Override public void onNext(PageVo<RoomRegisterVo> roomRegisterVoPageVo) {

                  }
                }, MainActivity.this));
      }
    });

    new Thread(new Runnable() {
      @Override public void run() {
        Observable.create(new Observable.OnSubscribe<String>() {
          @Override public void call(Subscriber<? super String> subscriber) {
            Log.d(TAG, "thread produce:" + Thread.currentThread().getName());
            subscriber.onNext("test1");
            subscriber.onNext("test2");
            subscriber.onNext("test3");
            subscriber.onCompleted();
          }
        }).map(new Func1<String, Integer>() {
          @Override public Integer call(String s) {
            return null;
          }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.newThread())
            //.subscribe(new Subscriber<String>() {
            //  @Override public void onStart() {
            //    Log.d(TAG, "start");
            //  }
            //
            //  @Override public void onCompleted() {
            //    Log.d(TAG, "competed");
            //  }
            //
            //  @Override public void onError(Throwable e) {
            //    Log.d(TAG, "error");
            //  }
            //
            //  @Override public void onNext(String s) {
            //    Log.d(TAG, "thread subscribe:" + Thread.currentThread().getName());
            //    Log.d(TAG, s);
            //  }
            //});
            .subscribe(new Action1<Integer>() {
              @Override public void call(Integer integer) {

              }
            });
      }
    }).start();

    //Person[] s = null;
    //Observable.from(s)
    //    .flatMap(new Func1<Person, Observable<Person.Course>>() {
    //      @Override public Observable<Person.Course> call(Person person) {
    //        return Observable.from(person.courses);
    //      }
    //    })
    //    .map(new Func1<Person.Course, Double>() {
    //      @Override public Double call(Person.Course course) {
    //        return course.score;
    //      }
    //    })
    //    .subscribeOn(Schedulers.io())
    //    .observeOn(AndroidSchedulers.mainThread())
    //    .subscribe(new Action1<Double>() {
    //      @Override public void call(Double aDouble) {
    //
    //      }
    //    });

    Observable.timer(2, 2, TimeUnit.SECONDS).map(new Func1<Long, String>() {
      @Override public String call(Long aLong) {
        Log.d(TAG, Thread.currentThread().getName());
        return String.valueOf(aLong);
      }
    }).take(10).groupBy(new Func1<String, String>() {
      @Override public String call(String s) {
        return String.valueOf(Integer.parseInt(s) / 3);
      }
    }).subscribe(new Action1<GroupedObservable<String, String>>() {
      @Override public void call(final GroupedObservable<String, String> result) {

        result.subscribe(new Action1<String>() {
          @Override public void call(String value) {
            Log.d(TAG, "key:" + result.getKey() + ", value:" + value);
          }
        });
      }
    });

    Observable.just(1, 2, 3).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer integer) {
        return integer > 3;
      }
    }).cast(Person.class).subscribe(new Subscriber<Person>() {
      @Override public void onCompleted() {
        Log.d(TAG, "cast: " + "finished");
      }

      @Override public void onError(Throwable e) {
        Log.d(TAG, "cast: " + "error");
      }

      @Override public void onNext(Person person) {
        Log.d(TAG, "cast: " + "0809");
      }
    });

    Observable.just(1, 2, 3).cast(Integer.class).scan(new Func2<Integer, Integer, Integer>() {
      @Override public Integer call(Integer sum, Integer item) {
        return sum + item;
      }
    }).subscribe(new Action1<Integer>() {
      @Override public void call(Integer integer) {
        Log.d(TAG, "Next: " + integer);
      }
    });
    //    .subscribe(new Action1<String>() {
    //  @Override public void call(String s) {
    //
    //    Log.d(TAG, s);
    //  }
    //});
    //int i= 0;
    Observable.defer(new Func0<Observable<Integer>>() {
      @Override public Observable<Integer> call() {
        return Observable.just(1, 2, 3);
      }
    });

    Observable.range(3, 3).repeat(2).subscribe(new Action1<Integer>() {
      @Override public void call(Integer integer) {
        Log.d(TAG, "--" + String.valueOf(integer));
      }
    });
    Observable.range(0, 4).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
      @Override public Observable<?> call(Observable<? extends Void> observable) {
        return null;
      }
    });

    Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override public void call(Subscriber<? super Integer> subscriber) {
        subscriber.onNext(1);
        subscriber.onNext(2);
        subscriber.onNext(3);
        subscriber.onCompleted();
      }
    }).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
      @Override public Observable<?> call(Observable<? extends Void> observable) {
        return Observable.timer(2, TimeUnit.SECONDS);
      }
    }).subscribe(new Action1<Integer>() {
      @Override public void call(Integer integer) {
        Log.d(TAG, "+++" + String.valueOf(integer));
      }
    });
  }

  public class Person {
    public String name;
    public List<Course> courses;

    class Course {
      public double score;
    }
  }
}
