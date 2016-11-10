package me.itxuye.gankdbinding.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;
import dagger.Module;
import dagger.Provides;
import me.itxuye.gankdbinding.di.scope.PerFragment;

/**
 * @author Created by itxuye(http://itxuye.com)
 *         on   2016/11/9 2:38
 * @version 1.0.0
 */

@Module
public class FragmentModule {

  private Fragment fragment;

  public FragmentModule(Fragment fragment) {
    this.fragment = fragment;
  }

  @Provides
  @PerFragment
  public Activity provideActivity() {
    return fragment.getActivity();
  }
}

