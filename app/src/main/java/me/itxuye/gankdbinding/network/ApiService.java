package me.itxuye.gankdbinding.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import me.itxuye.gankdbinding.BuildConfig;
import me.itxuye.gankdbinding.app.GankApplication;
import me.itxuye.gankdbinding.constant.DateDeserializer;
import me.itxuye.gankdbinding.model.FunnyData;
import me.itxuye.gankdbinding.model.GanHuoData;
import me.itxuye.gankdbinding.model.GankData;
import me.itxuye.gankdbinding.model.MeiziData;
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
import rx.Observable;

/**
 * Created by itxuye on 2016/7/29.
 */

public class ApiService {
  private static final String DATE_PATTERN1 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";
  private static final String DATE_PATTERN2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  private static GankApi mGankApi;
  private static OkHttpClient mOkHttpClient;
  private static OkHttpClient.Builder mBuilder;

  public ApiService() {
    getGankApi();
  }

  static {
    mBuilder = initOkHttpClient();
    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      mBuilder.addInterceptor(interceptor).addNetworkInterceptor(new StethoInterceptor());
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

    Gson dateGson = new GsonBuilder().registerTypeAdapter(Date.class,
        new DateDeserializer(DATE_PATTERN1, DATE_PATTERN2)).serializeNulls().create();

    return new Retrofit.Builder().baseUrl("http://gank.io/api/")
        .addConverterFactory(GsonConverterFactory.create(dateGson))
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

  public Observable<MeiziData> getMeiziData(int page) {
    return mGankApi.getMeiziData(page);
  }

  public Observable<FunnyData> getFunnyData(int page) {
    return mGankApi.getFunnyData(page);
  }

  public Observable<GanHuoData> getGanHuoData(String type, int page) {
    return mGankApi.getGanHuoData(type, page);
  }

  public Observable<GankData> getDailyData(int year, int month, int day) {
    return mGankApi.getDailyData(year, month, day);
  }
}
