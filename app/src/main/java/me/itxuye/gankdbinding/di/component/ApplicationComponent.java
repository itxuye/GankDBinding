package me.itxuye.gankdbinding.di.component;

import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import me.itxuye.gankdbinding.di.module.ApplicationModule;
import me.itxuye.gankdbinding.di.scope.ContextLife;
import me.itxuye.gankdbinding.network.ApiService;

@Singleton @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  @ContextLife("Application")
  Context getContext();

  ApiService mApiService();
}
