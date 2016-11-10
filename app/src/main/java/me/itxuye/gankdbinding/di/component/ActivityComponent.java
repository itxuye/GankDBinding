package me.itxuye.gankdbinding.di.component;

import android.app.Activity;
import dagger.Component;
import me.itxuye.gankdbinding.di.module.ActivityModule;
import me.itxuye.gankdbinding.di.scope.PerActivity;
import me.itxuye.gankdbinding.ui.fragment.gank.GankActivity;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  Activity getActivity();

  void inject(GankActivity gankActivity);
}
