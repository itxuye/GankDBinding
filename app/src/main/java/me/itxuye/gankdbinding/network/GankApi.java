package me.itxuye.gankdbinding.network;

import me.itxuye.gankdbinding.entities.GankBean;
import me.itxuye.gankdbinding.entities.GankDayInfoBean;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by itxuye on 2016/7/30.
 */

public interface GankApi {
  @GET("data/{type}/{number}/{page}") Observable<Response<GankBean>> getGank(@Path("type") String type,
      @Path("number") int number, @Path("page") int page);

  @GET("day/{year}/{month}/{day}") Observable<GankDayInfoBean> getGankDay(@Path("year") int year,
      @Path("month") int month, @Path("day") int day);

  @GET("search/query/listview/category/{type}/count/{count}/page/{page}")
  Observable<GankBean> searchGank(@Path("type") String type, @Path("count") int count,
      @Path("page") int page);
}
