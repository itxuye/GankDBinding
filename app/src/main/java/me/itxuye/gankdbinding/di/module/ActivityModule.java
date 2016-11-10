package me.itxuye.gankdbinding.di.module;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import me.itxuye.gankdbinding.di.scope.PerActivity;

@Module public class ActivityModule {

  private final Activity mActivity;

  public ActivityModule(Activity mActivity) {
    this.mActivity = mActivity;
  }

  @Provides @PerActivity public Activity provideActivity() {
    return mActivity;
  }
}
