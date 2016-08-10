package me.itxuye.gankdbinding.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import me.itxuye.gankdbinding.BuildConfig;
import me.itxuye.gankdbinding.app.GankApplication;
import me.itxuye.gankdbinding.utils.NetUtil;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by itxuye on 2016/7/29.
 */

public class ApiService {
  private static GankApi mGankApi;
  private static OkHttpClient mOkHttpClient;
  private static OkHttpClient.Builder mBuilder;

  static {
    mBuilder = initOkHttpClient();
    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      mBuilder
          .addInterceptor(interceptor)
          .addNetworkInterceptor(new StethoInterceptor());
    }
    mOkHttpClient = mBuilder.build();
  }

  public static GankApi getGankApi() {
    if (mGankApi == null) {
      mGankApi = createAdapter().create(GankApi.class);
    }
    return mGankApi;
  }

  private static Retrofit createAdapter() {
    return new Retrofit.Builder().baseUrl("http://gank.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(mOkHttpClient)
        .build();
  }

  /**
   * 初始化OKHttpClient
   */
  private static OkHttpClient.Builder initOkHttpClient() {

    if (mBuilder == null) {
      synchronized (ApiService.class) {
        if (mBuilder == null) {
          //设置Http缓存
          Cache cache =
              new Cache(new File(GankApplication.getContext().getCacheDir(), "OkHttpCache"),
                  1024 * 1024 * 100);

          mBuilder = new OkHttpClient.Builder().readTimeout(60000, TimeUnit.MILLISECONDS)
              .connectTimeout(60000, TimeUnit.MILLISECONDS)
              .writeTimeout(6000, TimeUnit.MILLISECONDS)
              .retryOnConnectionFailure(true)
              .cache(cache)
              .addInterceptor(new Interceptor() {
                @Override public Response intercept(Chain chain) throws IOException {
                  Request originalRequest = chain.request();
                  Request requestWithUserAgent = originalRequest.newBuilder()
                      .header("User-Agent", "Android/"
                          + android.os.Build.VERSION.RELEASE
                          + " GankApp/"
                          + String.valueOf(BuildConfig.VERSION_CODE)
                          + " Mobile/"
                          + android.os.Build.MODEL
                          + " NetType/"
                          + NetUtil.getNetType(GankApplication.getContext()))
                      .build();

                  return chain.proceed(requestWithUserAgent);
                }
              });
        }
      }
    }
    return mBuilder;
  }
}
