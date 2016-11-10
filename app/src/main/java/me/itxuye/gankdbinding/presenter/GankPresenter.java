package me.itxuye.gankdbinding.presenter;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import me.itxuye.gankdbinding.base.RxPresenter;
import me.itxuye.gankdbinding.model.GankData;
import me.itxuye.gankdbinding.model.entity.Gank;
import me.itxuye.gankdbinding.network.ApiService;
import me.itxuye.gankdbinding.presenter.Contract.GankContract;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public class GankPresenter extends RxPresenter<GankContract.View> implements GankContract.Presenter{
  private ApiService mApiService;

  @Inject public GankPresenter(ApiService apiService) {
    this.mApiService = apiService;
  }
  @Override public void fetchGankData(int year, int month, int day) {
    mView.showProgressBar();
    Subscription subscribe =
        mApiService.getDailyData(year, month, day)
            .map(new Func1<GankData, List<Gank>>() {
              @Override public List<Gank> call(GankData gankData) {
                return addAllResults(gankData.results);
              }
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<List<Gank>>() {
              @Override public void call(List<Gank> gankList) {
                mView.showGankList(gankList);
                mView.hideProgressBar();
              }
            }, new Action1<Throwable>() {
              @Override public void call(Throwable throwable) {
               mView.hideProgressBar();
               mView.showErrorView();
              }
            });
    addSubscribe(subscribe);
  }

  private List<Gank> addAllResults(GankData.Result results) {
    List<Gank> mGankList = new ArrayList<>();
    if (results.androidList != null) mGankList.addAll(results.androidList);
    if (results.iOSList != null) mGankList.addAll(results.iOSList);
    if (results.前端List != null) mGankList.addAll(results.前端List);
    if (results.appList != null) mGankList.addAll(results.appList);
    if (results.拓展资源List != null) mGankList.addAll(results.拓展资源List);
    if (results.瞎推荐List != null) mGankList.addAll(results.瞎推荐List);
    if (results.休息视频List != null) mGankList.addAll(0, results.休息视频List);
    return mGankList;
  }
}
