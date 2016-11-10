package me.itxuye.gankdbinding.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
import com.squareup.leakcanary.LeakCanary;
import me.itxuye.gankdbinding.BuildConfig;
import me.itxuye.gankdbinding.di.component.ApplicationComponent;
import me.itxuye.gankdbinding.di.component.DaggerApplicationComponent;
import me.itxuye.gankdbinding.di.module.ApplicationModule;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/7/29.
 */

public class GankApplication extends MultiDexApplication {
  public static synchronized GankApplication getContext() {
    return context;
  }

  private static GankApplication context;

  @Override public void onCreate() {
    super.onCreate();
    context = this;
    initLogger();
    LeakCanary.install(this);
  }

  private void initLogger() {
    Logger.initialize(
        new Settings()
            .isShowMethodLink(true)
            .isShowThreadInfo(true)
            .setMethodOffset(0)
            .setLogPriority(BuildConfig.DEBUG ? Log.VERBOSE : Log.ASSERT)
    );
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }

  public static ApplicationComponent getAppComponent(){
    return DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(context))
        .build();
  }
}
