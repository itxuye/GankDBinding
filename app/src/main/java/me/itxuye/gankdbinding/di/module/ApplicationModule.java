package me.itxuye.gankdbinding.di.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.itxuye.gankdbinding.di.scope.ContextLife;
import me.itxuye.gankdbinding.network.ApiService;
import org.greenrobot.eventbus.EventBus;

/**
 * @author Created by itxuye(http://itxuye.com)
 *         on  2016/9/2 11:06
 * @version 1.0.0
 */
@Module public class ApplicationModule {

  private final Context context;

  public ApplicationModule(Context context) {
    this.context = context;
  }

  @Provides
  @ContextLife
  @Singleton
  public Context provideApplicationContext() {
    return context.getApplicationContext();
  }

  @Provides @Singleton public EventBus provideBusEvent() {
    return new EventBus();
  }

  @Provides @Singleton public ApiService provideApiService() {
    return new ApiService();
  }
}
