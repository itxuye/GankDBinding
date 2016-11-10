package me.itxuye.gankdbinding.di.component;

import android.app.Activity;
import dagger.Component;
import me.itxuye.gankdbinding.di.module.FragmentModule;
import me.itxuye.gankdbinding.di.scope.PerFragment;
import me.itxuye.gankdbinding.ui.fragment.gank.GankActivity;
import me.itxuye.gankdbinding.ui.fragment.home.AllFragment;
import me.itxuye.gankdbinding.ui.fragment.home.GanHuoFragment;

/**
 * @author Created by itxuye(http://itxuye.com)
 *         on   2016/11/9 2:39
 * @version 1.0.0
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
  Activity getActivity();

  void inject(AllFragment allFragment);

  void inject(GanHuoFragment ganHuoFragment);

  void inject(GankActivity gankActivity);
}
