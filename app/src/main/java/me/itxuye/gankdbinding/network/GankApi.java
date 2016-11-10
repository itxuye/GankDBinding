package me.itxuye.gankdbinding.network;

import me.itxuye.gankdbinding.constant.GankApiConstant;
import me.itxuye.gankdbinding.model.FunnyData;
import me.itxuye.gankdbinding.model.GanHuoData;
import me.itxuye.gankdbinding.model.GankData;
import me.itxuye.gankdbinding.model.MeiziData;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by itxuye on 2016/7/30.
 */

public interface GankApi {

  @GET("data/{type}/{number}/{page}")
  Observable<Response<GanHuoData>> getGank(
      @Path("type") String type,
      @Path("number") int number,
      @Path("page") int page);

  @GET("day/{year}/{month}/{day}")
  Observable<Response<GankData>> getGankDay(
      @Path("year") int year,
      @Path("month") int month,
      @Path("day") int day);

  @GET("search/query/listview/category/{type}/count/{count}/page/{page}")
  Observable<Response<GankData>> searchGank(
      @Path("type") String type,
      @Path("count") int count,
      @Path("page") int page);

  // http://gank.io/api/data/数据类型/请求个数/第几页
  @GET(value = "data/福利/" + GankApiConstant.MEIZI_SIZE + "/{page}")
  Observable<MeiziData> getMeiziData(@Path("page") int page);
  @GET("data/休息视频/" + GankApiConstant.MEIZI_SIZE + "/{page}")
  Observable<FunnyData> getFunnyData(@Path("page") int page);

  //请求不同类型干货（通用）
  @GET("data/{type}/"+GankApiConstant.GANK_SIZE+"/{page}")
  Observable<GanHuoData> getGanHuoData(@Path("type") String type, @Path("page") int page);

  //请求某天干货数据
  @GET("day/{year}/{month}/{day}")
  Observable<GankData> getDailyData(
      @Path("year") int year,
      @Path("month") int month,
      @Path("day") int day);
}
